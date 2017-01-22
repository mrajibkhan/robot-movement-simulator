package com.example.simulaton.controllers;

import com.example.simulaton.commands.CommandType;
import com.example.simulaton.exceptions.InvalidCommnadException;
import com.example.simulaton.models.DirectionEnum;
import com.example.simulaton.models.Directions;
import com.example.simulaton.models.Position;
import com.example.simulaton.models.Robot;
import com.example.simulaton.services.UserInteractionService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.rule.OutputCapture;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

/**
 * Created by rajib.khan on 1/21/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class RobotControllerTest {

    @Rule
    public OutputCapture capture = new OutputCapture();
    @InjectMocks
    RobotController robotController;
    @Mock
    UserInteractionService userInteractionService;

    @Test
    public void run_should_place_robot_on_table() {
        Mockito.when(userInteractionService.readUserInput()).thenReturn("PLACE 0,0,NORTH").thenReturn("QUIT");
        robotController.run();
        assertThat(capture.toString(), containsString("You entered: PLACE 0, 0, NORTH"));
        assertThat(capture.toString(), containsString("Robot is placed on the table. Position: 0, 0, NORTH"));
    }

    @Test
    public void run_should_ignore_all_commands_except_PLACE_at_start() {
        Mockito.when(userInteractionService.readUserInput()).thenReturn("LEFT")
                .thenReturn("RIGHT").thenReturn("MOVE").thenReturn("REPORT").thenReturn("QUIT");
        robotController.run();
        assertThat(capture.toString(), containsString("To start please enter PLACE e.g. PLACE 0,0,NORTH"));
        assertThat(capture.toString(), not(containsString("Robot is placed on the table. Position: 0, 0, NORTH")));
    }

    @Test
    public void run_should_allow_PLACE_multiple_times() {
        Mockito.when(userInteractionService.readUserInput())
                .thenReturn("PLACE 0,0,NORTH")
                .thenReturn("PLACE 1, 2, SOUTH")
                .thenReturn("QUIT");
        robotController.run();
        assertThat(capture.toString(), not(containsString("To start please enter PLACE e.g. PLACE 0,0,NORTH")));
        assertThat(capture.toString(), containsString("You entered: PLACE 0, 0, NORTH"));
        assertThat(capture.toString(), containsString("You entered: PLACE 1, 2, SOUTH"));
    }

    @Test
    public void run_should_change_robot_position_for_PLACE_command() {
        Position startPosition = new Position(0, 0, new Directions().get(DirectionEnum.NORTH));
        Position currentPosition = new Position(5, 5, new Directions().get(DirectionEnum.WEST));
        Mockito.when(userInteractionService.readUserInput())
                .thenReturn("PLACE 0,0,NORTH")
                .thenReturn("PLACE 5, 5, WEST")
                .thenReturn("QUIT");
        robotController.run();
        assertThat(robotController.robot.get().getStartPosition(), is(startPosition));
        assertThat(robotController.robot.get().getCurrentPosition(), is(currentPosition));
    }

    @Test
    public void run_should_display_robot_position_for_REPORT_command() {
        Position startPosition = new Position(0, 0, new Directions().get(DirectionEnum.NORTH));
        Position currentPosition = new Position(5, 5, new Directions().get(DirectionEnum.WEST));
        Mockito.when(userInteractionService.readUserInput())
                .thenReturn("PLACE 5, 5, WEST")
                .thenReturn("REPORT")
                .thenReturn("QUIT");
        robotController.run();
        assertThat(capture.toString(), containsString("Robot position: 5, 5, WEST"));
    }

    @Test
    public void run_should_change_robot_position_NORTH_to_WEST_for_LEFT_command() {
        Position expectedPosition = new Position(0, 0, new Directions().get(DirectionEnum.WEST));
        Mockito.when(userInteractionService.readUserInput())
                .thenReturn("PLACE 0, 0, NORTH")
                .thenReturn("LEFT")
                .thenReturn("QUIT");
        robotController.run();
        assertThat(robotController.robot.get().getCurrentPosition(), is(expectedPosition));
    }

    @Test
    public void run_should_change_robot_position_SOUTH_to_WEST_for_RIGHT_command() {
        Position expectedPosition = new Position(2, 3, new Directions().get(DirectionEnum.WEST));
        Mockito.when(userInteractionService.readUserInput())
                .thenReturn("PLACE 2, 3, SOUTH")
                .thenReturn("RIGHT")
                .thenReturn("QUIT");
        robotController.run();
        assertThat(robotController.robot.get().getCurrentPosition(), is(expectedPosition));
    }

    @Test
    public void run_should_display_not_implemented_for_move_afer_start() {
        Mockito.when(userInteractionService.readUserInput())
                .thenReturn("PLACE 0,0,NORTH")
                .thenReturn("MOVE")
                .thenReturn("QUIT");
        robotController.run();
        assertThat(capture.toString(), containsString("command not implemented: MOVE"));
    }

    @Test
    public void placeRobotOnTable_should_initialise_robot() {
        Position startPosition = new Position(0, 0, new Directions().get(DirectionEnum.NORTH));
        robotController.placeRobotOnTable(startPosition);
        assertThat(robotController.robot.isPresent(), is(true));
    }

    @Test
    public void placeRobotOnTable_should_set_robot_start_position() {
        Position startPosition = new Position(0, 0, new Directions().get(DirectionEnum.NORTH));
        robotController.placeRobotOnTable(startPosition);
        assertThat(robotController.robot.get().getStartPosition(), is(startPosition));
    }

    @Test
    public void placeRobotOnTable_should_set_robot_current_position() {
        Position startPosition = new Position(0, 0, new Directions().get(DirectionEnum.NORTH));
        robotController.placeRobotOnTable(startPosition);
        assertThat(robotController.robot.get().getCurrentPosition(), is(startPosition));
    }

    @Test
    public void executePositionCommand_should_display_position() throws InvalidCommnadException {
        Position position = new Position(0, 0, new Directions().get(DirectionEnum.NORTH));
        Robot robot = new Robot("test1");
        robot.setCurrentPosition(position);
        robotController.executeCommand(CommandType.REPORT, Optional.of(position), Optional.of(robot));
        assertThat(capture.toString(), containsString("Robot position: 0, 0, NORTH"));
    }

    @Test
    public void executePositionCommand_should_position() throws InvalidCommnadException {
        robotController.executeCommand(CommandType.REPORT, Optional.empty(), Optional.empty());
        assertThat(capture.toString(), containsString("Robot position: unknown"));
    }

}