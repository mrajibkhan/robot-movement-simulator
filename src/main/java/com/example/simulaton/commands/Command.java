package com.example.simulaton.commands;

import com.example.simulaton.exceptions.InvalidCommnadException;
import com.example.simulaton.models.Position;
import com.example.simulaton.models.Robot;

import java.util.Optional;

/**
 * Created by rajib.khan on 1/21/17.
 */

@FunctionalInterface
public interface Command {
    public Optional<Position> apply(Optional<Position> position, Optional<Robot> robot) throws InvalidCommnadException;
}
