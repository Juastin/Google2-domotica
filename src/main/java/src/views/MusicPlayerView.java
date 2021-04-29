package src.views;
import src.core.Container;
import src.core.View;
import src.core.Navbar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class MusicPlayerView extends View {

    public MusicPlayerView(Container container, String name) {
        super(container, name);
        setLayout(new BorderLayout());
        setVisible(false);

        // NAVBAR
        Navbar navbar = new Navbar(this);

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