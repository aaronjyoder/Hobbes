package com.aaronjyoder.hobbes.input;

import javax.annotation.Nonnull;

public interface Input<T> {

  @Nonnull
  T get();

  @Nonnull
  String alias();

}
