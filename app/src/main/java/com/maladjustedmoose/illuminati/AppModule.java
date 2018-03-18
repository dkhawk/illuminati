package com.maladjustedmoose.illuminati;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;
import javax.inject.Singleton;

// AppModule.java
@Module(includes = AndroidInjectionModule.class)
interface AppModule {
  @PerActivity
  @ContributesAndroidInjector(modules = {
      AndroidInjectionModule.class,
      MainActivityModule.class
  })
  MainActivity mainActivityInjector();

  @Binds
  ViewModelProvider.Factory provideListIssuesViewModelFactory(
      IlluminatiViewModelFactory factory);

  @Binds
  @Singleton
  UserRepository providesUserRepository(UserRepositoryFake repo);

  @Binds
  ViewModel provideListIssuesViewModel(UserViewModel viewModel);
}
