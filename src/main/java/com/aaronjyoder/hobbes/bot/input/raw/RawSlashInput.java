package com.aaronjyoder.hobbes.bot.input.raw;

import com.aaronjyoder.hobbes.bot.input.InputType;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public record RawSlashInput(SlashCommandEvent event) implements RawInput {

  @Override
  public boolean isForBot() {
    return true;
  }

  @Override
  public InputType type() {
    return InputType.SLASH;
  }

}
