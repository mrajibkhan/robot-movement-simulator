package com.example.simulaton.commands;

import com.example.simulaton.exceptions.InvalidCommnadException;
import com.example.simulaton.models.Directions;
import com.example.simulaton.models.Position;
import com.example.simulaton.models.Robot;
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

    /* Factory pattern */
    public static CommandFactory init() {
        CommandFactory cf = new CommandFactory();
        cf.addCommand(CommandType.PLACE, (p, r) -> { r.get().setCurrentPosition(p.get());return p;});
        cf.addCommand(CommandType.LEFT, (p, r) -> { throw new InvalidCommnadException("command not implemented: " + CommandType.LEFT.value());});
        cf.addCommand(CommandType.RIGHT, (p, r) -> { throw new InvalidCommnadException("command not implemented: " + CommandType.RIGHT.value());});
        cf.addCommand(CommandType.MOVE, (p, r) -> {throw new InvalidCommnadException("command not implemented: " + CommandType.MOVE.value());});
        cf.addCommand(CommandType.REPORT, (p, r) -> {throw new InvalidCommnadException("command not implemented: " + CommandType.REPORT.value());});
        cf.addCommand(CommandType.QUIT, (p, r) -> { logger.info(MESSAGE_QUIT);return p;});
        return cf;
    }

    public String getCommandsAsString() {
        return commands.keySet().stream().map(i-> i.name()).sorted().collect(Collectors.joining(", "));
    }

    private void addCommand(CommandType name, Command command) {
        commands.put(name, command);
    }

    public Command getCommand(CommandType connamdType) throws InvalidCommnadException {
        if (commands.containsKey(connamdType)) {
            return commands.get(connamdType);
        }

        throw new InvalidCommnadException("Command not found");
    }
}
