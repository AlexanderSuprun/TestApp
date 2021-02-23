package com.example.testapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.testapp.R;
import com.example.testapp.fragment.AboutFragment;
import com.example.testapp.fragment.HostFragment;
import com.example.testapp.utils.Consts;

import static com.example.testapp.fragment.FirstFragment.TAG_FIRST_FRAGMENT;
import static com.example.testapp.fragment.HostFragment.TAG_HOST_FRAGMENT;
import static com.example.testapp.fragment.SecondFragment.TAG_SECOND_FRAGMENT;


public class MainActivity extends AppCompatActivity implements AboutFragment.OnSendFeedbackListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        toolbar.inflateMenu(R.menu.toolbar_overflow_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.item_toolbar_about_option) {
                    new AboutFragment().show(getSupportFragmentManager(), Consts.TAG_DIALOG_ABOUT);
                    return true;
                } else if (item.getItemId() == R.id.item_toolbar_settings_option) {
                    Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivityForResult(intent, Consts.REQUEST_CODE);
                    return true;
                }

                return false;
            }
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fl_activity_main_container, new HostFragment(), TAG_HOST_FRAGMENT)
                    .commit();
        }
        setListeners();
    }

    @Override
    public void onBackPressed() {
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == Consts.REQUEST_CODE && data != null) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle(getString(R.string.changes_in_settings))
                    .setMessage(getString(R.string.dialog_push_notification) +
                            data.getBooleanExtra(Consts.EXTRA_PUSH_SETTING, false) +
                            "\n" + getString(R.string.dialog_auto_update) +
                            data.getBooleanExtra(Consts.EXTRA_UPDATE_SETTING, false) +
                            "\n" + getString(R.string.dialog_language) +
                            data.getStringExtra(Consts.EXTRA_LANGUAGE_SETTING))
                    .show();
        }
    }

    @Override
    public void onFeedbackSend(String message) {
        Toast.makeText(MainActivity.this, getString(R.string.toast_feedback_message) +
                message, Toast.LENGTH_LONG).show();
    }

    private void setListeners() {
        findViewById(R.id.button_activity_main_first_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HostFragment) getSupportFragmentManager().findFragmentByTag(TAG_HOST_FRAGMENT))
                        .replaceWithFragment(TAG_FIRST_FRAGMENT);
            }
        });

        findViewById(R.id.button_activity_main_second_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HostFragment) getSupportFragmentManager().findFragmentByTag(TAG_HOST_FRAGMENT))
                        .replaceWithFragment(TAG_SECOND_FRAGMENT);
            }
        });
    }
}