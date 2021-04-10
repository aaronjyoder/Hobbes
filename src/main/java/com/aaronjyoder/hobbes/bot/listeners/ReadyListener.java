package com.aaronjyoder.hobbes.bot.listeners;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReadyListener extends ListenerAdapter {

  @Override
  public void onReady(ReadyEvent event) {
    System.out.println("[ReadyEvent] All systems ready.");
  }

}
