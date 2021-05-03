package com.aaronjyoder.hobbes.command;

import com.aaronjyoder.hobbes.input.Input;
import java.util.Set;
import javax.annotation.Nonnull;

public interface CommandHandler<T> {

  void registerCommands(@Nonnull final Set<Command<T>> commands);

  void process(@Nonnull final Input<T> input);

}
