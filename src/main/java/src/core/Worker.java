package src.core;

import java.util.TimerTask;

public class Worker extends TimerTask {
    private MainPanel parent;

    public Worker(MainPanel parent) {
        this.parent = parent;
    }

    @Override
    public void run() {
        long now = System.currentTimeMillis() / 1000L;
        parent.getPanel().onTick(now);
    }
    
}