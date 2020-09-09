package Infrastructure.SpaceComm;

import Infrastructure.Bus.IMessageReceivedBus;
import Infrastructure.INotifier;
import Infrastructure.Bus.IWriteToServiceBus;
import Infrastructure.Timing.SmartTimer;

import java.util.ArrayList;

public class MarsRoverReceiver implements IWriteToServiceBus {

    private IMessageReceivedBus marsRoverServiceBus;
    private ArrayList<String> datagrams = new ArrayList<>();
    private SmartTimer smartTimer = new SmartTimer();

    private INotifier notifyMessage = (data) -> this.notifyMessage(data);
    private final int MAX_DELAY_MILLISECONDS = 3000;

    public void writesTo(IMessageReceivedBus marsRoverServiceBus) {

        this.marsRoverServiceBus = marsRoverServiceBus;
    }

    public void received(String datagram) {
        datagrams.add(datagram);

        Message message = new Message(datagrams);

        if (message.isValid()) {
            marsRoverServiceBus.NotifyMessageReceived(message.toString());
            return;
        }

        smartTimer
                .waitMillisecond(MAX_DELAY_MILLISECONDS)
                .beforeDoing(notifyMessage, this.datagrams);
    }

    private void notifyMessage(ArrayList<String> datagrams) {
        Message message = new Message(datagrams);
        if (message.isValid()) {
            marsRoverServiceBus.NotifyMessageReceived(message.toString());
            return;
        }
        marsRoverServiceBus.NotifyError();
    }

}

