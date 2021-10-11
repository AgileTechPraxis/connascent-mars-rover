namespace Source.Infrastructure.Bus
{
    public interface IMessageReceivedBus {
        void NotifyMessageReceived(string message);
        void NotifyError();
    }
}