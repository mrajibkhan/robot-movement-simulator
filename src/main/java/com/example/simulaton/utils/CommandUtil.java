package com.example.simulaton.utils;

import com.example.simulaton.commands.CommandType;
import com.example.simulaton.exceptions.FalloffException;
import com.example.simulaton.exceptions.InvalidCommnadException;
import com.example.simulaton.models.*;
import com.example.simulaton.models.Robot;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by rajib.khan on 1/22/17.
 */
public class CommandUtil {

    static Directions directions = new Directions();

    public static Map<CommandType, Optional<Position>> parseCommand(String inputStr) throws InvalidCommnadException {
        Map<CommandType, Optional<Position>> commandMap = new HashMap<CommandType, Optional<Position>>();
        Optional<CommandType> command = getCommandFromString(inputStr);
        Optional<Position> position = Optional.empty();

        if (!command.isPresent()) {
            return commandMap;
        } else if (command.get().equals(CommandType.PLACE)) {
            position = Optional.of(parsePlaceCommand(inputStr));
        }

        commandMap.put(command.get(), position);

        return commandMap;
    }


    /**
     * Parse user input for PLACE command. PLACE command should be associated
     * with X (integer), Y (integer) and F (NORTH|SOUTH|EAST|WEST). For valid placeCommandStr argument
     * {@link Position} is created and returned
     *
     * @param placeCommandStr
     * @return position {@link Position}
     * @throws InvalidCommnadException if placeCommandStr doesn't contain valid PLACE  X, Y or F values
     */
    public static Position parsePlaceCommand(String placeCommandStr) throws InvalidCommnadException {
        if (placeCommandStr == null || placeCommandStr.isEmpty() ||
                !placeCommandStr.trim().toUpperCase().startsWith(CommandType.PLACE.name())) {
            throw new InvalidCommnadException("Invalid PLACE command");
        }

        int x = 0, y = 0;
        Optional<Direction> direction = Optional.empty();

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

        return new Position(x, y, direction.get());

    }

    public static Optional<Direction> getDirectionFromString(String directionStr) {
        if (directionStr == null || directionStr.isEmpty()) {
            return Optional.empty();
        }

        Optional<DirectionEnum> directionEnumOpt = Arrays.asList(DirectionEnum.values()).stream()
                .filter((directionEnum) -> directionStr.toUpperCase().startsWith(directionEnum.name()))
                .findAny();

        return (directionEnumOpt.isPresent()) ? Optional.of(directions.get(directionEnumOpt.get())) : Optional.empty();
    }

    /**
     * @param commandStr
     * @return
     */
    public static Optional<CommandType> getCommandFromString(String commandStr) {
        if (commandStr == null || commandStr.isEmpty()) {
            return Optional.empty();
        }

        Optional<CommandType> commandType = Arrays.asList(CommandType.values()).stream()
                .filter((commandEnum) -> commandStr.toUpperCase().startsWith(commandEnum.name()))
                .findAny();

        return commandType;
    }

    /**
     * Rotates {@link Robot}s {@link Direction} to Left or Right
     *
     * @param robot
     * @param commandType
     * @return
     */
    public static boolean rotate(Optional<Robot> robot, CommandType commandType) {
        if (robot == null || !robot.isPresent()
                || !(commandType.equals(CommandType.LEFT) || commandType.equals(CommandType.RIGHT))) {
            return false;
        }

        Position currentPosition = robot.get().getCurrentPosition();
        if (currentPosition == null) return false;

        Direction newDirection = commandType.equals(CommandType.LEFT) ?
                currentPosition.getDirection().left() : currentPosition.getDirection().right();
        robot.get().setCurrentPosition(new Position(currentPosition.getPoint().x, currentPosition.getPoint().y, newDirection));

        return true;
    }

    /**
     * Places the {@link Robot} at the specified {@link Position}
     * if the specified {@link Position} outside of the table
     *
     * @param robot
     * @param position {@link Position} to place the robot at
     * @param min      minimum allowed location ({@link Point})
     * @param max      maximum allowed location ({@link Point})
     * @return
     * @throws FalloffException if move causes the robot to fall off the table
     */
    public static boolean place(Optional<Robot> robot, Optional<Position> position, Point min, Point max) throws FalloffException {
        if (!(robot.isPresent() && position.isPresent())) {
            return false;
        }
        Point placingPoint = position.get().getPoint();

        if (!isPositionValid(placingPoint.x, placingPoint.y, min, max)) {
            throw new FalloffException("Invalid placing (X = " + placingPoint.x + ", Y = " + placingPoint.y +
                    "). Prevented falling off the table.");
        }

        robot.get().setCurrentPosition(position.get());

        return true;
    }

    /**
     * Moves the robot 1 unit in the current facing (north, south, east, west)
     *
     * @param robot
     * @param min   minimum allowed location ({@link Point})
     * @param max   maximum allowed location ({@link Point})
     * @return
     * @throws FalloffException if move causes the robot to fall off the table
     */
    public static boolean move(Optional<Robot> robot, Point min, Point max) throws FalloffException {
        if (robot == null || !robot.isPresent()) return false;

        Position currentPosition = robot.get().getCurrentPosition();
        if (currentPosition == null) return false;

        Direction direction = currentPosition.getDirection();
        Point currentPoint = currentPosition.getPoint();

        int x = currentPoint.x;
        int y = currentPoint.y;

        switch (direction.value()) {
            case NORTH:
                y++;
                break;
            case SOUTH:
                y--;
                break;
            case EAST:
                x++;
                break;
            case WEST:
                x--;
                break;
        }

        if (!isPositionValid(x, y, min, max)) {
            throw new FalloffException("Invalid move (X = " + x + ", Y = " + y +
                    "). Prevented falling off the table.");
        }

        robot.get().setCurrentPosition(new Position(x, y, direction));

        return true;
    }

    public static boolean isPositionValid(int x, int y, Point min, Point max) {
        if (x >= min.x && x <= max.x
                && y >= min.y && y <= max.y) {
            return true;
        }

        return false;
    }
}
