package com.aaronjyoder.hobbes.command;

import java.awt.Color;
import java.util.List;
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
    return getAliases().get(0);
  }

  @Nonnull
  List<String> getAliases();

  @Nonnull
  String getDescription();

  @Nonnull
  default Color getEmbedColor() {
    return new Color(128, 128, 128);
  }


}
