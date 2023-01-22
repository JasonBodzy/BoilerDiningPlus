package com.company;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.SimpleFormatter;

public class DC_Worker implements Runnable{

    static int swipes;
    static int masterSwipes;
    static String court;
    static boolean connected = false;
    static int crowded;

    private static Socket clientSocket;

    public static void main(String[] args) {

        protocol();
        SwingUtilities.invokeLater(new DC_Worker());

        SimpleDateFormat formatter = new SimpleDateFormat("ss");
        SimpleDateFormat mainFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        boolean printed = false;
        while (true) {
            Date date = new Date();
            if(formatter.format(date).equalsIgnoreCase("00") && !printed) {
                System.out.println("Minute!");
                System.out.println(swipes);
                writeToServer(new Data(swipes, crowded, mainFormatter.format(date), court, true ), clientSocket);
                swipes = 0;
                printed = true;
            } else if (formatter.format(date).equalsIgnoreCase("01")) {
                printed = false;
            }
        }

    }

    public static void protocol() {
        try {
            clientSocket = new Socket("localhost", 4242);

            PrintWriter clientWriter = new PrintWriter(clientSocket.getOutputStream());
            clientWriter.write('1');
            connected = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeToServer(Data data, Socket socket) {
        if (connected) {
            try {
                PrintWriter clientWriter = new PrintWriter(socket.getOutputStream());
                clientWriter.write(data.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void run() {
        String[] courts = {"Ford", "Wiley", "Windsor", "Hillenbrand", "Earhart"};
        court  = courts[JOptionPane.showInternalOptionDialog(null, "Which dining court are you working in?",
                "Purdue Dining Courts", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, courts, null)];

        JFrame frame = new JFrame();
        frame.setSize(400, 400);

        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 's') {
                    swipes++;
                    masterSwipes++;
                    if (masterSwipes % 15 == 0) {
                        String[] options = {"Very Busy: 35+", "Busy: 15-30",  "Mild: 5-15", "Light: <5"};
                        int choice = JOptionPane.showInternalOptionDialog(null, "Please select how busy it currently is",
                                "Purdue Dining Courts", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);

                        switch (choice) {
                            case 0:
                                crowded = 100;
                                break;
                            case 1:
                                crowded = ThreadLocalRandom.current().nextInt(15, 31);
                                break;
                            case 2:
                                crowded = ThreadLocalRandom.current().nextInt(5, 16);
                                break;
                            case 3:
                                crowded = 0;
                                break;
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };

        frame.addKeyListener(keyListener);
        frame.setVisible(true);
    }
}
