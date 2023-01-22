
package com.company;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DC_Server extends ServerManager implements Runnable {
    private Socket socket;
    private Data currentData;
    DC_Server(Socket socket) {
        this.socket = socket;
        this.currentData = super.getCurrentData();
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
                            // constantly reading data
                            Data data = (Data) objectInputStream.readObject();
                            super.setCurrentData(data);

                            // When DC closes end connection protocol
                            if (!data.isOpen) {
                                break;
                            }
                        }

                    } else {
                        boolean written = false;
                        while (true) {
                            Date date = new Date();
                            SimpleDateFormat formatter = new SimpleDateFormat("ss");
                            // Every minute
                            if(formatter.format(date).equalsIgnoreCase("00") && !written) {
                                // Write new data object to client every minute
                                ObjectOutputStream clientWriter = new ObjectOutputStream(socket.getOutputStream());
                                clientWriter.writeObject(super.getCurrentData());
                                clientWriter.flush();
                                written = true;
                            } else if (formatter.format(date).equalsIgnoreCase("01")) {
                                written = false;
                            }
                        }

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
