package com.example.simulaton.utils;

import com.example.simulaton.commands.CommandType;
import com.example.simulaton.exceptions.InvalidCommnadException;
import com.example.simulaton.models.Direction;
import com.example.simulaton.models.DirectionEnum;
import com.example.simulaton.models.Directions;
import com.example.simulaton.models.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by rajib.khan on 1/22/17.
 */
public class CommandUtil {

    static Directions directions = new Directions();

    public static Map<CommandType, Optional<Position>> parseCommand(String inputStr) throws InvalidCommnadException {
        Map<CommandType, Optional<Position>> commandMap = new HashMap<CommandType, Optional<Position>>();
        Optional<CommandType> command = getCommandFromString(inputStr);
        Optional<Position> position = Optional.empty();

        if(!command.isPresent()) {
            return commandMap;
        } else if (command.get().equals(CommandType.PLACE)) {
            position = Optional.of(parsePlaceCommand(inputStr));
        }

        commandMap.put(command.get(), position);

        return commandMap;
    }


    public static Position parsePlaceCommand(String placeCommandStr) throws InvalidCommnadException {
        if (placeCommandStr == null || placeCommandStr.isEmpty() ||
                !placeCommandStr.trim().toUpperCase().startsWith(CommandType.PLACE.name())) {
            throw new InvalidCommnadException("Invalid PLACE command");
        }

        int x = 0, y = 0;
        Optional<DirectionEnum> direction = Optional.empty();

        placeCommandStr = placeCommandStr.trim();
        String positionStr = placeCommandStr.substring(CommandType.PLACE.value().length()).trim();

        if (positionStr.isEmpty())
            throw new InvalidCommnadException("Invalid PLACE command. Check values for X,Y and F");

        positionStr = positionStr.replaceAll(" ", "");
        String[] positionArray = positionStr.split(",", 3);
        if (positionArray.length < 3) {
            throw new InvalidCommnadException("Invalid PLACE command. Check values for X,Y and F");
        }

        try {
            x = Integer.valueOf(positionArray[0]);
            y = Integer.valueOf(positionArray[1]);
        } catch (NumberFormatException nfEx) {
            throw new InvalidCommnadException("Invalid PLACE command. Wrong X = " + positionArray[0] + " or Y = " + positionArray[1]
                    + ". X and Y should be Integers");
        }

        direction = getDirectionFromString(positionArray[2]);
        if (!direction.isPresent()) {
            throw new InvalidCommnadException("Invalid PLACE command: wrong direction, Direction should be "
                    + directions.getDirectionsAsString());
        }

        return new Position(x, y, new Direction(direction.get()));

    }

    public static Optional<DirectionEnum> getDirectionFromString(String directionStr) {
        if (directionStr == null || directionStr.isEmpty()) {
            return Optional.empty();
        }

        return Arrays.asList(DirectionEnum.values()).stream()
                .filter((directionEnum) -> directionStr.toUpperCase().startsWith(directionEnum.name()))
                .findAny();
    }

    public static Optional<CommandType> getCommandFromString(String commandStr) {
        if (commandStr == null || commandStr.isEmpty()) {
            return Optional.empty();
        }

        Optional<CommandType> commandType = Arrays.asList(CommandType.values()).stream()
                .filter((commandEnum) -> commandStr.toUpperCase().startsWith(commandEnum.name()))
                .findAny();

        // check x,y and F values for PLACE command
        if (commandType.isPresent() && commandType.get().equals(CommandType.PLACE)) {

        }

        return commandType;
    }
}
