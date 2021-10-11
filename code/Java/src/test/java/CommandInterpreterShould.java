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
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class CommandInterpreterShould {

    private static Stream<Arguments> valueProvider() {
        return Stream.of(
                arguments("""
                        5 5
                        3 3 E
                        LFLFR""", List.of(
                        new InitialisationCommand(new Coordinate(5, 5)),
                        new StartingPositionCommand(new Position(new Coordinate(3, 3), Direction.EAST)),
                        new TurnLeftCommand(),
                        new MoveForwardCommand(),
                        new TurnLeftCommand(),
                        new MoveForwardCommand(),
                        new TurnRightCommand())),
                arguments("""
                        5 5
                        3 3 E
                        L""", List.of(
                        new InitialisationCommand(new Coordinate(5, 5)),
                        new StartingPositionCommand(new Position(new Coordinate(3, 3), Direction.EAST)),
                        new TurnLeftCommand())),
                arguments("""
                        5 5
                        3 3 E
                        F""", List.of(
                        new InitialisationCommand(new Coordinate(5, 5)),
                        new StartingPositionCommand(new Position(new Coordinate(3, 3), Direction.EAST)),
                        new MoveForwardCommand())),
                arguments("""
                        5 5
                        3 3 E
                        R""", List.of(
                        new InitialisationCommand(new Coordinate(5, 5)),
                        new StartingPositionCommand(new Position(new Coordinate(3, 3), Direction.EAST)),
                        new TurnRightCommand()))
        );
    }

    @BeforeEach
    void setUp() {

    }

    @ParameterizedTest
    @MethodSource("valueProvider")
    void parseCommands(String inputCommand, List<ICommand> expectedCommnads) {
        CommandInterpreter commandInterpreter = new CommandInterpreter();

        List<ICommand> commands = commandInterpreter.translate(inputCommand);

        assertEquals(expectedCommnads, commands);
    }

}