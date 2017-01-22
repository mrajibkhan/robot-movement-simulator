package com.example.simulaton;

import com.example.simulaton.controllers.RobotController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationTests {

    @InjectMocks
    Application application;

    @Mock
    RobotController robotController;

    @Test
    public void runTest() {
        application.run(new String[]{""});
        verify(robotController, times(1)).run();
    }

}
