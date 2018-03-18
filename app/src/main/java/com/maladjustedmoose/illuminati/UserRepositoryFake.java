package com.maladjustedmoose.illuminati;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.google.common.collect.ImmutableMap;

import javax.inject.Inject;

/**
 * A fake user repository for testing.
 */
class UserRepositoryFake implements UserRepository {
  private static final ImmutableMap<String, User> ALL_USERS = new ImmutableMap.Builder<String, User>()
      .put("1234", User.builder().setName("Hubert J. Farnsworth").setNetworth((long) 1e9)
          .setImageResourceId(R.drawable.farnsworth)
          .setUserId("1234").build())
      .put("42", User.builder().setName("Philip J. Fry").setNetworth((long) 13)
          .setImageResourceId(R.drawable.fry)
          .setUserId("42").build())
      .put("666", User.builder().setName("Zoidberg").setNetworth((long) 666)
          .setImageResourceId(R.drawable.zoidberg)
          .setUserId("666").build())
      .build();

  @Inject
  public UserRepositoryFake() {
  }

  @Override
  public LiveData<User> getUser(String userId) {
    final MutableLiveData<User> data = new MutableLiveData<>();
    data.setValue(ALL_USERS.get(userId));
    return data;
  }
}
