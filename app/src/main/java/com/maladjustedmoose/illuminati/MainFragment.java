package com.maladjustedmoose.illuminati;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;
import javax.inject.Inject;

// MainFragment.java
// Could also extend DaggerFragment instead of implementing HasFragmentInjector
// Could instead extend DialogFragment to add DialogFragment capabilities.
// DialogFragments may be embedded as regular fragments in a view of an Activity or Fragment
// and may also be shown as a dialog or in an alert dialog.
public final class MainFragment extends Fragment implements HasSupportFragmentInjector {

//  @Inject
//  AppDependency appDependency; // same object from IlluminatiApplication

//  @Inject
//  ActivityDependency activityDependency; // same object from MainActivity

//  @Inject
//  FragmentDependency fragmentDependency;

  @Inject
  DispatchingAndroidInjector<Fragment> childFragmentInjector;

  @Override
  public void onAttach(Context context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      // Perform injection here for versions before M as onAttach(*Context*) did not yet exist
      AndroidSupportInjection.inject(this);
    }
    super.onAttach(context);
  }

  @Override
  public void onAttach(Activity activity) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
      // Perform injection here for versions before M as onAttach(*Context*) did not yet exist
      AndroidSupportInjection.inject(this);
    }
    super.onAttach(activity);
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           Bundle savedInstanceState) {
    return inflater.inflate(R.layout.main_fragment, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    if (savedInstanceState == null) {
      addChildFragment(R.id.child_fragment_container, new MainChildFragment());
    }
  }

  private void addChildFragment(int containerID, Fragment fragment) {
    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
    transaction.replace(containerID, fragment);
    transaction.commit();
  }

  @Override
  public AndroidInjector<Fragment> supportFragmentInjector() {
    return childFragmentInjector;
  }
}
