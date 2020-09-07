import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MarsRoverReceiver implements INotifyMessageReceived {

    private IMessageReceivedBus marsRoverServiceBus;
    private ArrayList<String> datagrams = new ArrayList<>();
    private Timer timer = new Timer();

    public void writesTo(IMessageReceivedBus marsRoverServiceBus) {
        this.marsRoverServiceBus = marsRoverServiceBus;
    }

    //entry point of package detected
    public void received(String datagram) throws InterruptedException {
        datagrams.add(datagram);
        Message message = new Message(datagrams);
        //  if valid send
        if (message.isValid()) {
            marsRoverServiceBus.NotifyMessageReceived(message.toString());
            timer.cancel();
        } else {
            notifyMessageAfter3Seconds();
        }
    }

    private void notifyMessageAfter3Seconds() throws InterruptedException {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message(datagrams);
                if (message.isValid())
                    marsRoverServiceBus.NotifyMessageReceived(message.toString());
                else
                    marsRoverServiceBus.NotifyMessageReceived("ER");
                timer.cancel();
            }
        }, 3000, 1);
    }
}

