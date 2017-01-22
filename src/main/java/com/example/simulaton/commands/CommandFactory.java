package com.example.simulaton.commands;

import com.example.simulaton.exceptions.FalloffException;
import com.example.simulaton.exceptions.InvalidCommnadException;
import com.example.simulaton.utils.CommandUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Created by rajib.khan on 1/21/17.
 */
public class CommandFactory {

    private static final String MESSAGE_QUIT = "Exiting simulator!";
    static Logger logger = LoggerFactory.getLogger(CommandFactory.class);
    static Point minPoint = new Point(0, 0);
    static Point maxPoint = new Point(5, 5);
    private final HashMap<CommandType, Command> commands;
    private CommandFactory() {
        commands = new HashMap<CommandType, Command>();
    }

    /**
     * Factory pattern for initialising {@link Command}s used in the application.
     * Implementation of commands gets added as lambda functions
     *
     * @return
     */
    public static CommandFactory init() {
        CommandFactory cf = new CommandFactory();
        cf.addCommand(CommandType.PLACE, (p, r) -> {
            if (r.isPresent() && p.isPresent()) {
                r.get().setCurrentPosition(p.get());
            }
            return p;
        });

        cf.addCommand(CommandType.LEFT, (p, r) -> {
            CommandUtil.rotate(r, CommandType.LEFT);
            return p;
        });

        cf.addCommand(CommandType.RIGHT, (p, r) -> {
            CommandUtil.rotate(r, CommandType.RIGHT);
            return p;
        });
        cf.addCommand(CommandType.MOVE, (p, r) -> {
            try {
                CommandUtil.move(r, minPoint, maxPoint);
            } catch (FalloffException foEx) {
                logger.error(foEx.getMessage());
            }

            return p;
        });
        cf.addCommand(CommandType.REPORT, (p, r) -> {
            logger.info("Robot position: " + (r.isPresent() ? r.get().getCurrentPosition() : "unknown"));
            return p;
        });
        cf.addCommand(CommandType.QUIT, (p, r) -> {
            logger.info(MESSAGE_QUIT);
            return p;
        });
        return cf;
    }

    /**
     * string representation of all available commands
     *
     * @return
     */
    public String getCommandsAsString() {
        return commands.keySet().stream().map(i -> i.name()).sorted().collect(Collectors.joining(", "));
    }

    private void addCommand(CommandType name, Command command) {
        commands.put(name, command);
    }

    /**
     * returns {@link Command} if exists in {@link CommandFactory#commands}
     *
     * @param connamdType
     * @return
     * @throws InvalidCommnadException if command doesn't exist
     */
    public Command getCommand(CommandType connamdType) throws InvalidCommnadException {
        if (commands.containsKey(connamdType)) {
            return commands.get(connamdType);
        }

        throw new InvalidCommnadException("Command not found");
    }
}
