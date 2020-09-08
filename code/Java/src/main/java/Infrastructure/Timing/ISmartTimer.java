package Infrastructure.Timing;

import Infrastructure.INotifier;

import java.util.ArrayList;

public interface ISmartTimer {
    ISmartTimer waitMillisecond(int milliseconds);

    ISmartTimer beforeDoing(INotifier notifyMessage, ArrayList<String> datagrams);

}
