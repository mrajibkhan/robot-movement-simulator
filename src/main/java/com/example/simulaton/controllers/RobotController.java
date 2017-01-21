package com.example.simulaton.controllers;

import com.example.simulaton.commands.CommandFactory;
import com.example.simulaton.models.Robot;
import com.example.simulaton.services.UserInteractionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        while (true) {
            inputString = userInteractionService.readUserInput();
            if (isValidUserCommand(inputString)) {

            } else {
                logger.warn("You entered an invalid command: " + inputString);
            }
        }
    }

    @Autowired
    public void setUserInteractionService(UserInteractionService userInteractionService) {
        this.userInteractionService = userInteractionService;
    }

    private boolean isValidUserCommand(String inputStr) {
return false;
    }

}
