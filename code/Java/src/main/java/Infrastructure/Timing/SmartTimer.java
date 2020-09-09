package Infrastructure.Timing;

import Infrastructure.INotifier;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SmartTimer implements ISmartTimer {
    private int millisecondsToWait;
    private boolean isRunning = false;
    private Timer timer = new Timer();
    private TimerTask task;


    @Override
    public ISmartTimer waitMillisecond(int milliseconds) {
        reset();
        this.millisecondsToWait = milliseconds;
        return this;
    }

    @Override
    public ISmartTimer beforeDoing(INotifier notifier, ArrayList<String> datagrams) {

        task = new TimerTask() {
            @Override
            public void run() {
                notifier.notifyMessage(datagrams);
                reset();
            }
        };

        timer.schedule(task, millisecondsToWait, 1);
        return this;
    }

    private ISmartTimer reset() {
        if (task != null) {
            task.cancel();
        }
        return this;
    }

}
