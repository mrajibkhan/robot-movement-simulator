package com.example.simulaton.models;

import java.awt.*;

/**
 * Created by rajib.khan on 1/21/17.
 * Used to represent the position (2D) of any object.
 */
public class Position {
    Point point;
    Direction direction;

    /**
     * @param x         value of x coordinate
     * @param y         value of x coordinate
     * @param direction {@link Direction}
     */
    public Position(int x, int y, Direction direction) {
        this.point = new Point(x, y);
        this.direction = direction;
    }

    @Override
    public String toString() {
        return (int) point.getX() + ", " + (int) point.getY() + ", " + direction.value();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (point != null ? !point.equals(position.point) : position.point != null) return false;
        return direction != null ? direction.equals(position.direction) : position.direction == null;
    }

    @Override
    public int hashCode() {
        int result = point != null ? point.hashCode() : 0;
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        return result;
    }
}
