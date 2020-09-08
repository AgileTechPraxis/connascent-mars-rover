import App.CommandInterpreter;
import Commands.*;
import Model.Coordinate;
import Model.Direction;
import Model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class CommandInterpreterShould {

    private static Stream<Arguments> valueProvider() {
        return Stream.of(
                arguments("""
                        5 5
                        1 2 N
                        LMLMLMLMM""", new StartingPositionCommand(new Position(new Coordinate(1, 2), Direction.NORTH))),
                arguments("""
                        5 5
                        3 3 E
                        LMLMLMLMM""", new StartingPositionCommand(new Position(new Coordinate(3, 3), Direction.EAST))),
                arguments("""
                        5 5
                        3 3 E
                        LMLMLMLMM""", new InitialisationCommand(new Coordinate(5, 5))),
                arguments("""
                        5 5
                        3 3 E
                        L""", new TurnLeftCommand()),
                arguments("""
                        5 5
                        3 3 E
                        M""", new MoveForwardCommand()),
                arguments("""
                        5 5
                        3 3 E
                        R""", new TurnRightCommand())
        );
    }

    @BeforeEach
    void setUp() {

    }

    @ParameterizedTest
    @MethodSource("valueProvider")
    void extractStartingPosition(String inputCommand, ICommand expected) {
        CommandInterpreter commandInterpreter = new CommandInterpreter();

        List<ICommand> commandList = commandInterpreter.translate(inputCommand);

        assertTrue(commandList.contains(expected));
    }

}