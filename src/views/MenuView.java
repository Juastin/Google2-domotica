package src.views;
import src.core.*;
import src.components.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;

public class MenuView extends SubPanel implements ActionListener {
    private JButton jbNieuwProfiel;
    private JLabel jlKies;

    public MenuView(MainPanel parent, String panel_name) {
        super(parent, panel_name);
        setLayout(new BorderLayout());
        setVisible(false);

        JPanel top = new JPanel();
        top.setBorder(BorderFactory.createEmptyBorder(50, 0, 10, 0));
        JPanel center = new JPanel();
        JPanel bottom = new JPanel();
        bottom.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        jlKies = new JLabel("Kies uw profiel:");
        jlKies.setFont(jlKies.getFont().deriveFont(24.0f));
        jbNieuwProfiel = new CButton(this, "＋", Color.black, Color.white);

        top.add(jlKies);
        add(top, BorderLayout.NORTH);

        // Hier moet uiteindelijk de methode worden aangeroepen waarmee de gebruikers worden opgehaald
        ArrayList<String> gebruikers = new ArrayList<String>();
        Collections.addAll(gebruikers, "Bram", "Justin", "Cindy", "Jonathan", "Storm", "Camiel");
        JPanel userGrid = new JPanel();
        GridLayout userGridLayout = new GridLayout((gebruikers.size()/3), 3);
        userGridLayout.setHgap(10); userGridLayout.setVgap(10);
        userGrid.setLayout(userGridLayout);
        for (String naam: gebruikers) {
            CButton button = new CButton(this, naam, Color.black, Color.white);
            button.setFont(new Font(button.getFont().getFamily(), Font.PLAIN, 16));
            userGrid.add(button);
        }
        center.add(userGrid);
        add(center, BorderLayout.CENTER);

        bottom.add(new JLabel("Profiel toevoegen"));
        bottom.add(jbNieuwProfiel);
        add(bottom, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbNieuwProfiel) {
            changeFocus("ImageView");
        }
        Audio.play("../resources/click.wav");
    }

}
