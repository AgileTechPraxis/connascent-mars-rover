import App.MarsRoverEngine;
import Commands.*;
import Model.Coordinate;
import Model.Direction;
import Model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class MarsRoverEngineShould {
    private static Stream<Arguments> valueProvider() {
        return Stream.of(
                arguments(
                         new ICommand[]{
                                new InitialisationCommand(new Coordinate(5, 5)),
                                new StartingPositionCommand( new Position(2,2,"N")),
                        },
                        new Position(2,2,"N")
                ),
                arguments(
                        new ICommand[]{
                                new InitialisationCommand(new Coordinate(5, 5)),
                                new StartingPositionCommand(new Position( 2,2,"N")),
                                new TurnLeftCommand(),
                        },
                        new Position(2,2,"W")
                ),
                arguments(
                        new ICommand[]{
                                new InitialisationCommand(new Coordinate(5, 5)),
                                new StartingPositionCommand(new Position(2,2,"N")),
                                new TurnRightCommand(),
                        },
                        new Position(2,2,"E")),
                arguments(
                        new ICommand[]{
                                new InitialisationCommand(new Coordinate(5, 5)),
                                new StartingPositionCommand(new Position( 2,2,"N")),
                                new MoveForwardCommand(),
                        },
                        new Position(2,3,"N"))
        );
    }

    @BeforeEach
    void setUp() {

    }

    @ParameterizedTest
    @MethodSource("valueProvider")
    void executeCommands(ICommand[] commands, Position finalPosition) {
        MarsRoverEngine roverEngine = new MarsRoverEngine();

        roverEngine.execute(new ArrayList<>(Arrays.asList(commands)));

        assertEquals(finalPosition, roverEngine.getPosition());
    }

}
