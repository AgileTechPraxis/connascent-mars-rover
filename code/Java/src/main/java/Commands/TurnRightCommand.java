package Commands;

import Model.Position;

public record TurnRightCommand() implements ICommand {
    @Override
    public Position execute(Position position) {
        return position.turnRight();
    }
}
