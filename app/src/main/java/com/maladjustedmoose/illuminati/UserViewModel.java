package com.maladjustedmoose.illuminati;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import javax.inject.Inject;

/**
 * User profile view model
 */
class UserViewModel extends ViewModel {

  private LiveData<User> user;
  private final UserRepository userRepository;

  @Inject
  UserViewModel(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  void init(String userId) {
    if (user != null) {
      return;
    }
    user = userRepository.getUser(userId);
  }

  LiveData<User> getUser() {
    return user;
  }
}
