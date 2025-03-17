package org.drv.finger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static org.drv.finger.MainGui.infield;
import static org.drv.finger.MainGui.outfield;

public class ScoreGUI {

    private static JTextArea input = new JTextArea();
    public static void score(){
        //Object transfer
        TextListener h = MainGui.getTextListener();
        //Incorrect input initialize
        int incorrect = h.getIncorrectImport();


        //Words and Symbols per Second
        // int totalCharactersTyped = h.infield;
        int totalTimeSeconds = MainGui.getInputTime();


        int spm = (MainGui.infield.getText().length() / totalTimeSeconds) * 60;
        int wpm = spm / 5;

        //Window create
        JFrame frame = new JFrame();
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 850);
        frame.getContentPane().setBackground(new Color(72, 72, 72));

        input.setBounds(10,10,2,2);
        input.requestFocusInWindow();
        InputMap inputMap = input.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = input.getActionMap();
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ecs");
        actionMap.put("ecs", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();

            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1)); // Две равные секции
        panel.setBackground(new Color(72, 72, 72));

// SCORE SECTION
        JPanel scoreSection = new JPanel();
        scoreSection.setLayout(new BoxLayout(scoreSection, BoxLayout.Y_AXIS));
        scoreSection.setBackground(new Color(72, 72, 72));

        JLabel scoreLabel = new JLabel("SCORE", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Serif", Font.BOLD, 60));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
        scorePanel.setBackground(new Color(72, 72, 72));
        scorePanel.add(createSquarePanel("SPM:  " + spm + "<br>WPM: " + wpm));
        scorePanel.add(createSquarePanel("Incorrect: " + incorrect + "<br>Correct: " + (infield.getText().length() - incorrect)));
        scorePanel.add(createSquarePanel("Time: " + MainGui.timers));

        scoreSection.add(Box.createVerticalStrut(20));
        scoreSection.add(scoreLabel);
        scoreSection.add(Box.createVerticalStrut(50));
        scoreSection.add(scorePanel);

// RECORD SECTION
        JPanel recordSection = new JPanel();
        recordSection.setLayout(new BoxLayout(recordSection, BoxLayout.Y_AXIS));
        recordSection.setBackground(new Color(72, 72, 72));

        JLabel recordLabel = new JLabel("RECORD", SwingConstants.CENTER);
        recordLabel.setFont(new Font("Serif", Font.BOLD, 60));
        recordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel recordPanel = new JPanel();
        recordPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
        recordPanel.setBackground(new Color(72, 72, 72));
        recordPanel.add(createSquarePanel("Best spm  " + spm));
        recordPanel.add(createSquarePanel("The least number of Incorrect: " + "<br>Best Correct: " ));
        recordPanel.add(createSquarePanel("Best Time: " ));

        recordSection.add(Box.createVerticalStrut(20));
        recordSection.add(recordLabel);
        recordSection.add(Box.createVerticalStrut(50));
        recordSection.add(recordPanel);

        panel.add(scoreSection);
        panel.add(recordSection);

        frame.add(panel);
        frame.setVisible(true);
    }

    //Create Square with text
    private static JPanel createSquarePanel(String text) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(166,166,166));
        panel.setPreferredSize(new Dimension(200, 100)); //https://stackoverflow.com/questions/1594423/setting-the-size-of-panels
        panel.setLayout(new GridBagLayout());

        JLabel label = new JLabel("<html>" + text + "</html>", SwingConstants.CENTER); // AI support
        panel.add(label);
        return panel;
    }
}