using System.Collections;
using System.Collections.Generic;
using NUnit.Framework;
using Source.App;
using Source.Commands;
using Source.Model;

namespace Tests
{
    public class CommandInterpreterTestCases
    {
        public static IEnumerable TestCases
        {
            get
            {
                yield return new TestCaseData(
                    "5 5\n3 3 E\nLFLFR",
                    new ICommand[]
                    {
                        new InitialisationCommand(new Coordinate(5, 5)),
                        new StartingPositionCommand(new Position(new Coordinate(3, 3), Direction.EAST)),
                        new TurnLeftCommand(),
                        new MoveForwardCommand(),
                        new TurnLeftCommand(),
                        new MoveForwardCommand(),
                        new TurnRightCommand()
                    });

                yield return new TestCaseData(
                    "5 5\n3 3 E\nL",
                    new ICommand[]
                    {
                        new InitialisationCommand(new Coordinate(5, 5)),
                        new StartingPositionCommand(new Position(new Coordinate(3, 3), Direction.EAST)),
                        new TurnLeftCommand()
                    });

                yield return new TestCaseData(
                    "5 5\n3 3 E\nF",
                    new ICommand[]
                    {
                        new InitialisationCommand(new Coordinate(5, 5)),
                        new StartingPositionCommand(new Position(new Coordinate(3, 3), Direction.EAST)),
                        new MoveForwardCommand()
                    });

                yield return new TestCaseData(
                    "5 5\n3 3 E\nR",
                    new ICommand[]
                    {
                        new InitialisationCommand(new Coordinate(5, 5)),
                        new StartingPositionCommand(new Position(new Coordinate(3, 3), Direction.EAST)),
                        new TurnRightCommand()
                    });
            }
        }

        [TestFixture]
        public class CommandInterpreterShould
        {
            [TestCaseSource(typeof(CommandInterpreterTestCases), nameof(TestCases))]
            public void ParseCommands(string inputCommand, ICommand[] expectedCommands)
            {
                var commandInterpreter = new CommandInterpreter();

                var commands = commandInterpreter.Translate(inputCommand);

                CollectionAssert.AreEqual(expectedCommands, commands);
            }
        }
    }
}

    