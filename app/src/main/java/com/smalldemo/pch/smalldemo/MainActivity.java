package com.smalldemo.pch.smalldemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.smalldemo.pch.smalldemo.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {
    static String MAIN_FRAGMENT_TAG = "MAIN_FRAGMENT_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            MainFragment mainFragment = new MainFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mainFragment, MAIN_FRAGMENT_TAG).commit();
        }
    }

    // Used to refresh
    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(MAIN_FRAGMENT_TAG);
        if (fragment != null && fragment.getTag().equals(MAIN_FRAGMENT_TAG) && fragment.isVisible()) {
            ((MainFragment) fragment).onBackPressed();
        }
    }
}
