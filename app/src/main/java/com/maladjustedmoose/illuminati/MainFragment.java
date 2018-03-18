package com.maladjustedmoose.illuminati;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

// Could also extend DaggerFragment instead of implementing HasFragmentInjector
// Could instead extend DialogFragment to add DialogFragment capabilities.
// DialogFragments may be embedded as regular fragments in a view of an Activity or Fragment
// and may also be shown as a dialog or in an alert dialog.
public final class MainFragment extends Fragment implements HasSupportFragmentInjector {
  static final String UID_KEY = "uid";

  @Inject
  IlluminatiViewModelFactory mViewModelFactory;

  @Inject
  DispatchingAndroidInjector<Fragment> childFragmentInjector;
  private UserViewModel viewModel;
  private LiveData<User> user;

  static MainFragment create(String userId) {
    MainFragment fragment = new MainFragment();
    Bundle args = new Bundle();
    args.putString(UID_KEY, userId);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    Bundle args = getArguments();
    if (args != null && args.containsKey(UID_KEY)) {
      String userId = args.getString(UID_KEY);
      viewModel = ViewModelProviders.of(this, mViewModelFactory)
          .get(UserViewModel.class);
      viewModel.init(userId);

      user = viewModel.getUser();
      if (user != null) {
        user.observe(this, this::onUserReady);
      }
    }
  }

  private void onUserReady(User user) {
    Toast.makeText(getContext(), "User is " + user.name(), Toast.LENGTH_SHORT).show();
    ImageView imageView = getView().findViewById(R.id.user_image);
    imageView.setImageResource(user.imageResourceId());
  }

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
  public AndroidInjector<Fragment> supportFragmentInjector() {
    return childFragmentInjector;
  }

  public void setUserId(String userId) {
    getArguments().putString(UID_KEY, userId);
    if (user != null) {
      user.removeObservers(this);
    }
    user = viewModel.getUser(userId);
    user.observe(this, this::onUserReady);
  }
}
