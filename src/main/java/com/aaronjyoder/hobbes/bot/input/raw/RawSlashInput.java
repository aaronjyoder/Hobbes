package com.aaronjyoder.hobbes.bot.input.raw;

import com.aaronjyoder.hobbes.bot.input.InputType;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class RawSlashInput implements RawInput {

  private final SlashCommandEvent event;

  public RawSlashInput(SlashCommandEvent event) {
    this.event = event;
  }

  @Override
  public boolean isForBot() {
    return true;
  }

  @Override
  public InputType type() {
    return InputType.SLASH;
  }

  @Override
  public GenericEvent event() {
    return event;
  }
}
