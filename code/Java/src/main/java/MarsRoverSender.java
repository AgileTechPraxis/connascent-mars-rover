public class MarsRoverSender implements ISendNotifications {

    private INasaAntenna nasaAntenna;

    public MarsRoverSender(INasaAntenna nasaAntenna) {
        this.nasaAntenna = nasaAntenna;
    }

    public void readsFrom(ISendFinalStateBus marsRoverServiceBus) {
        marsRoverServiceBus.callBack(this);
    }

    public void send(String message) {
        String[] messageParts = message.split(" ");
        nasaAntenna.received(new String[]{
                "X" + messageParts[0],
                "Y" + messageParts[1],
                "D" + messageParts[2]
        });
    }

    public void sendError() {
        nasaAntenna.received(new String[]{"ER"});
    }
}
