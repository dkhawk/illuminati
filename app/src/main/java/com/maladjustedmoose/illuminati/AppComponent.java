package com.maladjustedmoose.illuminati;

import android.app.Application;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
    AndroidSupportInjectionModule.class,
    MainActivityModule.class,
    AppModule.class
})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

  @Override
  void inject(DaggerApplication instance);

  @Component.Builder
  interface Builder {

    @BindsInstance
    AppComponent.Builder application(Application application);

    AppComponent build();
  }
}