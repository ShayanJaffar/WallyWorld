package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.BorderLayout;
import java.io.PrintStream;

@SuppressWarnings("serial")
public class ScheduleWindow extends JPanel{
    private JTextArea textArea = new JTextArea(50, 75);
    private TextAreaOutputStream taOutputStream = new TextAreaOutputStream(
        textArea, "Schedule:");
        
    public ScheduleWindow(){
        setLayout(new BorderLayout());
        add(new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
        System.setOut(new PrintStream(taOutputStream));
        
    }
    
    public static void createAndShowGui() {
      JFrame frame = new JFrame("Schedule:");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(new ScheduleWindow());
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }
}

