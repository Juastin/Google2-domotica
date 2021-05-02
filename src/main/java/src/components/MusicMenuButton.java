package src.components;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

/* MusicMenuButton

An extension of JButton, created for the MusicPlayerMenuView to:
+ Immediately give style
+ Immediately give an ActionListener
+ Customize your button style in different states

*/

public class MusicMenuButton extends JButton{
    private Color defaultColor = new Color(250, 250, 250);
    private Color buttonChange = new Color(240, 240, 240);

    public MusicMenuButton(ActionListener parent, String text, int fontSize) {
        super(text);
        setForeground(Color.BLACK);
        setFont(new Font(this.getFont().getFamily(), Font.PLAIN, fontSize));
        setBorder(null);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        addActionListener(parent);
        setBackground(defaultColor);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (this.model.isPressed()) {
            g.setColor(defaultColor);
        }else if (this.model.isRollover()) {
            g.setColor(buttonChange);
        }
        else {
            g.setColor(defaultColor);
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}
