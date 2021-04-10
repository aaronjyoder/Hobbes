package com.aaronjyoder.hobbes.bot.input;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public record SlashInput(SlashCommandEvent event, String alias) implements Input {

  @Override
  public InputType type() {
    return InputType.SLASH;
  }

}
