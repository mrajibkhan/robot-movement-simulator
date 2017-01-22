package com.example.simulaton.models;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by rajib.khan on 1/21/17.
 * Directions is the container of all 4 {@link Direction} (north, south, east and west)
 * Each {@link Direction} is linked with left and right {@link Direction}.
 * For example, {@link DirectionEnum.NORTH} has {@link DirectionEnum.WEST} at 'left' and {@link DirectionEnum.EAST}
 * at 'right'
 */
@Component
public class Directions {
    // contains all Directon
    protected final Map<DirectionEnum, Direction> directionMap;

    public Directions() {
        directionMap = new HashMap<DirectionEnum, Direction>();
        init();
    }

    /**
     * returns {@link Direction} for {@link DirectionEnum}
     *
     * @param {@link DirectionEnum} directionEnum
     * @return {@link Direction}
     */
    public Direction get(DirectionEnum directionEnum) {
        return directionMap.get(directionEnum);
    }

    /**
     * initialises all {@link Direction}s
     */
    private void init() {
        Direction north = new Direction(DirectionEnum.NORTH);
        Direction east = new Direction(DirectionEnum.EAST);
        Direction south = new Direction(DirectionEnum.SOUTH);
        Direction west = new Direction(DirectionEnum.WEST);

        north.setLeft(west);
        north.setRight(east);

        east.setLeft(north);
        east.setRight(south);

        south.setLeft(east);
        south.setRight(west);

        west.setLeft(south);
        west.setRight(north);

        directionMap.put(DirectionEnum.NORTH, north);
        directionMap.put(DirectionEnum.EAST, east);
        directionMap.put(DirectionEnum.SOUTH, south);
        directionMap.put(DirectionEnum.WEST, west);
    }

    public String getDirectionsAsString() {
        return directionMap.keySet().stream().map(i-> i.name()).sorted().collect(Collectors.joining(", "));
    }

}
