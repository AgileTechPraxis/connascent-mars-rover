public class MarsRoverController implements IProcessMessages  {
    private MarsRoverEngine marsRoverEngine;
    private ISendNotificationBus marsRoverServiceWriter;

    public MarsRoverController(){
        this.marsRoverEngine = new MarsRoverEngine();
    }

    public void writesTo(ISendNotificationBus marsRoverServiceBus) {

        this.marsRoverServiceWriter = marsRoverServiceBus;
    }

    public void readsFrom(IReadMessages marsRoverServiceBus) {

        marsRoverServiceBus.callBack(this);
    }

    public void process(String messageReceived) {
        // qua fa tutto il movimento

        marsRoverServiceWriter.NotifyExecution("2 3 N");
    }


}
