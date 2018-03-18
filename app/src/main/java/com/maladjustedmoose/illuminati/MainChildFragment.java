package com.maladjustedmoose.illuminati;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import dagger.android.support.AndroidSupportInjection;
import javax.inject.Inject;

// MainChildFragment.java
public final class MainChildFragment extends Fragment {

  private static final String UID_KEY = "uid";

//  @Inject
//  AppDependency appDependency; // same object from IlluminatiApplication
//
//  @Inject
//  ActivityDependency activityDependency; // same object from MainActivity
//
//  @Inject
//  FragmentDependency fragmentDependency; // same object from MainFragment
//
//  @Inject
//  ChildFragmentDependency childFragmentDependency;

  @Inject
  UserRepository userRepository;

  @Inject
  IlluminatiViewModelFactory mViewModelFactory;

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    Bundle args = getArguments();
    String userId = "1234";
    if (args != null) {
      userId = getArguments().getString(UID_KEY);
    }
    UserViewModel viewModel = ViewModelProviders.of(this, mViewModelFactory)
        .get(UserViewModel.class);
    viewModel.init(userId);

    LiveData<User> user = viewModel.getUser();
    user.observe(this, this::onUserReady);
  }

  private void onUserReady(User user) {
    Toast.makeText(this.getContext(), "User is " + user.name(), Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onAttach(Context context) {
    AndroidSupportInjection.inject(this);
    super.onAttach(context);
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           Bundle savedInstanceState) {
    return inflater.inflate(R.layout.main_child_fragment, container, false);
  }
}
