package com.andrewisnew.console.commands;

import com.andrewisnew.console.maintainers.ListsMaintainer;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;

public class RemoveFromListCommand extends AbstractCommand {
    private final ListsMaintainer listsMaintainer;
    private static final Pattern REMOVE_PATTERN = Pattern.compile("^(?<name>" + LIST_NAME_REGEX + ") (?<index>\\d+)$");

    RemoveFromListCommand(@Nonnull String commandName, @Nonnull ListsMaintainer listsMaintainer) {
        super(commandName);
        this.listsMaintainer = requireNonNull(listsMaintainer, "listsMaintainer");
    }

    @Override
    public boolean execute(@Nonnull String commandArgs) {
        Matcher matcher = REMOVE_PATTERN.matcher(commandArgs);
        if (!matcher.matches()) {
            printWrongCommandSyntax();
            return false;
        }
        String name = matcher.group("name");
        List<String> list = listsMaintainer.getList(name);
        if (list == null) {
            System.out.println("List with name " + name + " does not exist");
            return false;
        }
        int index;
        try {
            String indexGroup = matcher.group("index");
            index = Integer.parseInt(indexGroup);
        } catch (NumberFormatException e) {
            System.out.println("Wrong index");
            return false;
        }

        if (index >= list.size()) {
            System.out.println("Can't remove value at index " + index + " from list with size " + list.size());
            return false;
        }
        String removed = list.remove(index);

        System.out.println(removed + " removed from " + name);
        return true;
    }

    @Override
    public String getUsage() {
        return getCommandName() + " <list_name> <index>";
    }
}
