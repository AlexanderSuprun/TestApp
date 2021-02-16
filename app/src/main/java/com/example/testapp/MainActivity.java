package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText mainEditText;
    private Button mainBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setListeners();

        Log.i("Lifecycle Main", "onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Lifecycle Main", "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Lifecycle Main", "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Lifecycle Main", "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Lifecycle Main", "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Lifecycle Main", "onDestroy()");
    }

    private void initViews() {
        mainEditText = findViewById(R.id.main_activity_edit_text);
        mainBtn = findViewById(R.id.main_activity_btn);
    }

    private void setListeners() {
        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mainEditText.getText())) {
                    Toast.makeText(MainActivity.this, "Fill in all fields",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getBaseContext(), SecondActivity.class);
                    intent.putExtra("EXTRA_TEXT", mainEditText.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }
}