package com.company;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;

public class DC_Server extends Thread implements Runnable {
    private Socket socket;

    DC_Server(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            if (socket.isConnected()) {
                while (socket.isConnected()) {
                    int message = reader.read();
                    if (message == 1) {
                        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                        while (true) {
                            Data data = (Data) objectInputStream.readObject();
                        }

                    } else {
                        writer.println();
                        writer.flush();
                    }
                }
            }
            writer.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}