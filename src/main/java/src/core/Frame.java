package src.core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Frame extends JFrame {
    private Container container;
    
    public Frame() {
        setSize(946, 540);
        setTitle("Google2: MainFrame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(0, 1));
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);

        container = new Container(this);

        add(container);
        setVisible(true);

        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                if (Navbar.getP() != null) {
                    Navbar.getP().destroy();
                }
            }
        });
    }

    public void addView(Object view) {
        container.addView((View)view);
    }

    public Container getContainer() {
        return container;
    }
}
