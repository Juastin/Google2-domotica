package src.views;

import src.core.Container;
import src.core.Navbar;
import src.core.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/*
This screen is meant so that it won't interrupt the other processes
of the domotica system while playing the game.
 */

public class GameScreenView extends View {
    public GameScreenView(Container container, String name) {
        super(container, name);
        setVisible(false);
        setLayout(new BorderLayout());
        setBackground(new Color(60, 30, 30));

        // NAVBAR
        Navbar navbar = new Navbar(this);

        add(navbar, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void onFocus(ArrayList<String> parameters) {

    }

    @Override
    public void onShadow() {

    }

    @Override
    public void onTick(long now) {

    }
}
