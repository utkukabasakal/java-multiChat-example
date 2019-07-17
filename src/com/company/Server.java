package com.company;
import java.net.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    protected ArrayList<ServerThread> threadsArray = new ArrayList<ServerThread>();
    ExecutorService pool = null;
    private Socket socket = null;
    private ServerSocket serverSocket = null;
    private int port;
    Server(int port){

        this.port=port;
        pool = Executors.newFixedThreadPool(5);
    }
    public Server() {
        try {
            serverSocket = new ServerSocket(5550);
            System.out.println("Bağlantı bekleniyor ...");
            while (true) {
                socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket,this);
                threadsArray.add(serverThread); //pool.execute(serverThread);
                serverThread.start();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try{
            serverSocket.close();
        }
        catch (Exception e) { e.printStackTrace();
        }
    }
    public static void main(String []args) {
        Server mserver = new Server();
    }
}