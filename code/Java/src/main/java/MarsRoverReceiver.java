import java.util.ArrayList;
import org.apache.commons.lang.time.StopWatch;

public class MarsRoverReceiver implements INotifyMessageReceived {

    private IMessageReceivedBus marsRoverServiceBus;
    private ArrayList<String> datagrams = new ArrayList<>();

    public void writesTo(IMessageReceivedBus marsRoverServiceBus) {
        this.marsRoverServiceBus = marsRoverServiceBus;
    }

    //entry point of package detected
    public void received(String datagram) throws InterruptedException {
        datagrams.add(datagram);
        rebuildMessage();
    }

    private void rebuildMessage() throws InterruptedException {
        Message message = new Message(datagrams);
        if (message.isValid())
            marsRoverServiceBus.NotifyMessageReceived(message.toString());
        else{
            //wait 3 seconds and check again
            Thread.sleep(1000);

            //  if valid send
            if (message.isValid())
                marsRoverServiceBus.NotifyMessageReceived(message.toString());
            //  else error
            else
                marsRoverServiceBus.NotifyMessageReceived("ER");
        }
    }

}

