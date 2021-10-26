package com.andrewisnew.console.commands;

import com.andrewisnew.console.maintainers.ListsMaintainer;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

public class CommandDispatcher {
    private final Map<String, Command> commands;

    public CommandDispatcher(@Nonnull ListsMaintainer listsMaintainer) {
        requireNonNull(listsMaintainer, "listsMaintainer");
        Map<String, Command> map = new HashMap<>();
        addCommand(map, "create_list", name -> new CreateListCommand(name, listsMaintainer));
        addCommand(map, "show_list", name -> new ShowListCommand(name, listsMaintainer));
        addCommand(map, "add_to_list", name -> new AddToListCommand(name, listsMaintainer));
        addCommand(map, "remove_from_list", name -> new RemoveFromListCommand(name, listsMaintainer));
        commands = Collections.unmodifiableMap(map);
    }

    private static void addCommand(@Nonnull Map<String, Command> map, @Nonnull String commandName, @Nonnull Function<String, Command> commandFunction) {
        map.put(commandName, commandFunction.apply(commandName));
    }

    public void executeCommand(@Nonnull String commandLine) {
        String commandName = getCommandName(commandLine);
        Command command = commands.get(commandName);
        if (command == null) {
            System.out.println("Unknown command");
            return;
        }
        command.execute(getCommandArgs(commandLine));
    }

    @Nonnull
    private String getCommandName(@Nonnull String commandLine) {
        int commandNameEnd = commandLine.indexOf(" ");
        if (commandNameEnd == -1) {
            return commandLine;
        }
        return commandLine.substring(0, commandNameEnd);
    }

    @Nonnull
    private String getCommandArgs(@Nonnull String commandLine) {
        int commandNameEnd = commandLine.indexOf(" ");
        if (commandNameEnd == -1) {
            return "";
        }
        return commandLine.substring(commandNameEnd + 1);
    }
}
