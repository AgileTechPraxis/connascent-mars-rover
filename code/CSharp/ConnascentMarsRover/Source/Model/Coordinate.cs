using System;

namespace Source.Model
{
    public class Coordinate
    {
        private readonly int _x;
        private readonly int _y;

        public Coordinate(int x, int y)
        {
            _x = x;
            _y = y;
        }

        protected bool Equals(Coordinate other)
        {
            return _x == other._x && _y == other._y;
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            if (obj.GetType() != this.GetType()) return false;
            return Equals((Coordinate) obj);
        }

        public override int GetHashCode()
        {
            return HashCode.Combine(_x, _y);
        }

        public Coordinate MoveNorth() {
            return new Coordinate(_x, _y + 1);
        }

        public Coordinate MoveEast() {
            return new Coordinate(_x + 1, _y);
        }

        public Coordinate MoveSouth() {
            return new Coordinate(_x, _y - 1);
        }

        public Coordinate MoveWest() {
            return new Coordinate(_x - 1, _y);
        }
        
        public override string ToString()
        {
            return $"{_x.ToString()} {_y.ToString()}";
        }
    }
}