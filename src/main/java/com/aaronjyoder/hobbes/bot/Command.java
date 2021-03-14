package com.aaronjyoder.hobbes.bot;

import com.aaronjyoder.hobbes.bot.input.MessageInput;
import com.aaronjyoder.hobbes.bot.input.SlashInput;

public abstract class Command {

  protected final CommandSettings settings = new CommandSettings();

  protected abstract void execute(SlashInput input);

  protected abstract void execute(MessageInput input);

}