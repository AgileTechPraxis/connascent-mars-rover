using System.Collections.Generic;
using Source.Commands;
using Source.Model;

namespace Source.App
{
    public class MarsRoverEngine
    {
        Position _position = new Position(new Coordinate(0,0), Direction.NORTH);
        public void Execute(List<ICommand> commands)
        {
            foreach (var command in commands)
            {
                _position = command.Execute(_position);
            }
        }

        public Position GetPosition()
        {
            return _position;
        }
    }
}