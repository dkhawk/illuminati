package com.maladjustedmoose.illuminati;

import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;

// MainChildFragmentModule.java
@Module
interface MainChildFragmentModule {
  @ContributesAndroidInjector(modules = {
      AndroidInjectionModule.class
  })
  MainChildFragment mainChildFragmentInjector();
}
