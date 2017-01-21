package com.example.simulaton.commands;

import com.example.simulaton.exceptions.InvalidCommnadException;
import com.example.simulaton.models.Position;

/**
 * Created by rajib.khan on 1/21/17.
 */

@FunctionalInterface
public interface Command {
    public Position apply(Position position) throws InvalidCommnadException;
}
