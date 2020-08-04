package com.example.covid19.stockFile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.covid19.R;

public class StockActivity extends AppCompatActivity {

    Toolbar stocktoolbar;
//    bqi1a5frh5rbuboltijg
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        stocktoolbar = findViewById(R.id.stockToolbar);
        setSupportActionBar(stocktoolbar);
        getSupportActionBar().setTitle("Stock");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
