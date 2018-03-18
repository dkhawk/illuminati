package com.maladjustedmoose.illuminati;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class IlluminatiApplication extends DaggerApplication {
  @Override
  protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
    return DaggerAppComponent.builder().application(this).build();
  }
}
