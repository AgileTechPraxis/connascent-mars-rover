package Infrastructure.SpaceComm;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Message {

    private String x;
    private String y;
    private int commandsCount;
    private String direction;
    private String positionMessage;
    private String commandsMessage;

    public Message(ArrayList<String> datagrams) {
        this.positionMessage = parsePosition(datagrams);
        this.commandsMessage = parseCommands(datagrams);
    }

    private String parsePosition(ArrayList<String> datagrams) {
        for (String datagram :
                datagrams) {
            if (datagram.startsWith("X"))
                this.x = datagram.substring(1);
            if (datagram.startsWith("Y"))
                this.y = datagram.substring(1);
            if (datagram.startsWith("D"))
                this.direction = datagram.substring(1);
            if (datagram.startsWith("M")) {
                this.commandsCount = parseInt(datagram.substring(1));
            }
        }
        return String.format("100 100\n%s %s %s\n", this.x, this.y, this.direction);
    }

    @Override
    public String toString() {
        return String.format("%s%s", this.positionMessage, this.commandsMessage);
    }

    public boolean isValid() {
        return this.x != null &&
                this.y != null &&
                this.direction != null &&
                this.commandsCount > 0 &&
                this.commandsMessage.length() == this.commandsCount;
    }

    String parseCommands(ArrayList<String> datagrams) {
        String commandsMessage = "";
        for (int commandNumber = 1; commandNumber <= commandsCount; commandNumber++) {
            for (String datagram : datagrams) {
                if (datagram.startsWith(String.valueOf(commandNumber))) {
                    commandsMessage += datagram.substring(1);
                }
            }
        }
        return commandsMessage;
    }
}
