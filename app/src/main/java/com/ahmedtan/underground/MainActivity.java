package com.ahmedtan.underground;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private TabAdapter tabAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        //initializing the the @Views here.
        tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        //setting up the tabs for the main activity using tab layout view pager,fragments and Fragment state pager adapter.
        tabAdapter.addFragment(new GlobalFragment(), "Global");
        tabAdapter.addFragment(new ByCountryFragment(), "By Country");
        tabAdapter.addFragment(new SafetyFragment(), "Safety");
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        Log.d("MainActivity", "Menu inflated");
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.project_info_btn) {
            startActivity(new Intent(this, ProjectInfoActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }


}