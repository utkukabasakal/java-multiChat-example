package com.company;
import java.io.*;
public class ClientThread implements Runnable {
    private BufferedReader in;
    private String userName;
    public ClientThread(BufferedReader in,String userName){
        this.setIn(in);
        this.setUserName(userName);
    }
    public void run() {
        Message message=new MessageSettings();
        try {
            message.getMessage(getIn());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public BufferedReader getIn() {
        return in;
    }
    public void setIn(BufferedReader in) {
        this.in = in;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
}