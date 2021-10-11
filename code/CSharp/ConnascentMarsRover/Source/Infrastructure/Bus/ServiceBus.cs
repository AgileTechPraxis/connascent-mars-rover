namespace Source.Infrastructure.Bus
{
    public class ServiceBus : 
        IMessageReceivedBus,
        ISendFinalStateBus,
        ISendNotificationBus,
        IReadMessages
    {
        private IProcessMessages marsRoverController;
        private ISendNotifications marsRoverSender;

        public void CallBack(IProcessMessages marsRoverController)
        {
            this.marsRoverController = marsRoverController;
        }

        public void Trigger(ISendNotifications marsRoverSender)
        {
            this.marsRoverSender = marsRoverSender;
        }

        public void NotifyMessageReceived(string rebuiltMessage)
        {
            this.marsRoverController.Process(rebuiltMessage);
        }

        public void NotifyError()
        {
            this.marsRoverSender.SendError();
        }

        public void NotifyExecution(string finalState)
        {
            this.marsRoverSender.Send(finalState);
        }
    }
}