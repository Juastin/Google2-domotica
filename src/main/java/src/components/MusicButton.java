package src.components;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

/* MusicButton
An extension of JButton, created for the MusicPlayerView to:
+ Immediately give style
+ Immediately give an ActionListener
+ Customize your button style in different states
*/

public class MusicButton extends JButton implements ChangeListener {
    private Color buttonChange = new Color(80, 80, 80);

    public MusicButton(ActionListener parent, String text, int fontSize) {
        super(text);
        setForeground(Color.BLACK);
        setFont(new Font(this.getFont().getFamily(), Font.PLAIN, fontSize));
        setBorder(null);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        addActionListener(parent);
        addChangeListener(this);
    }

    public MusicButton(String text, int fontSize) {
        this(null, text, fontSize);
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