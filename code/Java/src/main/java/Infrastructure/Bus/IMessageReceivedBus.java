package Infrastructure.Bus;

public interface IMessageReceivedBus {
    void NotifyMessageReceived(String message);
}
