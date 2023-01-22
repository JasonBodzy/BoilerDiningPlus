package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main extends JComponent implements Runnable{

    private Data data;


    JButton fordButton;
    JButton hillyButton;
    JButton wileyButton;
    JButton earhartButton;
    JButton windsorButton;

    JLabel swipesLabel;
    JLabel openLabel;
    JLabel waitLabel;
    JLabel swipesLabel2;
    JLabel openLabel2;
    JLabel waitLabel2;


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

        JPanel panelButtons = new JPanel();
        JPanel panelLabels = new JPanel();
        JPanel panelLabels2 = new JPanel();

        content.add(main, BorderLayout.CENTER);
        content.add(main);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        fordButton = new JButton("Ford");
        panelButtons.add(fordButton);
        content.add(panelButtons, BorderLayout.NORTH);

        hillyButton = new JButton("Hillenbrand");
        panelButtons.add(hillyButton);
        content.add(panelButtons, BorderLayout.NORTH);

        wileyButton = new JButton("Willey");
        panelButtons.add(wileyButton);
        content.add(panelButtons, BorderLayout.NORTH);

        earhartButton = new JButton("Earhart");
        panelButtons.add(earhartButton);
        content.add(panelButtons, BorderLayout.NORTH);

        windsorButton = new JButton("Windsor");
        panelButtons.add(windsorButton);
        content.add(panelButtons, BorderLayout.NORTH);

        swipesLabel = new JLabel("Swipes:");
        panelLabels.add(swipesLabel);
        content.add(panelLabels, BorderLayout.CENTER);

        openLabel = new JLabel("Open:");
        panelLabels.add(openLabel);
        content.add(panelLabels, BorderLayout.CENTER);

        waitLabel = new JLabel("Wait:");
        panelLabels.add(waitLabel);
        content.add(panelLabels, BorderLayout.CENTER);

        swipesLabel2 = new JLabel(String.valueOf(data.swipes));
        panelLabels2.add(swipesLabel2);
        content.add(panelLabels2, BorderLayout.CENTER);

        openLabel2 = new JLabel(String.valueOf(data.isOpen));
        panelLabels2.add(openLabel2);
        content.add(panelLabels2, BorderLayout.CENTER);

        waitLabel2 = new JLabel(String.valueOf(data.wait));
        panelLabels2.add(waitLabel2);
        content.add(panelLabels2, BorderLayout.CENTER);

        /* connect to socket       */
        /* if failure exit program */

        Socket socket;

        try {
            socket = new Socket("localhost", 4242);
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null, "This host does not exist", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "I/O Error in connection.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        /* get data from server */

        try {
            ObjectInputStream clientReader = new ObjectInputStream(socket.getInputStream());
            data = (Data) clientReader.readObject();
        } catch (IOException e) {
            //end program when there is an I/O exception
            JOptionPane.showMessageDialog(null, "I/O Error.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Class Not Found Error.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

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