package com.aaronjyoder.hobbes.auth;

import java.util.Objects;
import javax.annotation.Nonnull;

public final class DefaultAuthenticator implements Authenticator {

  private final String token;
  private final String clientID;
  private final String ownerID;
  private final String prefix;

  public DefaultAuthenticator(String token, String clientID, String ownerID, String prefix) {
    Objects.requireNonNull(token);
    Objects.requireNonNull(clientID);
    Objects.requireNonNull(ownerID);
    Objects.requireNonNull(prefix);
    if (token.isBlank() || clientID.isBlank() || ownerID.isBlank() || prefix.isBlank()) {
      throw new IllegalArgumentException("field cannot be blank");
    }
    this.token = token;
    this.clientID = clientID;
    this.ownerID = ownerID;
    this.prefix = prefix;
  }

  @Override
  @Nonnull
  public String token() {
    return token;
  }

  @Override
  @Nonnull
  public String clientID() {
    return clientID;
  }

  @Override
  @Nonnull
  public String ownerID() {
    return ownerID;
  }

  @Override
  @Nonnull
  public String prefix() {
    return prefix;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }
    var that = (DefaultAuthenticator) obj;
    return Objects.equals(this.token, that.token) &&
        Objects.equals(this.clientID, that.clientID) &&
        Objects.equals(this.ownerID, that.ownerID) &&
        Objects.equals(this.prefix, that.prefix);
  }

  @Override
  public int hashCode() {
    return Objects.hash(token, clientID, ownerID, prefix);
  }

  @Override
  public String toString() {
    return "DefaultAuthenticator[" +
        "token=" + token + ", " +
        "clientID=" + clientID + ", " +
        "ownerID=" + ownerID + ", " +
        "prefix=" + prefix + ']';
  }

}
