package com.example.simulaton.utils;

import com.example.simulaton.commands.CommandType;
import com.example.simulaton.exceptions.InvalidCommnadException;
import com.example.simulaton.models.Direction;
import com.example.simulaton.models.DirectionEnum;
import com.example.simulaton.models.Position;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by rajib.khan on 1/22/17.
 */
public class CommandUtilTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

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
                is(Optional.of(DirectionEnum.NORTH)));
    }

    @Test
    public void getDirectionFromString_should_return_SOUTH() {
        assertThat("SOUTH should return SOUTH", CommandUtil.getDirectionFromString("SOUTH"),
                is(Optional.of(DirectionEnum.SOUTH)));
    }

    @Test
    public void getDirectionFromString_should_return_EAST() {
        assertThat("EAST should return EAST", CommandUtil.getDirectionFromString("EAST"),
                is(Optional.of(DirectionEnum.EAST)));
    }

    @Test
    public void getDirectionFromString_should_return_WEST() {
        assertThat("NORTH should return WEST", CommandUtil.getDirectionFromString("NORTH"),
                is(Optional.of(DirectionEnum.NORTH)));
    }

    @Test
    public void getDirectionFromString_should_return_empty() {
        assertThat("abcd should return empty", CommandUtil.getDirectionFromString("abcd"),
                is(Optional.empty()));
    }


    @Test
    public void parsePlaceCommand_should_return_position_NORTH() throws InvalidCommnadException {
        assertThat("should return (0,0,NORTH)", CommandUtil.parsePlaceCommand("PLACE    0 , 0, NORTH"),
                is(new Position(0,0, new Direction(DirectionEnum.NORTH))));
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
}