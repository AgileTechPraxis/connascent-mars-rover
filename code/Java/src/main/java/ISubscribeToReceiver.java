public interface ISubscribeToReceiver {
    void onMessageReceived(String message);
    void onError();
}
