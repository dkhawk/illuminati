package com.maladjustedmoose.illuminati;

import dagger.Component;
import javax.inject.Singleton;

// AppComponent.java
@Singleton
@Component(modules = AppModule.class)
interface AppComponent {
  void inject(IlluminatiApplication app);
}
