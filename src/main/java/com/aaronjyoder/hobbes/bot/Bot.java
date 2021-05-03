package com.aaronjyoder.hobbes.bot;

import javax.annotation.Nonnull;

public interface Bot {

  void registerListeners(@Nonnull Object... listeners);

  void start();

}
