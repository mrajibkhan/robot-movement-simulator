package com.example.simulaton;

import com.example.simulaton.controllers.RobotController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    Logger log = LoggerFactory.getLogger(Application.class);

    RobotController robotController;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    public void setRobotController(RobotController robotController) {
        this.robotController = robotController;
    }

    @Override
    public void run(String... args) {
        robotController.run();
    }
}
