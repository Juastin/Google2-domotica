package google2_domotica.core;
import google2_domotica.core.*;

import java.util.TimerTask;
import java.util.ArrayList;

public class Worker extends TimerTask {
    private MainPanel parent;

    public Worker(MainPanel parent) {
        this.parent = parent;
    }

    @Override
    public void run() {
        System.out.println("Deze tekst wordt geprint in Worker.java");
    }
    
}
