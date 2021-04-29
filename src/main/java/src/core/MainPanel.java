package src.core;
import src.views.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;

public class MainPanel extends JPanel {
    private JFrame parent;
    private ArrayList<SubPanel> panels = new ArrayList<SubPanel>();
    private SubPanel currentPanel;
    
    public MainPanel(JFrame parent) {
        this.parent = parent;
        setBackground(Color.black);
        setLayout(new GridLayout(0, 1));

        panels.add(new ProfileView(this, "ProfileView"));
        panels.add(new MakeProfileView(this, "MakeProfileView"));
        panels.add(new LoginView(this, "LoginView"));
        panels.add(new MainScreenView(this, "MainScreenView"));
        panels.add(new PersonalSettingsView(this, "PersonalSettingsView"));
        panels.add(new MusicPlayerView(this, "MusicPlayerView"));

        add(panels.get(0));
        panels.get(0).setVisible(true);
        panels.get(0).onFocus();
        currentPanel = panels.get(0);

        Timer timer = new Timer();
        timer.schedule(new Worker(this), 0, 1000);

        setVisible(true);
    }

    public void switchPanel(SubPanel old_panel, String panel_name) {
        old_panel.onShadow();
        remove(old_panel);

        for (SubPanel panel: panels) {
            if (panel.getName().equals(panel_name)) {
                currentPanel = panel;
                add(panel);
                panel.setVisible(true);
                panel.onFocus();
            }
        }
    }

    public SubPanel getPanel() {
        return currentPanel;
    }

    public JFrame parent() {
        return parent;
    }

}
