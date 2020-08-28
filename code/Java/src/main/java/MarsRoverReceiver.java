public class MarsRoverReceiver implements INotifyMessageReceived {

    private IMessageReceivedBus marsRoverServiceBus;

    public void writesTo(IMessageReceivedBus marsRoverServiceBus){

        this.marsRoverServiceBus = marsRoverServiceBus;
    };

    //entry point of package detected
    public void received(String datagram){

        marsRoverServiceBus.NotifyMessageReceived("xxxx");
    }
}
