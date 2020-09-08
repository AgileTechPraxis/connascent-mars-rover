public class MarsRoverSender implements ISendNotifications {

    private INasaAntenna nasaAntenna;

    public MarsRoverSender(INasaAntenna nasaAntenna) {
        this.nasaAntenna = nasaAntenna;
    }

    public void readsFrom(ISendFinalStateBus marsRoverServiceBus) {
        marsRoverServiceBus.callBack(this);
    }

    public void send(String message) {
        //spaccalo in pacchetti e shipping to Nasa Antenna
        nasaAntenna.received(new String[]{message});
    }

    public void sendError() {
        //ritorna "ER"
    }
}
