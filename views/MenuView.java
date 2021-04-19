package google2_domotica.views;
import google2_domotica.core.*;
import google2_domotica.components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuView extends SubPanel implements ActionListener {
    private JButton jbStart;

    public MenuView(MainPanel parent, String panel_name) {
        super(parent, panel_name);
        setLayout(new BorderLayout());
        setVisible(false);

        JPanel bottom = new JPanel();
        bottom.setBackground(new Color(200, 200, 200));

        jbStart = new CButton(this, "✅ Naam checker", Color.black, Color.white);
        
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
