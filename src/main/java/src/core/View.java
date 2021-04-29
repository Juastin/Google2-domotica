package src.core;

import javax.swing.JPanel;
import java.awt.event.ActionEvent;

public abstract class View extends JPanel {
    private Container parent;
    private String name;
    
    public View(Container parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    /**
    * Returns this views name.
    */
    public String getName() {
        return name;
    }

    /**
    * Used to switch between views. Expects the String name of an existing view.
    * 
    * @param  name  String name of the view to switch to.
    */
    public void changeFocus(String name) {
        parent.switchView(this, name);
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
    * Event that is triggered every x ms. Determined by the Worker in Container.java.
    *
    * @param  now  a long timestamp in unix time stamp format.
    * @see         Container
    * @see         Worker
    */
    public abstract void onTick(long now);
}
