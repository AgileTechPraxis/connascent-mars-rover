using Source.Infrastructure.Bus;

namespace Source.Infrastructure
{
    public interface ISendNotifications
    {
        void ReadsFrom(ServiceBus marsRoverServiceBus);
        void SendError();
        void Send(string notification);
    }
}