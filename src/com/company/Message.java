package com.company;
import java.net.*;
import java.io.*;
public interface Message  {
    public void getMessage(BufferedReader in) throws IOException;
    public void send(String message,PrintWriter printWriter);
    public void sendMessage(Socket socket,BufferedReader sin,BufferedReader stdin,PrintWriter printWriter, Server server,int count,String userName);
}