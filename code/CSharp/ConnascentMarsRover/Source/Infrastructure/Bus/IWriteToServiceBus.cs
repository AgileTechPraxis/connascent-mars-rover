namespace Source.Infrastructure.Bus
{
    public interface IWriteToServiceBus
    {
        void WritesTo(IMessageReceivedBus marsRoverServiceBus);
    }
}