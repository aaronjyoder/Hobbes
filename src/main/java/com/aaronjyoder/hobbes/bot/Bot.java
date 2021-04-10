package com.aaronjyoder.hobbes.bot;

import com.aaronjyoder.hobbes.auth.AuthRecord;
import com.aaronjyoder.hobbes.auth.RecordsJsonAdapterFactory;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Instant;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;

public class Bot {

  private final CommandHandler commandHandler;
  private final AuthRecord auth;
  private final Instant startTime;

  public Bot() {
    this.startTime = Instant.now();
    this.auth = readAuthRecord();
    this.commandHandler = new CommandHandler();
  }

  public Instant getStartTime() {
    return startTime;
  }

  public CommandHandler getCommandHandler() {
    return commandHandler;
  }

  public AuthRecord auth() {
    return auth;
  }

  private AuthRecord readAuthRecord() {
    File file = new File("res/auth/auth.json");
    if (file.exists()) {
      Moshi moshi = new Moshi.Builder().add(new RecordsJsonAdapterFactory()).build(); // TODO: Moshi does not currently support Records
      JsonAdapter<AuthRecord> jsonAdapter = moshi.adapter(AuthRecord.class);
      try {
        return jsonAdapter.fromJson(Files.readString(file.toPath()));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return new AuthRecord("TOKEN", "CLIENT_ID", "OWNER_ID", "PREFIX");
  }

  public void start(final Object... listeners) {
    try {
      DefaultShardManagerBuilder shardBuilder = DefaultShardManagerBuilder.createDefault(auth.token());
      shardBuilder.addEventListeners(listeners);
      shardBuilder.build();
    } catch (LoginException e) {
      e.printStackTrace();
    }
  }

}
