package src.components;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;

/* CButton

An extention of JButton, created to:
+ Immediately give style
+ Immediately give an ActionListener

Decreases the amount of code by atleast 5 lines.

Example usage (in which 'this' is the ActionListener):
CButton button = new CButton(this, "click me", Color.black, Color.white);

*/

public class CButton extends JButton {

    public CButton(ActionListener parent, String text, Color fg, Color bg) {
        super(text);
        setBorderPainted(false);
        setFocusPainted(false);
        setForeground(fg);
        setBackground(bg);
        addActionListener(parent);
    }

    public CButton(ActionListener parent, String text, Color fg) {
        this(parent, text, fg, new Color(0f,0f,0f,0f));
    }
    
    public CButton(ActionListener parent, String text) {
        this(parent, text, new Color(0f,0f,0f,0f));
    }

}
