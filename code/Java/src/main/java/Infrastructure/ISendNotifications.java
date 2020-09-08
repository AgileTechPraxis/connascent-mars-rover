package Infrastructure;

import Infrastructure.Bus.ISendFinalStateBus;

public interface ISendNotifications {
    void readsFrom(ISendFinalStateBus marsRoverServiceBus);

    void send(String notification);
}
