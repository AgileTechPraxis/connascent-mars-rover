package Infrastructure;

import java.util.ArrayList;

public interface INotifier {
    void notifyMessage(ArrayList<String> datagrams);
}
