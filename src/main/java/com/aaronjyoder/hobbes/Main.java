package com.aaronjyoder.hobbes;

import com.aaronjyoder.hobbes.bot.Bot;
import com.aaronjyoder.hobbes.bot.listeners.MessageListener;
import com.aaronjyoder.hobbes.bot.listeners.ReadyListener;

import java.time.Instant;

public class Main {

    public static final Instant START_TIME = Instant.now();

    public static final Bot bot = new Bot();

    public static void main(String[] args) {
        try {
            bot.start(new ReadyListener(), new MessageListener());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
