using System.Collections.Generic;
using static Source.Model.Direction;

namespace Source.Model
{
    public enum Direction
    {
        EAST,
        NORTH,
        SOUTH,
        WEST
    }

    internal static class DirectionExtensions
    {
        private static readonly List<Direction> Directions = new List<Direction>
        {
            NORTH,
            EAST,
            SOUTH,
            WEST
        };

        internal static Direction TurnLeft(this Direction direction)
        {
            var index = Directions.IndexOf(direction) - 1;
            return Directions[(index % 4 + 4) % 4];
        }

        internal static Direction TurnRight(this Direction direction)
        {
            var index = Directions.IndexOf(direction) + 1;
            return Directions[(index % 4 + 4) % 4];
        }
    }
}