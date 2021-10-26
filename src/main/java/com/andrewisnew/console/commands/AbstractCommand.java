package com.andrewisnew.console.commands;

import com.andrewisnew.console.maintainers.ListsMaintainer;

import javax.annotation.Nonnull;

import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;

public abstract class AbstractCommand implements Command {
    protected static final String LIST_NAME_REGEX = "[A-Za-z]+";

    private static final Pattern LIST_NAME_PATTERN = Pattern.compile(LIST_NAME_REGEX);

    private final String commandName;
    public AbstractCommand(@Nonnull String commandName) {
        this.commandName = requireNonNull(commandName, "commandName");
    }

    protected String getCommandName() {
        return commandName;
    }

    protected boolean validListName(@Nonnull String listName) {
        return LIST_NAME_PATTERN.matcher(listName).matches();
    }

    protected void printWrongCommandSyntax() {
        System.out.println("Wrong command syntax");
        System.out.println(getUsage());
    }
}
