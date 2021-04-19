package src.core;
import src.views.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;

public class MainPanel extends JPanel {
    private JFrame parent;
    private ArrayList<SubPanel> panels = new ArrayList<SubPanel>();
    
    public MainPanel(JFrame parent) {
        this.parent = parent;
        setBackground(Color.black);
        setLayout(new GridLayout(0, 1));
        
        panels.add(new MenuView(this, "MenuView"));
        panels.add(new ImageView(this, "ImageView"));
        
        Timer timer = new Timer();
        timer.schedule(new Worker(this), 0, 60000);

        add(panels.get(0));
        panels.get(0).setVisible(true);

        setVisible(true);
    }

    public void switchPanel(SubPanel old_panel, String panel_name) {
        remove(old_panel);
        for (SubPanel panel: panels) {
            if (panel.getName().equals(panel_name)) {
                add(panel);
                panel.setVisible(true);
            }
        }
    }

    public JFrame parent() {
        return parent;
    }

}
