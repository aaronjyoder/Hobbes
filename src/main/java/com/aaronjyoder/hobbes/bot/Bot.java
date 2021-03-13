package com.aaronjyoder.hobbes.bot;

import com.google.gson.Gson;
import com.aaronjyoder.hobbes.auth.Authentication;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;

public class Bot {

    private final CommandHandler commandHandler = new CommandHandler();
    private final Authentication auth;

    public Bot() {
        this.auth = readAuth();
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
            DefaultShardManagerBuilder shardBuilder = DefaultShardManagerBuilder.createLight(auth.getToken());
            shardBuilder.addEventListeners(listeners);
            shardBuilder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

}
