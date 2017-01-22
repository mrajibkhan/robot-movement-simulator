package com.example.simulaton.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by rajib.khan on 1/21/17.
 */
@Component
public class UserInteractionService {
    protected String welcomeMessage;
    protected String helpMessage;
    Scanner scanner;
    PrintStream output;

    @Autowired
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    @Autowired
    public void setOutput(PrintStream output) {
        this.output = output;
    }

    @Value("${message.welcome}")
    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    @Value("${message.help}")
    public void setHelpMessage(String helpMessage) {
        this.helpMessage = helpMessage;
    }

    public void showWelcomeMessage() {
        output.println(welcomeMessage);
    }

    public void showHelpMessage() {
        output.println(helpMessage);
    }

    public String readUserInput() {
        String inputStr = "";
//            if (scanner.hasNextLine()) {
//                inputStr = scanner.nextLine();
//            }

        return scanner.nextLine();
    }

}
