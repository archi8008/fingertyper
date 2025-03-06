package org.drv.finger;

import javax.swing.*;
import java.awt.*;

public class ScoreGUI {


    public static void score(){
        JTextArea outfield = MainGui.outfield;
        JTextArea infield = MainGui.infield;

        String userInput = infield.getText();

        //Show incorrect Count
        TextListener h = new TextListener(outfield, infield);
        infield.getDocument().addDocumentListener(h);
        h.colorTextChange(); //Refresh Count
        int incorrect = h.getIncorrect();
        System.out.println("dddddddd" + incorrect);

        //Words and Symbols per Second
        // int totalCharactersTyped = h.infield;
        int totalTimeSeconds = MainGui.getInputTime();
        int spm = (userInput.length() / totalTimeSeconds) * 60;
        int wpm = spm / 5;

        JFrame frame = new JFrame();
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 850);
        frame.getContentPane().setBackground(new Color(72, 72, 72));


        JPanel panel = new JPanel(new BorderLayout());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(72, 72, 72));

        JLabel scoreLabel = new JLabel("SCORE",SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Serif",Font.BOLD,40));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // ~KI~



        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
        scorePanel.setBackground(new Color(72, 72, 72));

        scorePanel.add(createSquarePanel("SPM:  " + spm +"<br>WPM: " + wpm));
        scorePanel.add(createSquarePanel("Incorrect: " + incorrect));
        scorePanel.add(createSquarePanel("Time: " + totalTimeSeconds + "s"));

        panel.add(Box.createVerticalStrut(20)); // Отступ сверху
        panel.add(scoreLabel);
        panel.add(Box.createVerticalStrut(70)); // Отступ между текстом и квадратами
        panel.add(scorePanel);

        frame.add(panel);

        frame.setVisible(true);
    }

    //Create Square with text
    private static JPanel createSquarePanel(String text) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(166,166,166));
        panel.setPreferredSize(new Dimension(200, 100)); //https://stackoverflow.com/questions/1594423/setting-the-size-of-panels
        panel.setLayout(new GridBagLayout()); // Для центрирования текста

        JLabel label = new JLabel("<html>" + text + "</html>", SwingConstants.CENTER); // AI support
        panel.add(label);
        return panel;
    }
}