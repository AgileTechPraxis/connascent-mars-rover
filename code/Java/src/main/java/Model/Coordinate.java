package Model;

public record Coordinate(int x, int y) {
    public Coordinate moveNorth() {
        return new Coordinate(x, y + 1);
    }

    public Coordinate moveEast() {
        return new Coordinate(x + 1, y);
    }

    public Coordinate moveSouth() {
        return new Coordinate(x, y - 1);
    }

    public Coordinate moveWest() {
        return new Coordinate(x - 1, y);
    }

    @Override
    public String toString() {
        return String.format("%s %s", x, y);
    }
}
