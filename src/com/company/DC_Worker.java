package com.company;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class DC_Worker implements Runnable{

    static int swipes;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new DC_Worker());

        SimpleDateFormat formatter = new SimpleDateFormat("ss");
        boolean printed = false;
        while (true) {
            Date date = new Date();
            if(formatter.format(date).equalsIgnoreCase("00") && !printed) {
                System.out.println("Minute!");
                System.out.println(swipes);
                swipes = 0;
                printed = true;
            } else if (formatter.format(date).equalsIgnoreCase("01")) {
                printed = false;
            }
        }

    }


    @Override
    public void run() {
        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 's') {
                    Date date = new Date();
                    System.out.println(formatter.format(date));
                    swipes++;
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
