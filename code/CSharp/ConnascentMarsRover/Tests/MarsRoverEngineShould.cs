using System.Collections;
using System.Linq;
using NUnit.Framework;
using Source.App;
using Source.Commands;
using Source.Model;

namespace Tests
{
    [TestFixture]
    public class MarsRoverEngineShould
    {
        private class MarsRoverEngineTestCases
        {
            public static IEnumerable TestCases
            {
                get
                {
                    yield return new TestCaseData(
                        new ICommand[]
                        {
                            new InitialisationCommand(new Coordinate(5, 5)),
                            new StartingPositionCommand(new Position(new Coordinate(2, 2), Direction.NORTH))
                        },
                        new Position(new Coordinate(2, 2), Direction.NORTH));

                    yield return new TestCaseData(
                        new ICommand[]
                        {
                            new InitialisationCommand(new Coordinate(5, 5)),
                            new StartingPositionCommand(new Position(new Coordinate(2, 2), Direction.NORTH)),
                            new TurnRightCommand()
                        },
                        new Position(new Coordinate(2, 2), Direction.EAST));

                    yield return new TestCaseData(
                        new ICommand[]
                        {
                            new InitialisationCommand(new Coordinate(5, 5)),
                            new StartingPositionCommand(new Position(new Coordinate(2, 2), Direction.NORTH)),
                            new TurnLeftCommand()
                        },
                        new Position(new Coordinate(2, 2), Direction.WEST));

                    yield return new TestCaseData(
                        new ICommand[]
                        {
                            new InitialisationCommand(new Coordinate(5, 5)),
                            new StartingPositionCommand(new Position(new Coordinate(2, 2), Direction.NORTH)),
                            new MoveForwardCommand()
                        },
                        new Position(new Coordinate(2, 3), Direction.NORTH));
                }
            }
        }

        [TestCaseSource(typeof(MarsRoverEngineTestCases), nameof(MarsRoverEngineTestCases.TestCases))]
        public void ExecuteCommands(ICommand[] commands, Position finalPosition)
        {
            var roverEngine = new MarsRoverEngine();

            roverEngine.Execute(commands.ToList());

            Assert.AreEqual(finalPosition, roverEngine.GetPosition());
        }
    }
}