package Infrastructure.Bus;

public interface IWriteToServiceBus {
    void writesTo(IMessageReceivedBus marsRoverServiceBus);
}
