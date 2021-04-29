package src.views;

import src.core.MainPanel;
import src.core.SubPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MusicPlayerView extends SubPanel {

    public MusicPlayerView(MainPanel parent, String panel_name) {
        super(parent, panel_name);
        setVisible(false);

        add(new JLabel("Testfase Muziekspeler"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void onFocus() {

    }
}