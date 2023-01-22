package com.company;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;
/*
 * Uses port 4242 to connect to the server
 */
public class DC_Server {
    public static void main(String[] args) throws IOException{
        ServerSocket   serverSocket = new ServerSocket(4242);
        Socket      socket = serverSocket.accept();
        BufferedReader      reader = new BufferedReader( new InputStreamReader( socket.getInputStream()));
        PrintWriter writer = new PrintWriter( socket.getOutputStream());
        if(socket.isConnected()){
            while (socket.isConnected()) {
                int message = reader.read();
                if (message == 1){


                }
                else {
                    writer.println();
                    writer.flush();
                }
            }
        }
        writer.close();
        reader.close();

    }
}
