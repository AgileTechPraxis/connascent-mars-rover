import App.CommandInterpreter;
import App.MarsRoverEngine;
import Commands.ICommand;
import Model.Position;

import java.util.ArrayList;

public class MarsRoverController implements IProcessMessages {
    private MarsRoverEngine marsRoverEngine;
    private CommandInterpreter commandInterpreter;
    private ISendNotificationBus marsRoverServiceWriter;

    public MarsRoverController() {
        this.marsRoverEngine = new MarsRoverEngine();
        commandInterpreter = new CommandInterpreter();
    }

    public void writesTo(ISendNotificationBus marsRoverServiceBus) {

        this.marsRoverServiceWriter = marsRoverServiceBus;
    }

    public void readsFrom(IReadMessages marsRoverServiceBus) {

        marsRoverServiceBus.callBack(this);
    }

    public void process(String messageReceived) {
        ArrayList<ICommand> commands = commandInterpreter.translate(messageReceived);
        marsRoverEngine.execute(commands);
        Position finalPosition = marsRoverEngine.getPosition();
        marsRoverServiceWriter.NotifyExecution(finalPosition.toString());
    }
}
