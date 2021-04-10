package com.aaronjyoder.hobbes.bot.commands.preference;

import com.aaronjyoder.hobbes.bot.Command;
import com.aaronjyoder.hobbes.bot.input.MessageInput;
import com.aaronjyoder.hobbes.bot.input.SlashInput;

public class Preferences extends Command {

  public Preferences() {
    settings.setAliases("prefs", "preferences");
    settings.setDescription("Lists all preferences.");
  }

  @Override
  protected void execute(SlashInput input) {

  }

  @Override
  protected void execute(MessageInput input) {

  }

}
