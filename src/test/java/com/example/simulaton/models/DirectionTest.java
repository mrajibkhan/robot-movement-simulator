package com.example.simulaton.models;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * Created by rajib.khan on 1/21/17.
 */
public class DirectionTest {

    @Test
    public void value_should_return_NORTH_when_created_for_NORTH() {
        Direction direction = new Direction(DirectionEnum.NORTH);
        assertThat("should return NORTH", direction.value(), is(DirectionEnum.NORTH));
    }

    @Test
    public void left_of_NORTH_should_return_WEST() {
        Direction direction = new Direction(DirectionEnum.NORTH);
        direction.setLeft(new Direction(DirectionEnum.WEST));
        assertThat("should return WEST", direction.left().value(), is(DirectionEnum.WEST));
    }

    @Test
    public void right_of_NORTH_should_return_EAST() {
        Direction direction = new Direction(DirectionEnum.NORTH);
        direction.setRight(new Direction(DirectionEnum.EAST));
        assertThat("should return EAST", direction.right().value(), is(DirectionEnum.EAST));
    }

    @Test
    public void equals_should_return_true_when_compared() {
        Direction direction = new Direction(DirectionEnum.NORTH);
        direction.setLeft(new Direction(DirectionEnum.WEST));
        direction.setRight(new Direction(DirectionEnum.EAST));

        Direction direction1 = new Direction(DirectionEnum.NORTH);
        direction1.setLeft(new Direction(DirectionEnum.WEST));
        direction1.setRight(new Direction(DirectionEnum.EAST));

        assertThat(direction.equals(direction1), is(true));
    }

}