package com.example.simulaton.controllers;

import com.example.simulaton.models.Robot;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by rajib.khan on 1/21/17.
 * {@link RobotController} is used by {@link com.example.simulaton.Application} for controlling
 * all activities of the {@link com.example.simulaton.models.Robot}.
 */
@Component
public class RobotController {

    Optional<Robot> robot = Optional.empty();

}
