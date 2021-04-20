package src.views;
import src.core.*;
import src.components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ImageView extends SubPanel implements ActionListener {
    private JButton jbBack, jbSave;
    private JTextField jtTest;

    public ImageView(MainPanel parent, String panel_name) {
        super(parent, panel_name);
        setVisible(false);

        jtTest = new JTextField(13);
        jbSave = new CButton(this, "opslaan", Color.black, Color.white);
        jbBack = new CButton(this, "terug naar hoofdmenu", Color.black, Color.white);
        
        add(new JLabel("Vul hier je naam in:"));
        add(jtTest);
        add(jbSave);
        add(jbBack);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbBack) {
            changeFocus("ProfileView");
        } else if (e.getSource() == jbTest) {
            JOptionPane.showMessageDialog(this, "Wow '" + jtTest.getText() + "' is echt een coole naam.");
        }
        Audio.play("../resources/click.wav");
    }

}
