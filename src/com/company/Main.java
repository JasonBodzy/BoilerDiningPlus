package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Main extends JComponent implements Runnable{
    JButton fordButton;
    JButton hillyButton;
    JButton wileyButton;
    JButton earhartButton;
    JButton windsorButton;

    public Main() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

            }
        });

    }
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Main());
        JFrame frame = new JFrame();
        frame.setTitle("PurdueDiningPlus");

    }
    public void run()
    {
        JFrame frame = new JFrame("Purdue Dining Plus");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        Main main = new Main();

        JPanel panel = new JPanel();

        content.add(main, BorderLayout.CENTER);
        content.add(main);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        fordButton = new JButton("Ford");
        panel.add(fordButton);
        content.add(panel, BorderLayout.NORTH);

        hillyButton = new JButton("Hillenbrand");
        panel.add(hillyButton);
        content.add(panel, BorderLayout.NORTH);

        wileyButton = new JButton("Willey");
        panel.add(wileyButton);
        content.add(panel, BorderLayout.NORTH);

        earhartButton = new JButton("Earhart");
        panel.add(earhartButton);
        content.add(panel, BorderLayout.NORTH);

        windsorButton = new JButton("Windsor");
        panel.add(windsorButton);

        fordButton.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ){

            }
        } );
        hillyButton.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ){

            }
        } );
        wileyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        earhartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        windsorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

}