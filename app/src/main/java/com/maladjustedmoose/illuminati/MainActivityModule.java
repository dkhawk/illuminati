package com.maladjustedmoose.illuminati;

import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;

// MainActivityModule.java
@Module
interface MainActivityModule {
  @PerFragment
  @ContributesAndroidInjector(modules = {
      AndroidInjectionModule.class,
      MainFragmentModule.class
  })
  MainFragment mainFragmentInjector();
}
