package com.example.testapp.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.testapp.R;
import com.example.testapp.fragment.SettingsFragment;


public class SettingsActivity extends AppCompatActivity {

    private SettingsFragment settingsFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initToolbarWithNavigation(getString(R.string.settings_title));

        settingsFragment = new SettingsFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_activity_settings_container, settingsFragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK, settingsFragment.getResultIntent());
        super.onBackPressed();
    }

    public void initToolbarWithNavigation(String title) {
        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
