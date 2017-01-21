package com.example.simulaton;

import com.example.simulaton.services.UserInteractionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RobotApplication implements CommandLineRunner {

    Logger log = LoggerFactory.getLogger(RobotApplication.class);

    UserInteractionService userInteractionService;

    public static void main(String[] args) {
        SpringApplication.run(RobotApplication.class, args);
    }

    @Autowired
    public void setUserInteractionService(UserInteractionService userInteractionService) {
        this.userInteractionService = userInteractionService;
    }

    @Override
    public void run(String... args) {
        // show welcome message at the beginning of the application
        userInteractionService.showWelcomeMessage();
        // show help message to show accepted commands
        userInteractionService.showHelpMessage();
        // start interacting with user (i.e. take user commands)
        userInteractionService.readUserInput();
    }
}
