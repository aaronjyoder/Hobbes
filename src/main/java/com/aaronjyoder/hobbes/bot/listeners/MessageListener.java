package com.aaronjyoder.hobbes.bot.listeners;

import com.aaronjyoder.hobbes.Main;
import com.aaronjyoder.hobbes.bot.input.raw.RawInput;
import com.aaronjyoder.hobbes.bot.input.raw.RawMessageInput;
import com.aaronjyoder.hobbes.bot.input.raw.RawSlashInput;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;

public class MessageListener implements EventListener {

  @Override
  public void onEvent(GenericEvent event) {
    RawInput input = null;
    if (event instanceof SlashCommandEvent scEvent) {
      input = new RawSlashInput(scEvent);
    } else if (event instanceof MessageReceivedEvent mrEvent) {
      input = new RawMessageInput(mrEvent);
    }
    if (input != null && input.isForBot()) {
      Main.bot.getCommandHandler().process(input);
    }
  }

}
