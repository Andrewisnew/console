package com.andrewisnew.console;

import com.andrewisnew.console.commands.CommandDispatcher;
import com.andrewisnew.console.maintainers.ListsMaintainer;

import java.util.Scanner;

public class ConsoleApplication {
    private static final String EXIT_COMMAND = "exit";
    public static void main(String[] args) {
        ListsMaintainer listsMaintainer = new ListsMaintainer();
        CommandDispatcher commandDispatcher = new CommandDispatcher(listsMaintainer);
        System.out.println("Console launched.\nWrite exit to finish.");
        while (true) {
            System.out.print("> ");
            Scanner scanner = new Scanner(System.in);
            String commandLine = scanner.nextLine();
            if (EXIT_COMMAND.equals(commandLine)) {
                break;
            }
            commandDispatcher.executeCommand(commandLine);
        }
    }
}
