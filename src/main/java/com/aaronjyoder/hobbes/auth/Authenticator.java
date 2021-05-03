package com.aaronjyoder.hobbes.auth;

import javax.annotation.Nonnull;

public interface Authenticator {

  @Nonnull
  String token();

  @Nonnull
  String clientID();

  @Nonnull
  String ownerID();

  @Nonnull
  String prefix();

}
