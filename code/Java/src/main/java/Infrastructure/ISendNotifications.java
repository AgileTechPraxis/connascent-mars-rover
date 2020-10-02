package Infrastructure;

import Infrastructure.Bus.ISendFinalStateBus;

public interface ISendNotifications {
    void readsFrom(ISendFinalStateBus marsRoverServiceBus);
    void sendError();
    void send(String notification);
}
