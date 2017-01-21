package com.example.simulaton.models;

/**
 * Created by rajib.khan on 1/21/17.
 */
public class Direction {
    private DirectionEnum direction;
    private Direction left;
    private Direction right;

    public Direction(DirectionEnum direction) {
        this.direction = direction;
    }

    public void setLeft(Direction left) {
        this.left = left;
    }

    public void setRight(Direction right) {
        this.right = right;
    }

    /**
     * used to get the {@link Direction} on left (-90 degrees)
     *
     * @return {@link Direction} direction
     */
    public Direction left() {
        return this.left;
    }

    /**
     * used to get the {@link Direction} on right (+90 degrees)
     *
     * @return {@link Direction} direction
     */
    public Direction right() {
        return this.right;
    }

    /**
     * @return {@link DirectionEnum}
     */
    public DirectionEnum value() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Direction direction1 = (Direction) o;

        if (!direction.name().equals(direction1.direction.name())) return false;
        if (left != null ? !left.value().equals(direction1.left.value()) : direction1.left != null) return false;
        return right != null ? right.value().equals(direction1.right.value()) : direction1.right == null;
    }


}
