public interface ISendNotifications {
    void readsFrom(ISendFinalStateBus marsRoverServiceBus);

    void send(String notification);
}
