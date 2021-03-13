package com.aaronjyoder.hobbes.bot.commands;


import com.aaronjyoder.hobbes.bot.Command;
import com.aaronjyoder.hobbes.bot.CommandInput;

public class Ping extends Command {

    public Ping() {
        settings.setAliases("ping");
        settings.setDescription("Tests to see if the bot is online and functional.");
    }

    @Override
    protected void execute(CommandInput input) {
        input.getEvent().getChannel().sendMessage("Pong! :table_tennis: | Response in `" + input.getEvent().getJDA().getRestPing().complete().toString() + "ms`").queue();
    }

}
