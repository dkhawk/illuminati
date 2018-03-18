package com.maladjustedmoose.illuminati;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

// PerFragment.java
@Scope
@Retention(RetentionPolicy.RUNTIME)
@interface PerFragment {
}
