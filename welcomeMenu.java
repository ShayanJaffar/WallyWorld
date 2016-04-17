package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class welcomeMenu extends JFrame implements ActionListener {
    // row 1
private String uN;
private String pass;
// row 1
JPanel row1 = new JPanel();
JLabel usernameLabel = new JLabel("Username:");
JTextField username = new JTextField();
// row 2
JPanel row2 = new JPanel();
JLabel passwordLabel = new JLabel("Password:");
JPasswordField password = new JPasswordField(10);
// row 3
JPanel row3 = new JPanel();
JButton ok = new JButton("Ok");
JButton cancel = new JButton("Cancel");

public welcomeMenu() {
    super("Employee Login");

    setSize(500,400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLookAndFeel();
    GridLayout layout1 = new GridLayout(8, 10, 10, 10);
    setLayout(layout1);

    //row 1
    BorderLayout layout2 = new BorderLayout();
    row1.setLayout(layout2);
    usernameLabel.setLabelFor(username);
    row1.add(usernameLabel, BorderLayout.WEST);
    row1.add(username, BorderLayout.CENTER);
    add(row1);
    // row 2
    BorderLayout layout3 = new BorderLayout();
    row2.setLayout(layout3);
    passwordLabel.setLabelFor(password);
    row2.add(passwordLabel, BorderLayout.WEST);
    row2.add(password, BorderLayout.CENTER);
    add(row2);
    // row 3
    FlowLayout layout4 = new FlowLayout(FlowLayout.CENTER, 10, 10);
    row3.setLayout(layout4);
    row3.add(ok);
    row3.add(cancel);
    add(row3);

    ok.addActionListener(this);
    cancel.addActionListener(this);

    setVisible(true);

}
public String getUN(){
    return uN;
}

public String getPassword(){
    return pass;
}

@Override
public void actionPerformed(ActionEvent event) {
    if(event.getSource() == ok) {
        uN = username.getText();
        pass = String.valueOf(password.getPassword());
    }
    else if(event.getSource() == cancel){
        System.exit(0);
    }

}

public void setLookAndFeel() {
    try {UIManager.setLookAndFeel(
    "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
    } catch(Exception e) {

    }
}

/*public static void main(String[] args) {
    new ManagerMenuLogin();
}*/
}