package google2_domotica.views;
import google2_domotica.core.*;
import google2_domotica.components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ImageView extends SubPanel implements ActionListener {
    private JButton jbStart, jbTest;
    private JTextField jtTest;

    public ImageView(MainPanel parent, String panel_name) {
        super(parent, panel_name);
        setVisible(false);

        jtTest = new JTextField(13);
        jbTest = new CButton(this, "opslaan", Color.black, Color.white);
        jbStart = new CButton(this, "terug naar hoofdmenu", Color.black, Color.white);
        
        add(new JLabel("Vul hier je naam in:"));
        add(jtTest);
        add(jbTest);
        add(jbStart);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbStart) {
            changeFocus("MenuView");
        } else if (e.getSource() == jbTest) {
            JOptionPane.showMessageDialog(this, "Wow '" + jtTest.getText() + "' is echt een coole naam.");
        }
        Audio.play("../resources/click.wav");
    }

}
