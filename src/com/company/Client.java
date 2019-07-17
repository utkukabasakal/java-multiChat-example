package com.company;
import java.io.*;
import java.net.*;
import java.util.*;
public class Client {
    private Socket socket;
    private BufferedReader cin ;
    private  BufferedReader cin2 ;
    private PrintWriter printWriter ;
    private String message = null;
    private String userName = null;
    public  Client (String userName){
        this.setUserName(userName);
        start();
    }
    public void start() {
        try {
            setSocket(new Socket("localhost",5550));
            setCin(new BufferedReader(new InputStreamReader(getSocket().getInputStream())));
            setCin2(new BufferedReader(new InputStreamReader(System.in)));
            setPrintWriter(new PrintWriter(getSocket().getOutputStream()));
            getPrintWriter().println(getUserName());
            getPrintWriter().flush();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        write();
    }
    public void write() {
        System.out.println("mesaj girmeye baslayabilirsiniz ");
        try{
            Thread thread=new Thread(new ClientThread(getCin(), getUserName()));
            thread.start();
            while (true) {
                setMessage(getCin2().readLine());
                System.out.print("");
                getPrintWriter().println(getUserName() +"  : "+ getMessage());
                getPrintWriter().flush();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try{
            getSocket().close();
            getPrintWriter().close();
            getCin().close();
            getCin2().close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main (String [] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bağlantı kuruldu.");
        System.out.print("isim giriniz :");
        String name =scanner.nextLine();
        Client client = new  Client(name);
    }
    public Socket getSocket() {
        return socket;
    }
    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    public BufferedReader getCin() {
        return cin;
    }
    public void setCin(BufferedReader cin) {
        this.cin = cin;
    }
    public BufferedReader getCin2() {
        return cin2;
    }
    public void setCin2(BufferedReader cin2) {
        this.cin2 = cin2;
    }
    public PrintWriter getPrintWriter() {
        return printWriter;
    }
    public void setPrintWriter(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
}