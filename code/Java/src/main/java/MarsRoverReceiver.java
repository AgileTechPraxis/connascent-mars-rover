public interface MarsRoverReceiver {
    String packageReceived();
    void onValidMessageReceived(IListenToMessages listener);
}
