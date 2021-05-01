package src.components;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

/* MusicButton

An extension of JButton, created for the MusicPlayerView to:
+ Immediately give style
+ Immediately give an ActionListener
+ Customize your button style in different states

*/

public class MusicButton extends JButton implements ChangeListener {
    private Color buttonChange = new Color(80, 80, 80);

    public MusicButton(ActionListener parent, String text) {
        super(text);
        setForeground(Color.BLACK);
        setFont(new Font(this.getFont().getFamily(), Font.PLAIN, 40));
        setBorder(null);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        addActionListener(parent);
        addChangeListener(this);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (this.model.isPressed()) {
            this.setForeground(Color.BLACK);
        }
        else if (this.model.isRollover()) {
            this.setForeground(buttonChange);
        }
        else {
            this.setForeground(Color.BLACK);
        }
    }
}
