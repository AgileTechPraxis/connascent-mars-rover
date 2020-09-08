package Model;

import java.util.HashMap;
import java.util.Map;

public enum Direction {
    NORTH(0), EAST(1), SOUTH(2), WEST(3);

    private int value;
    private static Map map = new HashMap<>();

    Direction(int value) {
        this.value = value;
    }

    static {
        for (Direction direction : Direction.values()) {
            map.put(direction.value, direction);
        }
    }

    private static Direction valueOf(int directionValue) {
        return (Direction) map.get(directionValue);
    }

    public Direction turnLeft() {
        int previousValue = value - 1;
        return valueOf((previousValue % 4 + 4) % 4);
    }

    public Direction turnRight() {
        int previousValue = value + 1;
        return valueOf((previousValue % 4 + 4) % 4);
    }


    @Override
    public String toString() {
        return String.format("%s", super.toString().toCharArray()[0]);
    }
}

