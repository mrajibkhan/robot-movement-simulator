package com.example.simulaton;

import com.example.simulaton.services.UserInteractionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RobotApplicationTests {

    @InjectMocks
    RobotApplication application;

    @Mock
    UserInteractionService userInteractionService;

    @Test
    public void runTest() {
        application.run(new String[]{""});
        verify(userInteractionService, times(1)).readUserInput();
    }

}
