public class ServiceBus implements IMessageReceivedBus, ISendFinalStateBus, ISendNotificationBus, IReadMessages {
    private IProcessMessages marsRoverControllerCallback;
    private ISendNotifications marsRoverSenderCallback;

    public void callBack(IProcessMessages marsRoverController) {
        marsRoverControllerCallback = marsRoverController;
    }

    public void callBack(ISendNotifications marsRoverSender) {
        marsRoverSenderCallback = marsRoverSender;
    }

    public void NotifyMessageReceived(String messageReceived) {
        this.marsRoverControllerCallback.process(messageReceived);
    }

    public void NotifyExecution(String finalState) {
        this.marsRoverSenderCallback.send(finalState);
    }


}
