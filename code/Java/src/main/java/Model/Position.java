package Model;

import java.util.Objects;

public class Position {
    private final Coordinate coordinate;
    private final Direction direction;

    public Position(int x, int y, String directionText) {
        this.coordinate = new Coordinate(x, y);
        this.direction = switch (directionText) {
            case "N" -> Direction.NORTH;
            case "E" ->Direction.EAST;
            case "S" ->Direction.SOUTH;
            case "W" ->Direction.WEST;
            default -> Direction.NORTH;
        };
    }

    public Position turnLeft() {
        return new Position(this.coordinate.x(), this.coordinate.y(), this.direction.turnLeft().toString());
    }

    public Position turnRight() {
        return new Position(this.coordinate.x(), this.coordinate.y(), this.direction.turnRight().toString());
    }

    public Position moveForward() {
        var coordinate = switch (this.direction) {
            case NORTH -> this.coordinate.moveNorth();
            case EAST -> this.coordinate.moveEast();
            case SOUTH -> this.coordinate.moveSouth();
            case WEST -> this.coordinate.moveWest();
        };
        return new Position(coordinate.x(), coordinate.y(), this.direction.toString());
    }

    @Override
    public String toString() {
        return String.format("%s %s", coordinate.toString(), direction.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(coordinate, position.coordinate) &&
                direction == position.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate, direction);
    }
}
