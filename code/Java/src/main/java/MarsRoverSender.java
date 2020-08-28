public class MarsRoverSender implements ISendNotifications {
    public void readsFrom(ISendFinalStateBus marsRoverServiceBus){
        marsRoverServiceBus.callBack(this);
    };

    public void send(String message){
        //spaccalo in pacchetti e shipping
    };

    public void sendError(){
        //ritorna "ER"
    }


}
