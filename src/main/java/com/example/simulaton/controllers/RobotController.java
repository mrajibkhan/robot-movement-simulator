package com.example.simulaton.controllers;

import com.example.simulaton.commands.Command;
import com.example.simulaton.commands.CommandFactory;
import com.example.simulaton.commands.CommandType;
import com.example.simulaton.exceptions.InvalidCommnadException;
import com.example.simulaton.models.Position;
import com.example.simulaton.models.Robot;
import com.example.simulaton.services.UserInteractionService;
import com.example.simulaton.utils.CommandUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by rajib.khan on 1/21/17.
 * {@link RobotController} is used by {@link com.example.simulaton.Application} for controlling
 * all activities of the {@link com.example.simulaton.models.Robot}.
 */
@Component
public class RobotController {

    protected Optional<Robot> robot = Optional.empty();
    Logger logger = LoggerFactory.getLogger(RobotController.class);
    CommandFactory commandFactory = CommandFactory.init();
    ;
    UserInteractionService userInteractionService;

    public void run() {
        // show welcome message at the beginning of the application
        userInteractionService.showWelcomeMessage();
        // show help message to show accepted commands
        userInteractionService.showHelpMessage();
        // start interacting with user (i.e. take user commands)
        String inputString = "";
        Map<CommandType, Optional<Position>> commandMap = new HashMap<CommandType, Optional<Position>>();
        ;
        while (true) {
            inputString = userInteractionService.readUserInput();
            try {
                commandMap = CommandUtil.parseCommand(inputString);
            } catch (InvalidCommnadException icEx) {
                logger.error("ERROR: " + icEx.getMessage());
                continue;
            }
            if (commandMap.isEmpty()) {
                logger.warn("You entered an invalid command: " + inputString + ". Please enter a valid command.");
                continue;
            }
            CommandType command = (CommandType) commandMap.keySet().toArray()[0];
            Optional<Position> position = commandMap.get(command);
            // QUIT command is important to put here to allow breaking the loop
            if (command.equals(CommandType.QUIT)) {
                executeCommand(command, position, robot);
                break;
            }

            logger.info("You entered: " + command.name() + (position.isPresent() ? " " + position.get() : ""));

            if (!robot.isPresent()) {
                if (command.equals(CommandType.PLACE)) {
                    logger.info("Robot is placed on the table. Position: " + position.get());
                    placeRobotOnTable(position.get());
                } else {
                    logger.warn("To start please enter PLACE e.g. PLACE 0,0,NORTH");
                    continue;
                }
            }

            executeCommand(command, position, robot);
        }
    }

    /**
     * executes commands according to command implementation {@link CommandFactory#init()}
     *
     * @param commandType
     * @param position
     * @param robot
     */
    public void executeCommand(CommandType commandType, Optional<Position> position, Optional<Robot> robot) {
        try {
            Command command = commandFactory.getCommand(commandType);
            command.apply(position, robot);
        } catch (InvalidCommnadException icEx) {
            logger.error("Error: couldn't execute command " + commandType.name() + " - " + icEx.getMessage());
        }
    }

    /**
     * places the robot on the table at the provided {@link Position}.
     *
     * @param position
     */
    protected void placeRobotOnTable(Position position) {
        robot = Optional.of(new Robot("1")); //set ID for robot (any value as this is the only robot!)
        robot.get().setStartPosition(position);
        robot.get().setCurrentPosition(position);
    }

    @Autowired
    public void setUserInteractionService(UserInteractionService userInteractionService) {
        this.userInteractionService = userInteractionService;
    }

}
