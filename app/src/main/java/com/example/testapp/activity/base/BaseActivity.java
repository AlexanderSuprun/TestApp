package com.example.testapp.activity.base;

import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.example.testapp.R;
import com.example.testapp.fragment.AboutFragment;
import com.example.testapp.utils.Consts;


public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;

    public void initToolbarWithMenu(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        toolbar.inflateMenu(R.menu.toolbar_overflow_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_toolbar_about_option:
                        showAboutDialog();
                        break;
                    case R.id.item_toolbar_settings_option:
                        //TODO Start SettingsActivity

                }
                return false;
            }
        });
    }

    public void initToolbarWithNavigation(String title) {
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

    protected void showAboutDialog() {
        FragmentManager manager = getSupportFragmentManager();
        AboutFragment aboutFragment = new AboutFragment();
        aboutFragment.show(manager, Consts.TAG_DIALOG_ABOUT);
    }
}
