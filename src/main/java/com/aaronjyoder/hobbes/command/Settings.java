package com.aaronjyoder.hobbes.command;

import java.awt.Color;
import javax.annotation.Nonnull;

public interface Settings {

  default boolean isDisabled() {
    return false;
  }

  default boolean isOwnerCommand() {
    return false;
  }

  boolean isGuildOnly();

  @Nonnull
  default String getName() {
    return getAliases()[0];
  }

  @Nonnull
  String[] getAliases();

  @Nonnull
  String getDescription();

  @Nonnull
  default Color getEmbedColor() {
    return new Color(128, 128, 128);
  }


}
