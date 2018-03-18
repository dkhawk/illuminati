package com.maladjustedmoose.illuminati;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.google.common.collect.ImmutableMap;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;

public final class MainActivity extends DaggerAppCompatActivity {
  @Inject
  IlluminatiViewModelFactory mViewModelFactory;

  private static final ImmutableMap<Integer, String> userIds =
      new ImmutableMap.Builder<Integer, String>()
          .put(R.id.navigation_home, "42")
          .put(R.id.navigation_dashboard, "1234")
          .put(R.id.navigation_notifications, "666")
      .build();

  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
      = new BottomNavigationView.OnNavigationItemSelectedListener() {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      // TODO(dkhawk): switch fragments here!
      String userId = userIds.get(item.getItemId());
      childFragment.setUserId(userId);
      return true;
    }
  };
  private MainFragment childFragment;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (savedInstanceState == null) {
      childFragment = MainFragment.create("42");
      addFragment(R.id.fragment_container, childFragment);
    }

    BottomNavigationView navigation = findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
  }

  private void addFragment(int containerID, Fragment fragment) {
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.replace(containerID, fragment);
    transaction.commit();
  }
}
