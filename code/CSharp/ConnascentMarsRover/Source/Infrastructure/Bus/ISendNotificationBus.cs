namespace Source.Infrastructure.Bus
{
    public interface ISendNotificationBus {
        void NotifyExecution(string finalState);
    }
}