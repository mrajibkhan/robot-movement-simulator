package com.example.simulaton.utils;

import com.example.simulaton.commands.CommandType;
import com.example.simulaton.exceptions.InvalidCommnadException;
import com.example.simulaton.models.DirectionEnum;
import com.example.simulaton.models.Directions;
import com.example.simulaton.models.Position;
import com.example.simulaton.models.Robot;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by rajib.khan on 1/22/17.
 */
public class CommandUtilTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private Directions directions = new Directions();

    @Test
    public void getCommandFromString_should_return_LEFT() {
        assertThat("left should return LEFT", CommandUtil.getCommandFromString("left"),
                is(Optional.of(CommandType.LEFT)));
    }

    @Test
    public void getCommandFromString_should_return_RIGHT() {
        assertThat("Right should return RIGHT", CommandUtil.getCommandFromString("RIGHT"),
                is(Optional.of(CommandType.RIGHT)));
    }

    @Test
    public void getCommandFromString_should_return_MOVE() {
        assertThat("move should return MOVE", CommandUtil.getCommandFromString("move"),
                is(Optional.of(CommandType.MOVE)));
    }

    @Test
    public void getCommandFromString_should_return_PLACE() {
        assertThat("place should return PLACE", CommandUtil.getCommandFromString("place"),
                is(Optional.of(CommandType.PLACE)));
    }

    @Test
    public void getCommandFromString_should_return_REPORT() {
        assertThat("report should return REPORT", CommandUtil.getCommandFromString("REPORT"),
                is(Optional.of(CommandType.REPORT)));
    }

    @Test
    public void getCommandFromString_should_return_empty() {
        assertThat("\\\\xyz!! should return empty", CommandUtil.getCommandFromString("\\\\xyz!!"),
                is(Optional.empty()));
    }

    @Test
    public void getDirectionFromString_should_return_NORTH() {
        assertThat("NORTH should return NORTH", CommandUtil.getDirectionFromString("NORTH"),
                is(Optional.of(directions.get(DirectionEnum.NORTH))));
    }

    @Test
    public void getDirectionFromString_should_return_SOUTH() {
        assertThat("SOUTH should return SOUTH", CommandUtil.getDirectionFromString("SOUTH"),
                is(Optional.of(directions.get(DirectionEnum.SOUTH))));
    }

    @Test
    public void getDirectionFromString_should_return_EAST() {
        assertThat("EAST should return EAST", CommandUtil.getDirectionFromString("EAST"),
                is(Optional.of(directions.get(DirectionEnum.EAST))));
    }

    @Test
    public void getDirectionFromString_should_return_WEST() {
        assertThat("NORTH should return WEST", CommandUtil.getDirectionFromString("NORTH"),
                is(Optional.of(directions.get(DirectionEnum.NORTH))));
    }

    @Test
    public void getDirectionFromString_should_return_empty() {
        assertThat("abcd should return empty", CommandUtil.getDirectionFromString("abcd"),
                is(Optional.empty()));
    }


    @Test
    public void parsePlaceCommand_should_return_position_NORTH() throws InvalidCommnadException {
        assertThat("should return (0,0,NORTH)", CommandUtil.parsePlaceCommand("PLACE    0 , 0, NORTH"),
                is(new Position(0, 0, directions.get((DirectionEnum.NORTH)))));
    }

    @Test
    public void parsePlaceCommand_should_throw_InvalidCommnadException_when_unknown_direction() throws InvalidCommnadException {
        thrown.expect(InvalidCommnadException.class);
        thrown.expectMessage(containsString("wrong direction"));
        CommandUtil.parsePlaceCommand("PLACE    0 , 0, ABCD");
    }

    @Test
    public void parsePlaceCommand_should_throw_InvalidCommnadException_when_empty_direction() throws InvalidCommnadException {
        thrown.expect(InvalidCommnadException.class);
        thrown.expectMessage(containsString("Check values for X,Y and F"));
        CommandUtil.parsePlaceCommand("PLACE    0 , 0");
    }

    @Test
    public void parsePlaceCommand_should_throw_InvalidCommnadException_when_invalid_X() throws InvalidCommnadException {
        thrown.expect(InvalidCommnadException.class);
        thrown.expectMessage(containsString("X and Y should be Integers"));
        CommandUtil.parsePlaceCommand("PLACE    A , 0, NORTH");
    }

    @Test
    public void parsePlaceCommand_should_throw_InvalidCommnadException_when_invalid_Y() throws InvalidCommnadException {
        thrown.expect(InvalidCommnadException.class);
        thrown.expectMessage(containsString("X and Y should be Integers"));
        CommandUtil.parsePlaceCommand("PLACE    1 , B, SOUTH");
    }

    @Test
    public void rotate_left_should_change_robots_direction_from_NORTH_to_WEST() {
        Position currentPosition = new Position(0, 0, new Directions().get(DirectionEnum.NORTH));
        Position rotatedPosition = new Position(0, 0, new Directions().get(DirectionEnum.WEST));
        Robot robot = new Robot("test1");
        robot.setCurrentPosition(currentPosition);
        boolean hasRotated = CommandUtil.rotate(Optional.of(robot), CommandType.LEFT);
        assertThat("has rotated should return true", hasRotated, is(true));
        assertThat("current direction should be WEST", robot.getCurrentPosition(), is(rotatedPosition));
    }

    @Test
    public void rotate_right_should_change_robots_direction_from_NORTH_to_EAST() {
        Position currentPosition = new Position(0, 0, new Directions().get(DirectionEnum.NORTH));
        Position rotatedPosition = new Position(0, 0, new Directions().get(DirectionEnum.EAST));
        Robot robot = new Robot("test1");
        robot.setCurrentPosition(currentPosition);
        boolean hasRotated = CommandUtil.rotate(Optional.of(robot), CommandType.RIGHT);
        assertThat("has rotated should return true", hasRotated, is(true));
        assertThat("current direction should be EAST", robot.getCurrentPosition(), is(rotatedPosition));
    }

    @Test
    public void move_should_increase_robots_y_position_when_facing_NORTH() {
        Position currentPosition = new Position(0, 0, new Directions().get(DirectionEnum.NORTH));
        Position movedPosition = new Position(0, 1, new Directions().get(DirectionEnum.NORTH));
        Robot robot = new Robot("test1");
        robot.setCurrentPosition(currentPosition);
        boolean hasMoved = CommandUtil.move(Optional.of(robot));
        assertThat("hasMoved should return true", hasMoved, is(true));
        assertThat("current position y should increase", robot.getCurrentPosition(), is(movedPosition));
    }

    @Test
    public void move_should_decrease_robots_x_position_when_facing_SOUTH() {
        Position currentPosition = new Position(0, 5, new Directions().get(DirectionEnum.SOUTH));
        Position movedPosition = new Position(0, 4, new Directions().get(DirectionEnum.SOUTH));
        Robot robot = new Robot("test1");
        robot.setCurrentPosition(currentPosition);
        boolean hasMoved = CommandUtil.move(Optional.of(robot));
        assertThat("hasMoved should return true", hasMoved, is(true));
        assertThat("current position y should decrease", robot.getCurrentPosition(), is(movedPosition));
    }

    @Test
    public void move_should_increase_robots_x_position_when_facing_EAST() {
        Position currentPosition = new Position(0, 0, new Directions().get(DirectionEnum.EAST));
        Position movedPosition = new Position(1, 0, new Directions().get(DirectionEnum.EAST));
        Robot robot = new Robot("test1");
        robot.setCurrentPosition(currentPosition);
        boolean hasMoved = CommandUtil.move(Optional.of(robot));
        assertThat("hasMoved should return true", hasMoved, is(true));
        assertThat("current position x should increase", robot.getCurrentPosition(), is(movedPosition));
    }

    @Test
    public void move_should_decrease_robots_x_position_when_facing_WEST() {
        Position currentPosition = new Position(5, 0, new Directions().get(DirectionEnum.WEST));
        Position movedPosition = new Position(4, 0, new Directions().get(DirectionEnum.WEST));
        Robot robot = new Robot("test1");
        robot.setCurrentPosition(currentPosition);
        boolean hasMoved = CommandUtil.move(Optional.of(robot));
        assertThat("hasMoved should return true", hasMoved, is(true));
        assertThat("current position x should decrease", robot.getCurrentPosition(), is(movedPosition));
    }
}