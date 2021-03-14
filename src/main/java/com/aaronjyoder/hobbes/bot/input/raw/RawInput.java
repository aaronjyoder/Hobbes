package com.aaronjyoder.hobbes.bot.input.raw;

import com.aaronjyoder.hobbes.bot.input.InputType;
import net.dv8tion.jda.api.events.GenericEvent;

public interface RawInput {

  boolean isForBot();

  InputType type();

  GenericEvent event();

}
