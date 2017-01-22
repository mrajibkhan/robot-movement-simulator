package com.example.simulaton.commands;

import com.example.simulaton.exceptions.InvalidCommnadException;
import com.example.simulaton.models.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by rajib.khan on 1/21/17.
 */
public class CommandFactory {

    static Logger logger = LoggerFactory.getLogger(CommandFactory.class);
    private final HashMap<CommandType, Command>	commands;
    private static final String MESSAGE_QUIT = "Exiting simulator!";

    private CommandFactory() {
        commands = new HashMap<CommandType,Command>();
    }

    public Optional<Position> executeCommand(CommandType command, Optional<Position> position) throws InvalidCommnadException {
        if (commands.containsKey(command)) {
            return commands.get(command).apply(position);
        } else {
            throw new InvalidCommnadException("Unknown command");
        }
    }

    /* Factory pattern */
    public static CommandFactory init() {
        CommandFactory cf = new CommandFactory();
        cf.addCommand(CommandType.PLACE, (p) -> { return p;});
        cf.addCommand(CommandType.LEFT, (p) -> { throw new InvalidCommnadException("command not Implemented: " + CommandType.LEFT.value());});
        cf.addCommand(CommandType.RIGHT, (p) -> { throw new InvalidCommnadException("command not Implemented: " + CommandType.RIGHT.value());});
        cf.addCommand(CommandType.MOVE, (p) -> {throw new InvalidCommnadException("command not Implemented: " + CommandType.MOVE.value());});
        cf.addCommand(CommandType.REPORT, (p) -> {throw new InvalidCommnadException("command not Implemented: " + CommandType.REPORT.value());});
        cf.addCommand(CommandType.QUIT, (p) -> { logger.info(MESSAGE_QUIT);return p;});
        return cf;
    }

    public String getCommandsAsString() {
        return commands.keySet().stream().map(i-> i.name()).sorted().collect(Collectors.joining(", "));
    }

    private void addCommand(CommandType name, Command command) {
        commands.put(name, command);
    }
}
