package com.example.testapp.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.testapp.R;
import com.example.testapp.fragment.SettingsFragment;

import static com.example.testapp.fragment.SettingsFragment.TAG_SETTINGS_FRAGMENT;


public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.settings_title));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_activity_settings_container, new SettingsFragment(), TAG_SETTINGS_FRAGMENT)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentByTag(TAG_SETTINGS_FRAGMENT) != null) {
            setResult(RESULT_OK,
                    ((SettingsFragment) getSupportFragmentManager().findFragmentByTag(TAG_SETTINGS_FRAGMENT))
                            .getResultIntent());
            super.onBackPressed();
        }
    }
}
