package com.maladjustedmoose.illuminati;

import android.arch.lifecycle.LiveData;

/**
 * Interface for getting users.
 */
interface UserRepository {
  LiveData<User> getUser(String userId);
}
