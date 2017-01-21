package com.example.simulaton.models;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by rajib.khan on 1/21/17.
 */
public class DirectionsTest {

    Directions directions = new Directions();

    @Test
    public void constructor_should_set_all_directions() {
        assertThat("directionMap property should not be empty", directions, notNullValue());
        assertThat("", directions.directionMap.size(), is(4));
    }

    @Test
    public void get_should_return_NORTH_when_called_with_NORTH() {
        assertThat("should return NORTH", directions.get(DirectionEnum.NORTH).value(),
                equalTo(DirectionEnum.NORTH));
    }

    @Test
    public void get_should_return_SOUTH_when_called_with_SOUTH() {
        assertThat("should return SOUTH", directions.get(DirectionEnum.SOUTH).value(),
                equalTo(DirectionEnum.SOUTH));
    }

    @Test
    public void get_should_return_EAST_when_called_with_EAST() {
        assertThat("should return EAST", directions.get(DirectionEnum.EAST).value(),
                equalTo(DirectionEnum.EAST));
    }

    @Test
    public void get_should_return_WEST_when_called_with_WEST() {
        assertThat("should return EAST", directions.get(DirectionEnum.WEST).value(),
                equalTo(DirectionEnum.WEST));
    }

    @Test
    public void get_NORTH_should_have_WEST_on_left() {
        assertThat("should return WEST as left", directions.get(DirectionEnum.NORTH).left().value(),
                equalTo(DirectionEnum.WEST));
    }

    @Test
    public void get_NORTH_should_have_EAST_on_right() {
        assertThat("should return EAST as right", directions.get(DirectionEnum.NORTH).right().value(),
                equalTo(DirectionEnum.EAST));
    }

    @Test
    public void get_EAST_should_have_NORTH_on_left() {
        assertThat("should return NORTH as left", directions.get(DirectionEnum.EAST).left().value(),
                equalTo(DirectionEnum.NORTH));
    }

    @Test
    public void get_EAST_should_have_SOUTH_on_right() {
        assertThat("should return SOUTH as right", directions.get(DirectionEnum.EAST).right().value(),
                equalTo(DirectionEnum.SOUTH));
    }

    @Test
    public void get_SOUTH_should_have_EAST_on_left() {
        assertThat("should return EAST as left", directions.get(DirectionEnum.SOUTH).left().value(),
                equalTo(DirectionEnum.EAST));
    }

    @Test
    public void get_SOUTH_should_have_WEST_on_right() {
        assertThat("should return WEST as right", directions.get(DirectionEnum.SOUTH).right().value(),
                equalTo(DirectionEnum.WEST));
    }

    @Test
    public void get_WEST_should_have_SOUTH_on_left() {
        assertThat("should return SOUTH as left", directions.get(DirectionEnum.WEST).left().value(),
                equalTo(DirectionEnum.SOUTH));
    }

    @Test
    public void get_WEST_should_have_NORTH_on_right() {
        assertThat("should return NORTH as right", directions.get(DirectionEnum.WEST).right().value(),
                equalTo(DirectionEnum.NORTH));
    }

}