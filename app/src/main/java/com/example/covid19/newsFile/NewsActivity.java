package com.example.covid19.newsFile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.covid19.MainActivity;
import com.example.covid19.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {

    private static final String API_KEY = "07af1771d8764b0a8f8c576b20cb6ee0";
    RecyclerView mRecyclerView;
    Adapter mAdapter;
    List<Article> mArticles = new ArrayList<>();
    Toolbar newstoolbar;
    Geocoder geoc;
    List<Address> addresses;
    double lat=51.50735;
    double lon=-0.1277567;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        newstoolbar = findViewById(R.id.newsToolbar);
        setSupportActionBar(newstoolbar);
        getSupportActionBar().setTitle("News");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        geoc = new Geocoder(this);

        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        final String country = getCountr();
        fetchJSON(country, API_KEY);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.news_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        int id = item.getItemId();
//        switch (id){
//            case R.id.refreshTab:
//                finish();
//                startActivity(getIntent());
//                Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT).show();
//        }
//        return true;
//    }

    private void fetchJSON(String country, String apiKey) {
        Call<Headlines> call = NewsClient.getInstance().getNewsInterface().getHeadlines(country, apiKey);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if (response.isSuccessful() && response.body().getArticles() != null){
                    mArticles.clear();
                    mAdapter = new Adapter(NewsActivity.this, response.body().getArticles());
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                Toast.makeText(NewsActivity.this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getCountr() {
//        Locale locale = Locale.getDefault();
//        String country = locale.getCountry();
//        Log.d("Country", country+" @@@@@@@@@@");
        String country = "in";
        lat = Double.parseDouble(MainActivity.latitude);
        lon = Double.parseDouble(MainActivity.longitude);
        Log.d("Country", lat+" @@@@@@@@@@ "+lon);
        try {
            addresses = geoc.getFromLocation(lat, lon, 1);
            Address address = addresses.get(0);
            country = address.getCountryCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return country.toLowerCase();
    }
}
