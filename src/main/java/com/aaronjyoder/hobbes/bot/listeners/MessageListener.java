package com.aaronjyoder.hobbes.bot.listeners;

import com.aaronjyoder.hobbes.Main;
import com.aaronjyoder.hobbes.bot.input.raw.RawInput;
import com.aaronjyoder.hobbes.bot.input.raw.RawMessageInput;
import com.aaronjyoder.hobbes.bot.input.raw.RawSlashInput;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageListener extends ListenerAdapter {

  @Override
  public void onSlashCommand(@NotNull SlashCommandEvent event) {
    RawInput input = new RawSlashInput(event);

    if (input.isForBot()) {
      Main.bot.getCommandHandler().process(input);
    }
  }

  @Override
  public void onMessageReceived(@NotNull MessageReceivedEvent event) {
    RawInput input = new RawMessageInput(event);

    if (input.isForBot()) {
      Main.bot.getCommandHandler().process(input);
    }
  }

}
