package com.maladjustedmoose.illuminati;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;
import javax.inject.Singleton;

@Module(includes = AndroidInjectionModule.class)
interface AppModule {
  @PerActivity
  @ContributesAndroidInjector(modules = {
      AndroidInjectionModule.class,
      MainActivityModule.class
  })
  MainActivity mainActivityInjector();

  @Binds
  ViewModelProvider.Factory provideIlluminatiViewModelFactory(IlluminatiViewModelFactory factory);

  @Binds
  @Singleton
  UserRepository providesUserRepository(UserRepositoryFake repo);

  @Binds
  ViewModel provideUserViewModel(UserViewModel viewModel);

  @Binds
  abstract Context bindContext(Application application);
}
