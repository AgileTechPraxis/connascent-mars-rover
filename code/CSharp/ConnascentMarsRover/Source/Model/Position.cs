using System;

namespace Source.Model
{
    public class Position
    {
        private readonly Coordinate _coordinate;
        private readonly Direction _east;

        public Position(Coordinate coordinate, Direction east)
        {
            _coordinate = coordinate;
            _east = east;
        }

        protected bool Equals(Position other)
        {
            return Equals(_coordinate, other._coordinate) && _east == other._east;
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
            return HashCode.Combine(_coordinate, (int) _east);
        }
    }
}