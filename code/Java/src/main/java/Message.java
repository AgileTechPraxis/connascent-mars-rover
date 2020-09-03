import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Message {

    private String x;
    private String y;
    private int commandsCount;
    private String direction;

    public int getCommandsCount() {
        return commandsCount;
    }

    public Message(ArrayList<String> datagrams) {
        parsePosition(datagrams);
    }

    private void parsePosition(ArrayList<String> datagrams) {
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
    }

    @Override
    public String toString() {
        return String.format("100 100\n%s %s %s\n", this.x, this.y, this.direction);
    }

    public boolean isValid() {
        return this.x != null && this.y != null && this.direction != null;
    }
}
