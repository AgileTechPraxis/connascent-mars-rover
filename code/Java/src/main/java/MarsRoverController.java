public class MarsRoverController implements IListenToMessages {
    private MarsRoverEngine marsRoverEngine;
    private MarsRoverSender marsRoverSender;

    public MarsRoverController(MarsRoverEngine marsRoverEngine){

        this.marsRoverEngine = marsRoverEngine;
    }

    public void processMessage(String message) {
        // do stuff

        //at the end call
        marsRoverSender.send(marsRoverEngine.currentState());
    }

    public void onExecutionEnded(MarsRoverSender marsRoverSender) {

        this.marsRoverSender = marsRoverSender;
    }
}
