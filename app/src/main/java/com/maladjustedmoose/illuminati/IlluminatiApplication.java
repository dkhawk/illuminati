package com.maladjustedmoose.illuminati;

import android.app.Activity;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerApplication;

import javax.inject.Inject;

// IlluminatiApplication.java
public class IlluminatiApplication extends DaggerApplication {

//  @Inject
//  AppDependency appDependency;

  @Inject
  DispatchingAndroidInjector<Activity> activityInjector;

  @Override
  public void onCreate() {
    super.onCreate();
  }

  @Override
  protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
    return DaggerAppComponent.builder().application(this).build();
  }
}
