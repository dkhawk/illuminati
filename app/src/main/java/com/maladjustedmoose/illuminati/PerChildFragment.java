package com.maladjustedmoose.illuminati;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

// PerChildFragment.java
@Scope
@Retention(RetentionPolicy.RUNTIME)
@interface PerChildFragment {
}

