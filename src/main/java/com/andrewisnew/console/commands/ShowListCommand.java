package com.andrewisnew.console.commands;

import com.andrewisnew.console.maintainers.ListsMaintainer;

import javax.annotation.Nonnull;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class ShowListCommand extends AbstractCommand {
    private final ListsMaintainer listsMaintainer;

    ShowListCommand(@Nonnull String commandName, @Nonnull ListsMaintainer listsMaintainer) {
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
        List<String> list = listsMaintainer.getList(commandArgs);
        if (list == null) {
            System.out.println("List with name " + commandArgs + " does not exist");
            return false;
        }
        System.out.println(list);
        return true;
    }

    @Override
    public String getUsage() {
        return getCommandName() + " <list_name>";
    }
}
