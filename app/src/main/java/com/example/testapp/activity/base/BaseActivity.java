package com.example.testapp.activity.base;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testapp.R;

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;

    //TODO Add navigation
    public void initToolbar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
    }
}
