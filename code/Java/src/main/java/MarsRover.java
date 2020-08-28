public class MarsRover {
    private final ServiceBus marsRoverServiceBus;
    private MarsRoverReceiver marsRoverReceiver = null;
    private final MarsRoverSender marsRoverSender;
    private MarsRoverController controller = null;

    public MarsRover(
            ServiceBus marsRoverServiceBus,
            MarsRoverReceiver marsRoverReceiver,
            MarsRoverSender marsRoverSender,
            MarsRoverController controller) {
        this.marsRoverServiceBus = marsRoverServiceBus;
        this.marsRoverReceiver = marsRoverReceiver;
        this.marsRoverSender = marsRoverSender;
        this.controller = controller;

        this.marsRoverReceiver.writesTo(this.marsRoverServiceBus);
        this.controller.readsFrom(this.marsRoverServiceBus);
        this.controller.writesTo(this.marsRoverServiceBus);
        this.marsRoverSender.readsFrom(this.marsRoverServiceBus);
    }


}

