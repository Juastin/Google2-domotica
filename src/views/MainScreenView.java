package src.views;

import src.components.CButton;
import src.core.*;
import src.system.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreenView extends SubPanel implements ActionListener {

    private JLabel jlWelcomeMessage;
    private JButton jbLogOut, jbSettings;

    public MainScreenView(MainPanel parent, String panel_name) {
        super(parent, panel_name);
        setVisible(false);

        jlWelcomeMessage = new JLabel("", JLabel.CENTER);
        add(jlWelcomeMessage);

        jbLogOut = new CButton(this, "Afmelden", Color.black, Color.white);
        add(jbLogOut);

        jbSettings = new CButton(this, "Settings", Color.black, Color.white);
        add(jbSettings);
    }

    @Override
    public void actionPerformed (ActionEvent e){
        if (e.getSource() == jbLogOut) {
            User.logOut();
            changeFocus("ProfileView");
            JOptionPane.showMessageDialog(this, "U bent afgemeld");
        } else if (e.getSource() == jbSettings) {
            changeFocus("PersonalSettingsView");
        }
    }

    @Override
    public void onFocus() {
        jlWelcomeMessage.setText("Gebruiker: " + User.getUsername());
    }

}

