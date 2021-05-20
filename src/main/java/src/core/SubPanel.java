package src.core;

import javax.swing.JPanel;
import java.awt.event.ActionEvent;

public abstract class SubPanel extends JPanel {
    private MainPanel parent;
    private String panel_name;
    
    public SubPanel(MainPanel parent, String panel_name) {
        this.parent = parent;
        this.panel_name = panel_name;
    }

    public String getName() {
        return panel_name;
    }

    public void changeFocus(String panel_name) {
        setVisible(false);
        parent.switchPanel(this, panel_name);
    }

    public abstract void actionPerformed(ActionEvent e);

    public abstract void onFocus();
}
