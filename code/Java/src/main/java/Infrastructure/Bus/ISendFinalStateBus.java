package Infrastructure.Bus;

import Infrastructure.ISendNotifications;

public interface ISendFinalStateBus {
    void callBack(ISendNotifications sender);
}
