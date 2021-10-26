package com.andrewisnew.console.commands;

import com.andrewisnew.console.maintainers.ListsMaintainer;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;

public class AddToListCommand extends AbstractCommand {
    private final ListsMaintainer listsMaintainer;
    private static final Pattern ADD_PATTERN = Pattern.compile("^(?<name>" + LIST_NAME_REGEX + ") ((?<index>\\d+) )?(?<value>.*)$");


    AddToListCommand(@Nonnull String commandName, @Nonnull ListsMaintainer listsMaintainer) {
        super(commandName);
        this.listsMaintainer = requireNonNull(listsMaintainer, "listsMaintainer");
    }

    @Override
    public boolean execute(@Nonnull String commandArgs) {
        Matcher matcher = ADD_PATTERN.matcher(commandArgs);
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
        String value = matcher.group("value");
        Integer index = null;
        try {
            String indexGroup = matcher.group("index");
            if (indexGroup != null) {
                index = Integer.valueOf(indexGroup);
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong index");
            return false;
        }
        if (index == null) {
            list.add(value);
        } else {
            if (index > list.size()) {
                System.out.println("Can't add value at index " + index + " to list with size " + list.size());
                return false;
            }
            list.add(index, value);
        }
        System.out.println(value + " added to " + name);
        return true;
    }

    @Override
    public String getUsage() {
        return getCommandName() + " <list_name> [<index>] <value_to_add>";
    }
}
