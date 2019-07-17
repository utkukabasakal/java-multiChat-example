package com.company;
import java.io.*;
import java.net.*;

class MessageSettings implements Message {
    private String input = null;
    private boolean command=true;
    private String userName;
    public void getMessage(BufferedReader in) throws IOException {
        while (true) {
            try {
                String s = in.readLine();
                System.out.println(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void sendMessage(Socket socket,BufferedReader sin,BufferedReader stdin,PrintWriter printWriter, Server server,int count,String userName) {
        try {
            try {
                userName =userName ;
                while (true) {
                    try {
                        setInput(sin.readLine());
                    }
                    catch (Exception e)
                    {
                        System.out.println(userName +"  odadan ayrıldı  ");
                        break;
                    }
                    System.out.println(getInput());
                    for(int i = 0; i < server.threadsArray.size(); i++) {
                        ServerThread serverThread = server.threadsArray.get(i);
                        if(getInput().equalsIgnoreCase("Bye")){
                            if(userName==serverThread.getUserName()){
                                server.threadsArray.remove(count-1);
                                socket.close();
                                printWriter.close();
                                sin.close();
                                stdin.close();
                            }
                            setCommand(false);
                        }
                        if(isCommand()) {
                            send(getInput(), serverThread.getPrintWriter());
                        }
                    }
                }
            }
            catch (NullPointerException e) {
                e.printStackTrace();
                server.threadsArray.remove(count-1);
                socket.close();
                printWriter.close();
                sin.close();
                stdin.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try {
            socket.close();
            printWriter.close();
            sin.close();
            stdin.close();
            server.threadsArray.remove(count-1);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void send(String message, PrintWriter printWriter) {
        printWriter.println(message);
        printWriter.flush();
    }
    public String getInput() {
        return input;
    }
    public void setInput(String input) {
        this.input = input;
    }
    public boolean isCommand() {
        return command;
    }
    public void setCommand(boolean command) {
        this.command = command;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
}