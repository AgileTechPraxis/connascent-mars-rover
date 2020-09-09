package Infrastructure.Bus;

public interface ISendNotificationBus {
    void NotifyExecution(String finalState);
}
