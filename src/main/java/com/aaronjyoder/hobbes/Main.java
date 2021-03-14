package com.aaronjyoder.hobbes;

import com.aaronjyoder.hobbes.bot.Bot;
import com.aaronjyoder.hobbes.bot.listeners.MessageListener;
import com.aaronjyoder.hobbes.bot.listeners.ReadyListener;

public class Main {

  public static final Bot bot = new Bot(); // TODO: Bot shouldn't be a static reference probably

  public static void main(String[] args) {
    try {
      bot.start(new ReadyListener(), new MessageListener());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
