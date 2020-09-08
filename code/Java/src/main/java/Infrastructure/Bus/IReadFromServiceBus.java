package Infrastructure.Bus;

public interface IReadFromServiceBus {
    void writesTo(IMessageReceivedBus marsRoverServiceBus);
}
