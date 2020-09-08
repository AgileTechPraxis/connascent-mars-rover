package Commands;

import Model.Position;

public record StartingPositionCommand(Position startingPosition) implements ICommand {

    @Override
    public Position execute(Position position) {
        return this.startingPosition;
    }
}


