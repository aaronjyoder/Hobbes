package com.aaronjyoder.hobbes.bot.input;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class SlashInput implements Input {

  private final SlashCommandEvent event;
  private final String commandAlias;

  public SlashInput(SlashCommandEvent event) {
    this.event = event;
    this.commandAlias = event.getName();
  }

  @Override
  public InputType type() {
    return InputType.SLASH;
  }

  @Override
  public String alias() {
    return commandAlias;
  }

  public SlashCommandEvent event() {
    return event;
  }

}
