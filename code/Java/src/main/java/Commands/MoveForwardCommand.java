package Commands;

import Model.Position;

public record MoveForwardCommand() implements ICommand {
    @Override
    public Position execute(Position position) {
        return position.moveForward();
    }
}
