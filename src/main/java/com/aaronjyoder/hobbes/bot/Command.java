package com.aaronjyoder.hobbes.bot;

public abstract class Command {

    protected final CommandSettings settings = new CommandSettings();

    protected abstract void execute(CommandInput input);

}