package com.aaronjyoder.hobbes.bot.commands;


import com.aaronjyoder.hobbes.bot.Command;
import com.aaronjyoder.hobbes.bot.input.MessageInput;
import com.aaronjyoder.hobbes.bot.input.SlashInput;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.commands.CommandHook;

public class Ping extends Command {

  public Ping() {
    settings.setAliases("ping");
    settings.setDescription("Tests to see if the bot is online and functional.");
  }

  @Override
  protected void execute(SlashInput input) {
    input.event().acknowledge(true).queue();
    CommandHook hook = input.event().getHook();
    hook.setEphemeral(true).sendMessage(pingMessage(input.event().getJDA())).queue();
  }

  @Override
  protected void execute(MessageInput input) {
    input.event().getChannel().sendMessage(pingMessage(input.event().getJDA())).queue();
  }

  private String pingMessage(JDA jda) {
    return "Pong! :table_tennis: | Response in `" + jda.getRestPing().complete().toString() + "ms`";
  }

}
