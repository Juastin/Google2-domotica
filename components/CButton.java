package google2.domotica.components;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;

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
