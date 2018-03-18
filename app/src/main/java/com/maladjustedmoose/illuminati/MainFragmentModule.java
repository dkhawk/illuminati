package com.maladjustedmoose.illuminati;

import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;

// MainFragmentModule.java
@Module
interface MainFragmentModule {
  @PerChildFragment
  @ContributesAndroidInjector(modules = {
      AndroidInjectionModule.class,
      MainChildFragmentModule.class
  })
  MainChildFragment mainChildFragmentInjector();
}
