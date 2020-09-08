package Commands;

import Model.Coordinate;
import Model.Position;

public record InitialisationCommand(Coordinate topRightCoordinate) implements ICommand {
    @Override
    public Position execute(Position position) {
        return position;
    }
}
