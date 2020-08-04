package com.example.covid19.startupFile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.covid19.R;

public class StartupActivity extends AppCompatActivity {

    Toolbar startuptoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);


        startuptoolbar = findViewById(R.id.startupToolbar);
        setSupportActionBar(startuptoolbar);
        getSupportActionBar().setTitle("Startup");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
