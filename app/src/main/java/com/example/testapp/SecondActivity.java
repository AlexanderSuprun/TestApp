package com.example.testapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private TextView textViewToShowMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);
        textViewToShowMessage = findViewById(R.id.second_activity_text_view);

        if (getIntent() != null && getIntent().getStringExtra("EXTRA_TEXT") != null) {
            textViewToShowMessage.setText(getIntent().getStringExtra("EXTRA_TEXT"));
        }
        Log.i("Lifecycle Second", "onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Lifecycle Second", "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Lifecycle Second", "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Lifecycle Second", "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Lifecycle Second", "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Lifecycle Second", "onDestroy()");
    }
}
