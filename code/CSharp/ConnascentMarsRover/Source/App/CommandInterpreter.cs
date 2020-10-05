using System.Collections.Generic;
using Source.Commands;
using Source.Model;
using static Source.App.Configuration.Primitives;

namespace Source.App
{
    public class CommandInterpreter
    {
        private const string NORTH = "N";
        private const string EAST = "E";
        private const string SOUTH = "S";
        private const string WEST = "W";

        private readonly Dictionary<string, Direction> _letterToDirection =
            new Dictionary<string, Direction>
            {
                {NORTH, Direction.NORTH},
                {EAST, Direction.EAST},
                {SOUTH, Direction.SOUTH},
                {WEST, Direction.WEST}
            };


        public List<ICommand> Translate(string commands)
        {
            var allCommands = new List<ICommand>();
            allCommands.Add(GetInitialisationCommand(commands));
            allCommands.Add(GetStartingPositionCommand(commands));
            allCommands.AddRange(GetMovementCommands(commands));

            return allCommands;
        }

        private IEnumerable<ICommand> GetMovementCommands(string commands)
        {
            var movementCommands = new List<ICommand>();
            var lines = commands.Split("\n");

            foreach (var command in lines[2])
            {
                switch (command)
                {
                    case TURN_LEFT:
                        movementCommands.Add(new TurnLeftCommand());
                        break;
                    case MOVE_FORWARD:
                        movementCommands.Add(new MoveForwardCommand());
                        break;
                    case TURN_RIGHT:
                        movementCommands.Add(new TurnRightCommand());
                        break;
                }
            }

            return movementCommands;
        }

        private InitialisationCommand GetInitialisationCommand(string commands)
        {
            var lines = commands.Split("\n");
            var topRight = lines[0].Split(" ");

            return new InitialisationCommand(
                new Coordinate(int.Parse(topRight[0]), int.Parse(topRight[1])));
        }

        private StartingPositionCommand GetStartingPositionCommand(string commands)
        {
            var lines = commands.Split("\n");
            var coords = lines[1].Split(" ");

            var coordinate = new Coordinate(int.Parse(coords[0]), int.Parse(coords[1]));
            var direction = _letterToDirection[coords[2]];
            var position = new Position(coordinate, direction);

            return new StartingPositionCommand(position);
        }
    }
}