package com.maladjustedmoose.illuminati;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import javax.inject.Inject;

/** ViewModelFactory for the Illuminati app. */
class IlluminatiViewModelFactory implements ViewModelProvider.Factory {

  private final UserViewModel userViewModel;

  @Inject
  public IlluminatiViewModelFactory(UserViewModel userViewModel) {
    this.userViewModel = userViewModel;
  }

  @NonNull
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    if (modelClass.isAssignableFrom(UserViewModel.class)) {
      return (T) userViewModel;
    }
    throw new IllegalArgumentException("Unknown class name");
  }
}
