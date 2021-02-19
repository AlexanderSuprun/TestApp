package com.example.testapp.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.testapp.R;
import com.example.testapp.activity.base.BaseActivity;
import com.example.testapp.fragment.SettingsFragment;


public class SettingsActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initToolbarWithNavigation(getString(R.string.settings_title));

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_activity_settings_container, new SettingsFragment())
                .commit();
    }

}
