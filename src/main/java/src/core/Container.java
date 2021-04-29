package src.core;
import src.views.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;

public class Container extends JPanel {
    private JFrame parent;
    private ArrayList<View> panels = new ArrayList<View>();
    private View currentPanel;
    
    public Container(JFrame parent) {
        this.parent = parent;
        setBackground(Color.black);
        setLayout(new GridLayout(0, 1));

        panels.add(new Index(this, "ProfileView"));

        add(panels.get(0));
        panels.get(0).setVisible(true);
        panels.get(0).onFocus();
        currentPanel = panels.get(0);

        Timer timer = new Timer();
        timer.schedule(new Worker(this), 0, 1000);

        setVisible(true);
    }

    public void switchPanel(View old_panel, String panel_name) {
        old_panel.onShadow();
        remove(old_panel);

        for (View panel: panels) {
            if (panel.getName().equals(panel_name)) {
                currentPanel = panel;
                add(panel);
                panel.setVisible(true);
                panel.onFocus();
            }
        }
    }

    public View getCurrentPanel() {
        return currentPanel;
    }

    public JFrame parent() {
        return parent;
    }

    public void addPanel(View panel) {
        panels.add(panel);
    }

}
