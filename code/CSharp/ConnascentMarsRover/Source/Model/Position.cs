using System;
using static Source.Model.Direction;

namespace Source.Model
{
    public class Position
    {
        private readonly Coordinate _coordinate;
        private readonly Direction _direction;

        public Position(Coordinate coordinate, Direction direction)
        {
            _coordinate = coordinate;
            _direction = direction;
        }

        public Position MoveForward()
        {
            return _direction switch
            {
                NORTH => new Position(_coordinate.MoveNorth(), _direction),
                EAST => new Position(_coordinate.MoveEast(), _direction),
                SOUTH => new Position(_coordinate.MoveSouth(), _direction),
                WEST => new Position(_coordinate.MoveWest(), _direction),
                _ => throw new ArgumentOutOfRangeException()
            };
        }

        public Position TurnLeft()
        {
            return new Position(_coordinate, _direction.TurnLeft());
        }

        public Position TurnRight()
        {
            return new Position(_coordinate, _direction.TurnRight());
        }

        protected bool Equals(Position other)
        {
            return Equals(_coordinate, other._coordinate) && _direction == other._direction;
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            if (obj.GetType() != this.GetType()) return false;
            return Equals((Position) obj);
        }

        public override int GetHashCode()
        {
            return HashCode.Combine(_coordinate, (int) _direction);
        }

        public override string ToString()
        {
            var direction = _direction.ToString().Substring(0, 1);
            return $"{_coordinate} {direction}";
        }
    }
}