package src.views;
import src.core.*;
import src.models.User;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainScreenView extends SubPanel implements ActionListener {

    private JLabel jlWelcomeMessage;

    public MainScreenView(MainPanel parent, String panel_name) {
        super(parent, panel_name);
        setVisible(false);

        jlWelcomeMessage = new JLabel("", JLabel.CENTER);
        add(jlWelcomeMessage);
    }

    @Override
    public void actionPerformed (ActionEvent e){

    }

    @Override
    public void onFocus() {
        jlWelcomeMessage.setText("Hello, Welcome " + User.getUsername());

        System.out.println("Light: " + User.getLight());
        System.out.println("Temperature: " + User.getTemperature());
        System.out.println("PLaylistId: " + User.getPlaylistID());
    }

}

