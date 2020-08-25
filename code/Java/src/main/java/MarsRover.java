public class MarsRover {
    private MarsRoverReceiver marsRoverReceiver = null;
    private MarsRoverSender marsRoverSender = null;
    private MarsRoverController controller;

    public MarsRover(
            MarsRoverReceiver marsRoverReceiver,
            MarsRoverSender marsRoverSender,
            MarsRoverController controller) {

        this.marsRoverReceiver = marsRoverReceiver;
        this.marsRoverSender = marsRoverSender;
        this.controller = controller;

        marsRoverReceiver.onValidMessageReceived(this.controller);
        controller.onExecutionEnded(this.marsRoverSender);
    }


}

