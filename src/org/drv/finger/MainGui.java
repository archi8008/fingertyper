package org.drv.finger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainGui extends JFrame {
    //Combobox output
    final static String[] languages = { "English", "Deutsch", "Java" };
    final static String[] mode = { "Text", "Random"};
    final static String[] add = { "Ponctuation", "Numbers", "Symbols"};
    final static String[] time = { "15s", "30s", "60s"};
    final static String[] words = { "25", "50", "100"};

    public static JTextArea outfield = new JTextArea();
    public static JTextArea infield = new JTextArea();


    private static int inputTime = 0;
    private static Timer timer;


    public static void main (String[] args) {

        //Databank inout imitation
        String text = "Some text;\n" +
                "I dont know what it is.";
//        List<Character> trueText = new ArrayList<>();
//        for (char c : text.toCharArray()) {
//            trueText.add(c);
//        }
//
//
//        StringBuilder sb = new StringBuilder();
//        for (char c : trueText) {
//            sb.append(c);
//        };
        //Main Frame and panel
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 850);
        //JPanel mpanel = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout());
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 20));
        panel.setBackground(new Color(72, 72, 72));

        //BUTTONS
        //Start time
        JButton startButton = new JButton("START");
        startButton.setBounds(750, 10, 100,20);
        startButton.setBackground(new Color(128,123,123));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputTime = 0;
                timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        inputTime++;
                    }
                });
                timer.start();
            }
        });

        //End timer and Score Frame open
        JButton endButton = new JButton("END");
        endButton.setBounds(750, 40, 100,20);
        endButton.setBackground(new Color(100,71,71));
        // move to score menu
        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScoreGUI.score();
                System.out.println("time is: " + inputTime);

                timer.stop();
            }

        });
//        endButton.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_END){
//                    System.out.println("time is: " + inputTime);
//                    timer.stop();
//                }
//            }
//        });

        JButton accountButton = new JButton("Account");
        accountButton.setBounds(750, 70, 100,20);
        accountButton.setBackground(new Color(128,123,123));

        //Text Fields
        //Output field, where Databank outputs the content
        outfield.setBounds(34,100,900,350);
        outfield.setBackground(new Color(171,171,171));
        outfield.setFont(new Font("Serif",Font.BOLD,20));
        outfield.setLineWrap(true);
        outfield.setText(text);

        //
        //String[] temp;
        //User input field
        infield.setBounds(34,450,900,350);
        infield.setBackground(new Color(82,82,82));
        infield.setFont(new Font("Serif",Font.BOLD,20));
        infield.setLineWrap(true);
        infield.getDocument().addDocumentListener(new TextListener(outfield, infield));
         //oracle.com   //

        // TRUE FALSE INPUT;
        //Main Text to char transform
//        char[] charArray = text.toCharArray();
//        List<Character> trueText = new ArrayList<>();
//        for (char c : charArray) {
//            trueText.add(c);
//        }

        //compare between in and out;


        //Adding
        panel.add(cBox(languages, "language"));
        panel.add(cBox(mode, "mode"));
        panel.add(cBox(add, "add"));
        panel.add(cBox(time, "time"));
        panel.add(cBox(words, "words"));

        frame.add(outfield);
        frame.add(infield);
        frame.add(startButton);
        frame.add(endButton);
        frame.add(accountButton);



        frame.add(panel);

        frame.setVisible(true);

    }
    //Combobox create function
    public static JComboBox<String> cBox(String[] items, String defaul){

        JComboBox<String> comboBox = new JComboBox(items);
        comboBox.setBounds(520,120,200,30);
        comboBox.setBackground(new Color(128,123,123));

        comboBox.insertItemAt(defaul, 0);
        comboBox.setSelectedItem(defaul);

        ///////////////

        //////////////

        return comboBox;



    }

    public static int getInputTime() {
        return inputTime;
    }


}


