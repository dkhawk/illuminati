package com.maladjustedmoose.illuminati;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

// MainActivity.java
// Could also extend DaggerActivity instead of implementing HasFragmentInjector
public final class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {

//  private TextView mTextMessage;

  @Inject
  DispatchingAndroidInjector<Fragment> fragmentInjector;

  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
      = new BottomNavigationView.OnNavigationItemSelectedListener() {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      // TODO(dkhawk): switch fragments here!
      switch (item.getItemId()) {
        case R.id.navigation_home:
          Toast.makeText(MainActivity.this, R.string.title_home, Toast.LENGTH_SHORT).show();
//          mTextMessage.setText(R.string.title_home);
          return true;
        case R.id.navigation_dashboard:
          Toast.makeText(MainActivity.this, R.string.title_dashboard, Toast.LENGTH_SHORT).show();
//          mTextMessage.setText(R.string.title_dashboard);
          return true;
        case R.id.navigation_notifications:
          Toast.makeText(MainActivity.this, R.string.title_dashboard, Toast.LENGTH_SHORT).show();
//          mTextMessage.setText(R.string.title_notifications);
          return true;
      }
      return false;
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (savedInstanceState == null) {
      addFragment(R.id.fragment_container, new MainFragment());
    }

//    mTextMessage = findViewById(R.id.message);
    BottomNavigationView navigation = findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
  }

  private void addFragment(int containerID, Fragment fragment) {
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.replace(containerID, fragment);
    transaction.commit();
  }

  @Override
  public AndroidInjector<Fragment> supportFragmentInjector() {
    return fragmentInjector;
  }
}
