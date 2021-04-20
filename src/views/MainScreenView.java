package src.views;
import src.core.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainScreenView extends SubPanel implements ActionListener {

    public MainScreenView(MainPanel parent, String panel_name) {
        super(parent, panel_name);
        setVisible(false);

        add(new JLabel("Hello, Welcome!", JLabel.CENTER));
    }

    @Override
    public void actionPerformed (ActionEvent e){

    }

}

