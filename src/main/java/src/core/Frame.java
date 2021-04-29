package src.core;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private Container container;
    
    public Frame() {
        setSize(946, 540);
        setTitle("Google2: MainFrame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(0, 1));

        container = new Container(this);

        add(container);
        setVisible(true);
    }

    public void addView(Object view) {
        container.addView((View)view);
    }

    public Container getContainer() {
        return container;
    }
}
