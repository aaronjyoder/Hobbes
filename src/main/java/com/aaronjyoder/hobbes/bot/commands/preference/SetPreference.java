package com.aaronjyoder.hobbes.bot.commands.preference;

import com.aaronjyoder.hobbes.bot.Command;
import com.aaronjyoder.hobbes.bot.input.MessageInput;
import com.aaronjyoder.hobbes.bot.input.SlashInput;

public class SetPreference extends Command {

  public SetPreference() {
    settings.setAliases("setpref", "setpreference");
    settings.setDescription("Sets a preference.");
  }

  @Override
  protected void execute(SlashInput input) {

  }

  @Override
  protected void execute(MessageInput input) {

  }

}
