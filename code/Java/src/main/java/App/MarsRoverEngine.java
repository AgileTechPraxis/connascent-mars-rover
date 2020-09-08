package App;

import Commands.ICommand;
import Model.Coordinate;
import Model.Direction;
import Model.Position;

import java.util.ArrayList;

public class MarsRoverEngine {
    private Position position = new Position(new Coordinate(0, 0), Direction.NORTH);

    public void execute(ArrayList<ICommand> commands) {

        for (ICommand command : commands) {
            this.position = command.execute(this.position);
        }
    }

    public Position getPosition() {
        return this.position;
    }
}
