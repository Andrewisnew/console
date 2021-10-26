package com.andrewisnew.console.commands;

public interface Command {
    boolean execute(String commandArgs);

    String getUsage();
}
