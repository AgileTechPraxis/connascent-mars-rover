package App;

import Commands.*;
import Model.Coordinate;
import Model.Direction;
import Model.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class CommandInterpreter {

    private Map<String, Direction> letterToDirection = new HashMap<>() {{
        put("N", Direction.NORTH);
        put("E", Direction.EAST);
        put("S", Direction.SOUTH);
        put("W", Direction.WEST);
    }};

    public ArrayList<ICommand> translate(String commands) {

        ArrayList<ICommand> allCommands = new ArrayList<>();
        allCommands.add(getInitialisationCommand(commands));
        allCommands.add(getStartingPositionCommand(commands));
        allCommands.addAll(getMovementCommands(commands));

        return allCommands;
    }

    private ArrayList<ICommand> getMovementCommands(String commands) {
        ArrayList<ICommand> movementCommands = new ArrayList<>();
        String[] lines = commands.split("\n");

        for (Character command : lines[2].toCharArray()){
            switch (command){
                case 'L'-> movementCommands.add(new TurnLeftCommand());
                case 'F'-> movementCommands.add(new MoveForwardCommand());
                case 'R'-> movementCommands.add(new TurnRightCommand());
            }
        }
        return movementCommands;
    }

    private InitialisationCommand getInitialisationCommand(String commands) {
        String[] lines = commands.split("\n");
        String[] topRight = lines[0].split(" ");

        return new InitialisationCommand(new Coordinate(parseInt(topRight[0]), parseInt(topRight[1])));
    }

    private StartingPositionCommand getStartingPositionCommand(String commands) {
        String[] lines = commands.split("\n");
        String[] coords = lines[1].split(" ");

        Coordinate coordinate = new Coordinate(parseInt(coords[0]), parseInt(coords[1]));
        Direction direction = letterToDirection.get(coords[2]);
        Position position = new Position(coordinate, direction);

        return new StartingPositionCommand(position);
    }
}
