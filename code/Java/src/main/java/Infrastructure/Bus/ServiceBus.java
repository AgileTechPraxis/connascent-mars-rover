package Infrastructure.Bus;

import Infrastructure.IProcessMessages;
import Infrastructure.IReadMessages;
import Infrastructure.ISendNotifications;

public class ServiceBus implements
        IMessageReceivedBus,
        ISendFinalStateBus,
        ISendNotificationBus,
        IReadMessages {
    private IProcessMessages marsRoverController;
    private ISendNotifications marsRoverSender;

    public void callBack(IProcessMessages marsRoverController) {
        this.marsRoverController = marsRoverController;
    }

    public void trigger(ISendNotifications marsRoverSender) {
        this.marsRoverSender = marsRoverSender;
    }

    public void NotifyMessageReceived(String rebuiltMessage) {
        this.marsRoverController.process(rebuiltMessage);
    }

    public void NotifyExecution(String finalState) {
        this.marsRoverSender.send(finalState);
    }
}