package src.core;
import src.views.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;

public class Container extends JPanel {
    private JFrame parent;
    private ArrayList<View> views = new ArrayList<View>();
    private View currentView;
    
    public Container(JFrame parent) {
        this.parent = parent;
        setBackground(Color.black);
        setLayout(new GridLayout(0, 1));

        views.add(new Index(this, "ProfileView"));

        add(views.get(0));
        views.get(0).setVisible(true);
        views.get(0).onFocus();
        currentView = views.get(0);

        Timer timer = new Timer();
        timer.schedule(new Worker(this), 0, 1000);

        setVisible(true);
    }

    public void switchView(View oldView, String name) {
        boolean found = false;

        for (View view: views) {
            if (view.getName().equals(name)) {
                oldView.onShadow();
                oldView.setVisible(false);
                remove(oldView);

                currentView = view;
                add(view);
                view.setVisible(true);
                view.onFocus();

                found = true;
                break;
            }
        }
        
        if (!found) {System.out.println("View " + name + " could not be found.");}
    }

    public View getCurrentView() {
        return currentView;
    }

    public JFrame parent() {
        return parent;
    }

    public void addView(View view) {
        views.add(view);
    }

}
