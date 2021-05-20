package src.core;
import java.util.TimerTask;

public class Worker extends TimerTask {
    private Container parent;
    public Worker(Container parent) {
        this.parent = parent;
    }

    @Override
    public void run() {
        long now = System.currentTimeMillis() / 1000L;
        parent.getCurrentView().onTick(now);
    }
}