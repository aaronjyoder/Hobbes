package com.aaronjyoder.hobbes.bot;

import com.aaronjyoder.hobbes.auth.Authentication;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.time.Instant;
import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;

public class Bot {

  private final CommandHandler commandHandler = new CommandHandler();
  private final Authentication auth;
  private final Instant startTime;

  public Bot() {
    this.startTime = Instant.now();
    this.auth = readAuth();
  }

  public Instant getStartTime() {
    return startTime;
  }

  public CommandHandler getCommandHandler() {
    return commandHandler;
  }

  public Authentication getAuth() {
    return auth;
  }

  public String getDefaultPrefix() {
    return auth.getDefaultPrefix();
  }

  public String getPrefix() {
    return auth.getPrefix();
  }

  private Authentication readAuth() {
    File file = new File("res/auth/auth.json");
    if (file.exists()) {
      Gson gson = new Gson();
      try {
        Reader reader = new FileReader(file);
        return gson.fromJson(reader, Authentication.class);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  public void start(@Nonnull final Object... listeners) {
    try {
      DefaultShardManagerBuilder shardBuilder = DefaultShardManagerBuilder.createDefault(auth.getToken());
      shardBuilder.addEventListeners(listeners);
      shardBuilder.build();

    } catch (LoginException e) {
      e.printStackTrace();
    }
  }

}
