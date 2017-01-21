package com.example.simulaton.services;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

/**
 * Created by rajib.khan on 1/21/17.
 */
public class UserInteractionServiceTest {
    static final String WELCOME_MSG = "Welcome!";
    static final String HELP_MSG = "Help Message";
    static final String USER_INPUT = "PLACE 0,0,NORTH\n";
    UserInteractionService userInteractionService;
    ByteArrayOutputStream outSpy;

    @Before
    public void setUp() {
        outSpy = new ByteArrayOutputStream();
        userInteractionService = new UserInteractionService();
        userInteractionService.setWelcomeMessage(WELCOME_MSG);
        userInteractionService.setHelpMessage(HELP_MSG);
        userInteractionService.setOutput(new PrintStream(outSpy));
        userInteractionService.setScanner(new Scanner(USER_INPUT));
    }

    @Test
    public void showWelcomeMessage_should_print_welcome_message() {
        userInteractionService.showWelcomeMessage();
        assertThat("should print welcome message", outSpy.toString(), containsString(WELCOME_MSG));
    }

    @Test
    public void showHelpMessage_should_print_help_message() {
        userInteractionService.showHelpMessage();
        assertThat("should print help message", outSpy.toString(), containsString(HELP_MSG));
    }

    @Test
    public void readUserInput_should_print_user_input() {
        userInteractionService.readUserInput();
        assertThat("should print user input message", outSpy.toString(), containsString(USER_INPUT));
    }

}