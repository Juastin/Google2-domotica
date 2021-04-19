package src.views;
import src.core.*;
import src.components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;

public class MenuView extends SubPanel implements ActionListener {
    private JButton jbStart;
    private JLabel jlKies;

    public MenuView(MainPanel parent, String panel_name) {
        super(parent, panel_name);
        setLayout(new BorderLayout());
        setVisible(false);

        JPanel top = new JPanel();
        top.setBorder(BorderFactory.createEmptyBorder(50, 0, 10, 0));
        JPanel center = new JPanel();
        JPanel bottom = new JPanel();

        jlKies = new JLabel("Kies uw profiel:");
        jlKies.setFont (jlKies.getFont().deriveFont(24.0f));
        jbStart = new CButton(this, "+", Color.black, Color.white);

        top.add(jlKies);
        add(top, BorderLayout.NORTH);

        // Hier moet uiteindelijk de methode worden aangeroepen waarmee de gebruikers worden opgehaald
        ArrayList<String> gebruikers = new ArrayList<String>();
        Collections.addAll(gebruikers, "Bram", "Justin", "Cindy", "Jonathan", "Storm", "Camiel");
        for (String naam: gebruikers) {
            center.add(new CButton(this, naam, Color.black, Color.white));
        }
        add(center, BorderLayout.CENTER);

        bottom.add(jbStart);
        add(bottom, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbStart) {
            changeFocus("ImageView");
        }
        Audio.play("../resources/click.wav");
    }

}
