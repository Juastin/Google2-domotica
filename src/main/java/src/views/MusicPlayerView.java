package src.views;
import src.core.*;
import src.core.MainPanel;
import src.core.SubPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class MusicPlayerView extends SubPanel {

    public MusicPlayerView(MainPanel parent, String panel_name) {
        super(parent, panel_name);
        setLayout(new BorderLayout());
        setVisible(false);

        // NAVBAR
        NavbarPanel navbar = new NavbarPanel(this);

        // Main Panel
        JPanel main = new JPanel();
        main.add(new JLabel("Testfase Muziekspeler"));

        add(main, BorderLayout.CENTER);
        add(navbar, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void onFocus() {}

    @Override
    public void onShadow() {}

    @Override
    public void onTick(long now) {}
    
}