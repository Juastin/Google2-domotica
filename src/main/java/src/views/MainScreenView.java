package src.views;
import src.components.CButton;
import src.core.*;
import src.system.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainScreenView extends SubPanel implements ActionListener {

    private JLabel jlWelcomeMessage;
    private JButton jbLogOut;

    public MainScreenView(MainPanel parent, String panel_name) {
        super(parent, panel_name);
        setLayout(new BorderLayout());
        setVisible(false);

        // Main Panel
        JPanel main = new JPanel();
        main.setLayout(new GridLayout(2, 2));

        jlWelcomeMessage = new JLabel("", JLabel.CENTER);
        add(jlWelcomeMessage);

        jbLogOut = new CButton(this, "Afmelden", Color.black, Color.white);
        add(jbLogOut);
    }

    @Override
    public void actionPerformed (ActionEvent e){
        if (e.getSource() == jbLogOut) {
            User.logOut();
            changeFocus("ProfileView");
            JOptionPane.showMessageDialog(this, "U bent afgemeld");
        }
    }

    @Override
    public void onFocus() {
        jlWelcomeMessage.setText("Gebruiker: " + User.getUsername());

        System.out.println("Light: " + User.getLight());
        System.out.println("Temperature: " + User.getTemperature());
        System.out.println("PLaylistId: " + User.getPlaylistID());
    }

}

