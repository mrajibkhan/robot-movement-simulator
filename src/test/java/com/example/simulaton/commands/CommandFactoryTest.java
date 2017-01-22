package com.example.simulaton.commands;

import com.example.simulaton.exceptions.InvalidCommnadException;
import com.example.simulaton.models.Direction;
import com.example.simulaton.models.DirectionEnum;
import com.example.simulaton.models.Position;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * Created by rajib.khan on 1/21/17.
 */
public class CommandFactoryTest {

    CommandFactory commandFactory = CommandFactory.init();

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    private static final String sortedCommandsStr = "LEFT, MOVE, PLACE, QUIT, REPORT, RIGHT";
    private Position position = new Position(0,0,new Direction(DirectionEnum.NORTH));

    @Test
    public void getCommandsAsString() {
        assertThat(commandFactory.getCommandsAsString(), is(sortedCommandsStr));
    }

}