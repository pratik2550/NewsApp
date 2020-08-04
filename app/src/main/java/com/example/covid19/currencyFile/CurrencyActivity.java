package com.example.covid19.currencyFile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.covid19.Common;
import com.example.covid19.MainActivity;
import com.example.covid19.R;
import com.example.covid19.weatherFile.WeatherApi;
import com.example.covid19.weatherFile.WeatherPojo;
import com.google.android.gms.common.api.Api;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CurrencyActivity extends AppCompatActivity {

    Toolbar currencytoolbar;
    EditText convertET;
    TextView convertTV;
    Spinner editSpinner, textSpinner;
    Button convertBtn;

//    String valueEt, valueTv, spinnV1, spinnV2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        currencytoolbar = findViewById(R.id.currencyToolbar);
        setSupportActionBar(currencytoolbar);
        getSupportActionBar().setTitle("Currency");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        convertET = findViewById(R.id.currency_ET);
        convertTV = findViewById(R.id.currency_TV);
        editSpinner = findViewById(R.id.spinner_1);
        textSpinner = findViewById(R.id.spinner_2);
        convertBtn = findViewById(R.id.convert_Btn);
//
        editSpinner.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, CurrencyName.currencyCountry));
        textSpinner.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, CurrencyName.currencyCountry));

//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        final String valueET = convertET.getText().toString().trim();
        convertTV.setText(valueET);
//        spinnV1 = CurrencyName.currencyCode[spinner1.getSelectedItemPosition()];
//        spinnV2 = CurrencyName.currencyCode[spinner2.getSelectedItemPosition()];
        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Kam Karle", convertET.getText().toString());
                Log.d("Kam Karle", CurrencyName.currencyCode[editSpinner.getSelectedItemPosition()] +"**"+ CurrencyName.currencyCode[textSpinner.getSelectedItemPosition()]);
                Log.d("Kam Karle", "buttonClicked");
                getCurrencyuData();
            }
        });
    }

    protected void getCurrencyuData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiCurrency.Base_Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiCurrency api = retrofit.create(ApiCurrency.class);

        Call<CurrencyPojo> call = api.getCurrency(ApiCurrency.func, CurrencyName.currencyCode[editSpinner.getSelectedItemPosition()], CurrencyName.currencyCode[textSpinner.getSelectedItemPosition()], ApiCurrency.key);
        call.enqueue(new Callback<CurrencyPojo>() {
            @Override
            public void onResponse(Call<CurrencyPojo> call, Response<CurrencyPojo> response) {
                CurrencyPojo convert = response.body();

                String converted = convert.getRealtimeCurrencyExchangeRate().get5ExchangeRate();
                double value = Double.parseDouble(converted) * Double.parseDouble(convertET.getText().toString().trim());
                convertTV.setText(String.valueOf(value));
//                currencyTV.setText(converted);
            }

            @Override
            public void onFailure(Call<CurrencyPojo> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                Log.d("==============", t.getMessage());
            }
        });

    }
}
