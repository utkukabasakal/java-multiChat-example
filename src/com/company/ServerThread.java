package com.company;
import java.io.*;
import java.net.*;
import java.util.Scanner;
public class ServerThread extends Thread {
    private int count=0;
    private String string;
    private String userName;
    private Server server = null;
    private String input = null;
    private Socket socket = null;
    private BufferedReader sin = null;
    private BufferedReader stdin = null;
    private PrintWriter printWriter = null;
    private Scanner scanner=new Scanner(System.in);
    public String getUserName() {
        return userName;
    }
    public PrintWriter getPrintWriter() {
        return printWriter;
    }
    public ServerThread(Socket socket, Server server){
        this.setServer(server);
        this.setSocket(socket);
        this.setCount(this.getCount() + 1);
    }
    @Override
    public void run() {
        /*int x=1;
        try{
            while(true){
                string=sin.readLine();
                System.out.print("Server : ");
                string=scanner.nextLine();
                if (string.equalsIgnoreCase("bye"))
                {
                    printWriter.println("BYE");
                    x=0;
                    System.out.println("Bağlanti sonlandirildi");
                    break;
                }
                printWriter.println(string);
            }
            sin.close();
            socket.close();
            printWriter.close();
            if(x==0) {
                System.out.println( "\n" +"Sunucu temizleniyor." );
                System.exit(0);
            }
        }
        catch(IOException ex){
            System.out.println("Error : "+ex);
        }*/
        try {
            setSin(new BufferedReader(new InputStreamReader(getSocket().getInputStream())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setStdin(new BufferedReader(new InputStreamReader(System.in)));
        try {
            setPrintWriter(new PrintWriter(getSocket().getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {

            userName= sin.readLine();

            System.out.println("=>" + userName );

        } catch (IOException e) {

            e.printStackTrace();

        }
        Message message=new MessageSettings();
        message.sendMessage(getSocket(), getSin(), getStdin(), getPrintWriter(), getServer(), getCount(), getUserName());
    }
    public Server getServer() {
        return server;
    }
    public void setServer(Server server) {
        this.server = server;
    }
    public BufferedReader getSin() {
        return sin;
    }
    public void setSin(BufferedReader sin) {
        this.sin = sin;
    }
    public BufferedReader getStdin() {
        return stdin;
    }
    public void setStdin(BufferedReader stdin) {
        this.stdin = stdin;
    }
    public void setPrintWriter(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }
    public Socket getSocket() {
        return socket;
    }
    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    public String getInput() {
        return input;
    }
    public void setInput(String input) {
        this.input = input;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public Scanner getScanner() {
        return scanner;
    }
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
    public String getString() {
        return string;
    }
    public void setString(String string) {
        this.string = string;
    }
}