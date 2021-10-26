package com.andrewisnew.console.commands;

import com.andrewisnew.console.maintainers.ListsMaintainer;

import javax.annotation.Nonnull;

import static java.util.Objects.requireNonNull;

public class CreateListCommand extends AbstractCommand {
    private final ListsMaintainer listsMaintainer;

    CreateListCommand(@Nonnull String commandName, @Nonnull ListsMaintainer listsMaintainer) {
        super(commandName);
        this.listsMaintainer = requireNonNull(listsMaintainer, "listsMaintainer");
    }

    @Override
    public boolean execute(@Nonnull String commandArgs) {
        if (!validListName(commandArgs)) {
            System.out.println("Wrong list name");
            System.out.println(getUsage());
            return false;
        }
        boolean addedSuccessfully = listsMaintainer.addList(commandArgs);
        if (!addedSuccessfully) {
            System.out.println("List with name " + commandArgs + " already exists");
            return false;
        }
        System.out.println("List with name " + commandArgs + " created");
        return true;
    }

    @Override
    public String getUsage() {
        return getCommandName() + " <list_name>";
    }
}
