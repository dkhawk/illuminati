package com.maladjustedmoose.illuminati;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import com.google.common.collect.ImmutableMap;
import javax.inject.Inject;

/** A fake user repository for testing. */
class UserRepositoryFake implements UserRepository {
  private static final ImmutableMap<String, User> ALL_USERS = new ImmutableMap.Builder<String, User>()
    .put("1234", User.builder().setName("Hubert J. Farnsworth").setNetworth((long) 1e9)
        .setUserId("1234").build())
    .build();

  @Inject
  public UserRepositoryFake() {}

  @Override
  public LiveData<User> getUser(String userId) {
    final MutableLiveData<User> data = new MutableLiveData<>();
    data.setValue(ALL_USERS.get(userId));
    return data;
  }
}
