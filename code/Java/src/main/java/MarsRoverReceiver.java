import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MarsRoverReceiver implements INotifyMessageReceived {

    private IMessageReceivedBus marsRoverServiceBus;
    private ArrayList<String> datagrams = new ArrayList<>();
    private SmartTimer smartTimer = new SmartTimer();
    private Timer timer = new Timer();

    public void writesTo(IMessageReceivedBus marsRoverServiceBus) {
        this.marsRoverServiceBus = marsRoverServiceBus;
    }

    public void received(String datagram) {
        datagrams.add(datagram);
        Message message = new Message(datagrams);
        if (message.isValid()) {
            marsRoverServiceBus.NotifyMessageReceived(message.toString());
            timer.cancel();
        } else {
//            timer.WaitBeforeDoing(nofitfyMessage(this.datagrams));
            notifyMessageAfter3Seconds();
        }
    }

    private void notifyMessage(ArrayList<String> datagrams) {
        Message message = new Message(datagrams);
        if (message.isValid())
            marsRoverServiceBus.NotifyMessageReceived(message.toString());
        else
            marsRoverServiceBus.NotifyMessageReceived("ER");

    }

    private void notifyMessageAfter3Seconds() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                notifyMessage(datagrams);
                timer.cancel();
            }
        }, 3000, 1);
    }


}

