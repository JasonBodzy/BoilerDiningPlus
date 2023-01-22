package com.company;

import java.net.ServerSocket;
import java.net.Socket;

/*
 * Uses port 4242 to connect to the server
 */

public class ServerManager extends Thread {
    // Current Data
    public Data currentData;
    public static void main(String[] args) {
        try {
            while (true) {
                ServerSocket serverSocket = new ServerSocket(4242);
                Socket socket = serverSocket.accept();
                DC_Server server = new DC_Server(socket);
                server.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Data getCurrentData() {
        return currentData;
    }

    public void setCurrentData(Data data) {
        this.currentData = data;
    }
}
