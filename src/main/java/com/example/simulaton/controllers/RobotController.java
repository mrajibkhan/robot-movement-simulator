package com.example.simulaton.controllers;

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

    Logger logger = LoggerFactory.getLogger(RobotController.class);

    Optional<Robot> robot = Optional.empty();
    CommandFactory commandFactory;
    UserInteractionService userInteractionService;

    public void run () {
        commandFactory = CommandFactory.init();
        // show welcome message at the beginning of the application
        userInteractionService.showWelcomeMessage();
        // show help message to show accepted commands
        userInteractionService.showHelpMessage();
        // start interacting with user (i.e. take user commands)
        String inputString = "";
        Map<CommandType, Optional<Position>> commandMap = new HashMap<CommandType, Optional<Position>>();;
        while (true) {
            inputString = userInteractionService.readUserInput();
            try {
                commandMap = CommandUtil.parseCommand(inputString);
            } catch (InvalidCommnadException icEx) {

            }
            if (commandMap.isEmpty()) {
                logger.warn("You entered an invalid command: " + inputString + ". Please enter a valid command\n");
                continue;
            }
            CommandType command = (CommandType) commandMap.keySet().toArray()[0];
            if (command.equals(CommandType.QUIT)) {
                try {
                    commandFactory.executeCommand(command, commandMap.get(command));
                } catch (Exception ex) {

                } finally {
                    break;
                }
            }
            if(!robot.isPresent()) {
                if(command.equals(CommandType.PLACE)) {
                    robot = Optional.of(new Robot("1")); //set ID for robot (any value as this is the only robot!)
                } else {
                    logger.warn("To start please enter PLACE e.g. PLACE 0,0,NORTH\n");
                    continue;
                }
            }

            Optional<Position> position = commandMap.get(command);
            logger.info("You entered: " + command.name() + (position.isPresent()? position : ""));

        }


    }

    @Autowired
    public void setUserInteractionService(UserInteractionService userInteractionService) {
        this.userInteractionService = userInteractionService;
    }

}
