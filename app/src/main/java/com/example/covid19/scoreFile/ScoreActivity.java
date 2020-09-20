package com.example.covid19.scoreFile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.covid19.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

public class ScoreActivity extends AppCompatActivity {
//    https://api.themoviedb.org/3/search/movie?api_key=1b8f73865fea28a5c09554004451630e&query=club
//    https://api.themoviedb.org/3/search/multi?api_key=1b8f73865fea28a5c09554004451630e&language=en-US&query=game&page=1&include_adult=true
    Toolbar scoretoolbar;
    private CarouselView carouselView;

    final int[] sampleImages= {R.drawable.foggy, R.drawable.lightning, R.drawable.rain,
            R.drawable.cloud, R.drawable.drizzle};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scoretoolbar = findViewById(R.id.scoreToolbar);
        setSupportActionBar(scoretoolbar);
        getSupportActionBar().setTitle("Movie");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView();
    }

    private void carouselView() {
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(sampleImages[position]);
            }
        });

        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(ScoreActivity.this, "Clicked Item =" + position + " will be applied soon.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
