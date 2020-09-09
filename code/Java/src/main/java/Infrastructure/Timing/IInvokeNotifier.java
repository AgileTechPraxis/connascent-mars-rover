package Infrastructure.Timing;
import Infrastructure.INotifier;
import java.util.ArrayList;

public interface IInvokeNotifier {
    ISmartTimer beforeDoing(INotifier notifyMessage, ArrayList<String> datagrams);
}
