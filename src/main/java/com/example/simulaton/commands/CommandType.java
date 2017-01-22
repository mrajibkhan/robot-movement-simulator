package com.example.simulaton.commands;

/**
 * Created by rajib.khan on 1/21/17.
 */
public enum CommandType {
    PLACE,
    MOVE,
    LEFT,
    RIGHT,
    REPORT,
    QUIT;

    public static CommandType fromValue(String v) {
        return valueOf(v);
    }

    public String value() {
        return name();
    }
}
