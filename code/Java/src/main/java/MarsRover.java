public class MarsRover implements IListenToMessages {
    private MarsRoverReceiver marsRoverReceiver = null;
    private MarsRoverSender marsRoverSender = null;

    public MarsRover(
            MarsRoverReceiver marsRoverReceiver,
            MarsRoverSender marsRoverSender) {

        this.marsRoverReceiver = marsRoverReceiver;
        this.marsRoverSender = marsRoverSender;

        marsRoverReceiver.onValidMessageReceived(this);
    }

    //method called by the receiver, simulating an event raised
    public void processMessage(String message){
        // logic when message is received
        // => translate the message in a sequence of commands
        // => pass the commands into the engine to be executed
        // => send back the final position to earth like
        //marsRoverSender.SendMessage("5 5 N");

    }
}

