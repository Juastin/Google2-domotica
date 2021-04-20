package src.views;
import src.core.*;
import src.components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// Gebruikergegevens moeten hier worden meegenomen van selecteren profiel
public class LoginView extends SubPanel implements ActionListener{

    private JPasswordField jbPassword;
    private JLabel labelPassword;
    private JButton jbOk, jbTerug;

    public LoginView(MainPanel parent, String panel_name) {
        super(parent, panel_name);
        setVisible(false);

        jbPassword = new JPasswordField(20);

        jbOk = new CButton(this, "Ok", Color.black, Color.white);

        jbTerug = new JButton("< Terug");
        jbTerug.addActionListener(this);
        jbTerug.setOpaque(false);
        jbTerug.setContentAreaFilled(false);
        jbTerug.setBorderPainted(false);

        labelPassword = new JLabel("Wachtwoord", JLabel.CENTER);
        add(labelPassword);
        add(jbPassword);
        add(jbOk);
        add(jbTerug);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbOk) {
            String wachtwoord = new String(jbPassword.getPassword());
            //communicatie met db voor later
            if (wachtwoord.equals("Hallo!3243")) {
                System.out.println("Gelukt");
            } else {
                System.out.println("Mislukt");
            }
        } else if (e.getSource() == jbTerug) {
            changeFocus("ProfileView");
        }
        Audio.play("../resources/click.wav");
    }

}

