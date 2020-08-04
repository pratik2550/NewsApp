package com.example.covid19.scoreFile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.covid19.R;
//LFLNKMJTsCg1pvRE6GF3bx786rJuSArghN1w7BO1TxoeUm1qWC96EIYAlbY7
public class ScoreActivity extends AppCompatActivity {

    Toolbar scoretoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scoretoolbar = findViewById(R.id.scoreToolbar);
        setSupportActionBar(scoretoolbar);
        getSupportActionBar().setTitle("Score");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
