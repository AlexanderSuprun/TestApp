package com.example.testapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.testapp.R;
import com.example.testapp.activity.base.BaseActivity;
import com.example.testapp.fragment.AboutFragment;
import com.example.testapp.fragment.HostFragment;


public class MainActivity extends BaseActivity implements AboutFragment.OnFeedbackSendListener {

    private static final String TAG_HOST_FRAGMENT = "com.example.testapp.HostFragment";

    private AppCompatButton buttonFirstFragment;
    private AppCompatButton buttonSecondFragment;
    private HostFragment hostFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbarWithMenu(getString(R.string.app_name));

        if (getSupportFragmentManager().findFragmentByTag(TAG_HOST_FRAGMENT) == null) {
            hostFragment = new HostFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fl_activity_main_container, hostFragment, TAG_HOST_FRAGMENT)
                    .commit();
        } else {
            hostFragment = (HostFragment) getSupportFragmentManager().findFragmentByTag(TAG_HOST_FRAGMENT);
        }

        buttonFirstFragment = findViewById(R.id.button_activity_main_first_fragment);
        buttonSecondFragment = findViewById(R.id.button_activity_main_second_fragment);
        setListeners();
    }

    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();

        for (Fragment fragment : manager.getFragments()) {
            if (fragment.isVisible()) {
                FragmentManager childManager = fragment.getChildFragmentManager();
                if (childManager.getBackStackEntryCount() > 0) {
                    childManager.popBackStack();
                    return;
                }
            }
        }

        super.onBackPressed();
    }

    private void setListeners() {
        buttonFirstFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hostFragment.replaceWithFirstFragment();
            }
        });

        buttonSecondFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hostFragment.replaceWithSecondFragment();
            }
        });
    }

    @Override
    public void onFeedbackSend(String message) {
        Toast.makeText(getApplicationContext(), "Feedback message: " + message, Toast.LENGTH_LONG).show();
    }
}