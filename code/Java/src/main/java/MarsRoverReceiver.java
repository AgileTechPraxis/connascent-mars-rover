import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class MarsRoverReceiver implements INotifyMessageReceived {

    private IMessageReceivedBus marsRoverServiceBus;
    private ArrayList<String> datagrams = new ArrayList<>();
    private String X = "";
    private String Y = "";
    private String D = "";

    public void writesTo(IMessageReceivedBus marsRoverServiceBus) {

        this.marsRoverServiceBus = marsRoverServiceBus;
    }

    ;

    //entry point of package detected
    public void received(String datagram) {
        datagrams.add(datagram);
        rebuildMessage();
    }

    private void rebuildMessage() {

        Message message = new Message(datagrams);

        String wholeMessage = message.toString();
        String commandsMessage1 = "";
        for (int commandNumber = 1; commandNumber <= message.getCommandsCount(); commandNumber++) {
            for (String datagram : datagrams) {
                if (datagram.startsWith(String.valueOf(commandNumber))) {
                    commandsMessage1 += datagram.substring(1);
                }
            }
        }

        String commandsMessage = commandsMessage1;
        if (message.isValid() && commandsMessage.length() == message.getCommandsCount()) {
            marsRoverServiceBus.NotifyMessageReceived(
                    String.format("%s%s", wholeMessage, commandsMessage));
        }
    }
}

