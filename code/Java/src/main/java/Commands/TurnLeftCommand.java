package Commands;

import Model.Position;

public record TurnLeftCommand() implements ICommand {

    @Override
    public Position execute(Position position) {
        return position.turnLeft();
    }
}
