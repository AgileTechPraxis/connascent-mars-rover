package Infrastructure.Bus;

import Infrastructure.ISendNotifications;

public interface ISendFinalStateBus {
    void trigger(ISendNotifications sender);
}
