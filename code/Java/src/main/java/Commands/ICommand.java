package Commands;

import Model.Position;

public interface ICommand {
    Position execute(Position position);
}
