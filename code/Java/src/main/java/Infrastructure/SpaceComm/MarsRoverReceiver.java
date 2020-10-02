package Infrastructure.SpaceComm;

import Infrastructure.Bus.IMessageReceivedBus;
import Infrastructure.INotifier;
import Infrastructure.Bus.IWriteToServiceBus;
import Infrastructure.Timing.IWaitMillisecons;
import Infrastructure.Timing.SmartTimer;

import java.util.ArrayList;

public class MarsRoverReceiver implements IWriteToServiceBus {

    private IMessageReceivedBus marsRoverServiceBus;
    private ArrayList<String> datagrams = new ArrayList<>();
    private IWaitMillisecons smartTimer = SmartTimer.New();

    private INotifier notifyMessage = (data) -> this.notifyMessage(data);
    private int maxDelay;

    public MarsRoverReceiver(int maxDelay) {

        this.maxDelay = maxDelay;
    }

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
                .waitMillisecond(maxDelay)
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

