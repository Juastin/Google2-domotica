package src.core;

import javax.swing.JPanel;
import java.awt.event.ActionEvent;

public abstract class View extends JPanel {
    private Container parent;
    private String panel_name;
    
    public View(Container parent, String panel_name) {
        this.parent = parent;
        this.panel_name = panel_name;
    }

    /**
    * Returns this views panel_name.
    */
    public String getName() {
        return panel_name;
    }

    /**
    * Used to switch between views. Expects the String name of an existing view.
    * 
    * @param  panel_name  String name of the view to switch to.
    */
    public void changeFocus(String panel_name) {
        setVisible(false);
        parent.switchPanel(this, panel_name);
    }

    public abstract void actionPerformed(ActionEvent e);

    /**
    * Event that is triggered upon changing to this view. Similar to the HTML onload event.
    */
    public abstract void onFocus();

    /**
    * Event that is triggered upon leaving this view. Similar to the HTML onload event.
    */
    public abstract void onShadow();

    /**
    * Event that is triggered every x ms. Determined by the Worker in MainPanel.java.
    *
    * @param  now  a long timestamp in unix time stamp format.
    * @see         Container
    * @see         Worker
    */
    public abstract void onTick(long now);
}
