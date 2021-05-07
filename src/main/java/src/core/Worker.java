package src.core;

import java.io.IOException;
import java.util.TimerTask;

public class Worker extends TimerTask {
    private Container parent;
    private final Arduino ar = new Arduino();

    public Worker(Container parent) {
        this.parent = parent;
    }

    @Override
    public void run() {
        long now = System.currentTimeMillis() / 1000L;
        parent.getCurrentView().onTick(now);
        System.out.println(ar.getArduinoData());
    }
}