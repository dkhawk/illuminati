package com.maladjustedmoose.illuminati;

import android.app.Activity;
import android.app.Application;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import javax.inject.Inject;

// IlluminatiApplication.java
// Could also extend DaggerApplication instead of implementing HasActivityInjector
public class IlluminatiApplication extends Application implements HasActivityInjector {

//  @Inject
//  AppDependency appDependency;

  @Inject
  DispatchingAndroidInjector<Activity> activityInjector;

  @Override
  public void onCreate() {
    super.onCreate();
    DaggerAppComponent.create().inject(this);
  }

  @Override
  public AndroidInjector<Activity> activityInjector() {
    return activityInjector;
  }
}
