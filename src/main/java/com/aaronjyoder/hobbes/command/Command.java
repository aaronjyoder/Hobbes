package com.aaronjyoder.hobbes.command;

import com.aaronjyoder.hobbes.input.Input;
import javax.annotation.Nonnull;

public interface Command<T> {

  @Nonnull
  Settings getSettings();

  void execute(Input<T> input);

}
