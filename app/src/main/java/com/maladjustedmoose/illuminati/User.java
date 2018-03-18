package com.maladjustedmoose.illuminati;

import com.google.auto.value.AutoValue;

@AutoValue
abstract class User {

  static Builder builder() {
    return new AutoValue_User.Builder();
  }

  public abstract String userId();

  public abstract String name();

  public abstract long networth();

  @AutoValue.Builder
  abstract static class Builder {
    abstract Builder setUserId(String userId);

    abstract Builder setName(String name);

    abstract Builder setNetworth(long networth);

    abstract User build();
  }
}
