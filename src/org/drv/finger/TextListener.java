package org.drv.finger;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import javax.swing.JTextArea;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TextListener implements DocumentListener {

    private final JTextArea outfield;
    public JTextArea infield;
    public int i = 0;
    public int incorrect; //Incorrect input Count
    private int previousIncorrect;
    private String userInput;

    public void setIncorrectImport(int incorrectImport) {
        this.incorrectImport = incorrectImport;
    }

    private int incorrectImport;
    public TextListener( JTextArea outfield, JTextArea infield) {
        //this.textArea = textArea;

        this.outfield = outfield;
        this.infield = infield;
        this.incorrect = 0;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        colorTextChange();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        colorTextChange();

    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        colorTextChange();
    }

    public void colorTextChange() {
        String mainText = outfield.getText();
        userInput = infield.getText();

        incorrect = 0;
        Highlighter highlighter = outfield.getHighlighter();
        highlighter.removeAllHighlights();
        //charList.clear();
        //String text = textArea.getText();

        for (i = 0; i < userInput.length(); i++) {
           if (i >= mainText.length())
               break;
           //If false then color is red
           if (userInput.charAt(i) != mainText.charAt(i)){
               incorrect++;
               try {
                   highlighter.addHighlight(i, i + 1 /*paint one char to red*/, new DefaultHighlighter.DefaultHighlightPainter(Color.RED));  //https://stackoverflow.com/questions/68337928/how-to-highlight-words-using-java-highlighter
               } catch (BadLocationException ex) {
                   ex.printStackTrace();
               }
           }
            if (userInput.charAt(i) == mainText.charAt(i)){

                try {
                    highlighter.addHighlight(i, i + 1 /*paint one char to red*/, new DefaultHighlighter.DefaultHighlightPainter(Color.GRAY));  //https://stackoverflow.com/questions/68337928/how-to-highlight-words-using-java-highlighter
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        }
        System.out.println(incorrectImport);
        if (incorrect > previousIncorrect) {
            incorrectImport += 1;

        }
        System.out.println(incorrectImport);
        previousIncorrect = incorrect;


        //System.out.println(charList);
    }

    public int getIncorrectImport() {
        return incorrectImport;

    }
}