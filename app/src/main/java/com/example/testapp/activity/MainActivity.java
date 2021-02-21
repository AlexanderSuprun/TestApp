package com.example.testapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.testapp.R;
import com.example.testapp.fragment.AboutFragment;
import com.example.testapp.fragment.HostFragment;
import com.example.testapp.utils.Consts;


public class MainActivity extends AppCompatActivity implements AboutFragment.OnSendFeedbackListener {

    private static final String TAG_HOST_FRAGMENT = "com.example.testapp.HOST_FRAGMENT";

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

    public void initToolbarWithMenu(String title) {
        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        toolbar.inflateMenu(R.menu.toolbar_overflow_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.item_toolbar_about_option) {
                    showAboutDialog();
                    return true;
                } else if (item.getItemId() == R.id.item_toolbar_settings_option) {
                    Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivityForResult(intent, Consts.REQUEST_CODE);
                    return true;
                }

                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    protected void showAboutDialog() {
        FragmentManager manager = getSupportFragmentManager();
        AboutFragment aboutFragment = new AboutFragment();
        aboutFragment.show(manager, Consts.TAG_DIALOG_ABOUT);
    }

    @Override
    public void onFeedbackSend(String message) {
        Toast.makeText(getApplicationContext(), "Feedback message: " + message, Toast.LENGTH_LONG).show();
    }
}