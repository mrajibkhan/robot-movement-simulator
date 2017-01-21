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
        return "Position=(" + point.getX() + ", " + point.getY() + ", " + direction.value() + ")";
    }

}
