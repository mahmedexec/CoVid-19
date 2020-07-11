package com.ahmedtan.underground;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;

public class CountryDetailsActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView countryName;
    TextView newCases;
    TextView newRecovered;
    TextView newDeaths;
    TextView totalCases;
    TextView totalDeaths;
    TextView totalRecovered;
    TextView critical;
    TextView active;
    TextView casesPOM;
    ImageView countryFlag;
    ProgressBar progressBar;
    ConstraintLayout constraintLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        Country country = (Country) intent.getSerializableExtra("countryObj");
        assert country != null;

        countryName = findViewById(R.id.details_country_name);
        newCases = findViewById(R.id.details_new_cases_text);
        newRecovered = findViewById(R.id.details_new_recovered_text);
        newDeaths = findViewById(R.id.details_new_deaths_text);
        totalCases = findViewById(R.id.details_total_cases_text);
        totalRecovered = findViewById(R.id.details_total_recovered_text);
        totalDeaths = findViewById(R.id.details_total_deaths_text);
        critical = findViewById(R.id.details_critical_text);
        active = findViewById(R.id.details_active_text);
        casesPOM = findViewById(R.id.details_cases_POM_text);
        countryFlag = findViewById(R.id.details_country_flag_image);
        progressBar= findViewById(R.id.pb_details);
        constraintLayout=findViewById(R.id.layout_details);



        countryName.setText(country.getCountry());
        newCases.setText(country.getNewConfirmed());
        newRecovered.setText(country.getNewRecovered());
        newDeaths.setText(country.getNewDeaths());
        critical.setText(country.getCritical());
        casesPOM.setText(country.getCasesPOM());
        active.setText(country.getActive());
        totalCases.setText(country.getTotalConfirmed());
        totalDeaths.setText(country.getTotalDeaths());
        totalRecovered.setText(country.getTotalRecovered());
        Glide.with(this).load(country.getFlagUrl()).into(countryFlag);

        progressBar.setVisibility(View.GONE);
        constraintLayout.setVisibility(View.VISIBLE);

    }
}