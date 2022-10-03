package com.example.mydiplomaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Objects;

public class FlightsPage extends AppCompatActivity {

    JSONArray array;
    Adapter adapter;
    String toDushanbe, toKhujand, toKulob, fromDushanbe, fromKhujand, fromKulob;
    HTTPHandler handler;
    RecyclerView list;
    LinearLayoutManager linearLayout;
    ImageButton arrival_btn, departure_btn;
    ImageView menuClick, backHome, account;
    TextView arrival_text, departure_text;
    Drawable drawable1, back_drawable1, drawable2, back_drawable2;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    String cityName;
    RelativeLayout arrival_Layout, departure_Layout;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.flights_page);
        adapter = new Adapter();
        adapter.activity = this;
        handler = new HTTPHandler();
        list = findViewById(R.id.landing_plane_list);
        arrival_btn = findViewById(R.id.landing_plane);
        departure_btn = findViewById(R.id.taking_off_plane);
        arrival_text = findViewById(R.id.landing_plane_text);
        departure_text = findViewById(R.id.taking_off_plane_text);
        drawable1 = getResources().getDrawable(R.drawable.landing_yellow_2);
        back_drawable1 = getResources().getDrawable(R.drawable.landing_blue_1);
        drawable2 = getResources().getDrawable(R.drawable.flying_yellow_2);
        back_drawable2 = getResources().getDrawable(R.drawable.flying_blue_1);
        arrival_Layout = findViewById(R.id.arrivalLayout);
        departure_Layout = findViewById(R.id.departureLayout);
        linearLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        menuClick = findViewById(R.id.menu_image);
        backHome =findViewById(R.id.backHome);
        drawerLayout = findViewById(R.id.flights);
        navigationView = findViewById(R.id.navigationLayoutFlights);
        account = findViewById(R.id.account);

        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        menuClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.settings:
                    {
                        Toast.makeText(FlightsPage.this, "Settings selected", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.privacyPolicy:
                    {
                        Toast.makeText(FlightsPage.this, "Privacy policy selected", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.support:
                    {
                        Toast.makeText(FlightsPage.this, "Support selected", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.FAQ:
                    {
                        Toast.makeText(FlightsPage.this, "FAQ selected", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.appRating:
                    {
                        Toast.makeText(FlightsPage.this, "AppRating selected", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.sharing:
                    {
                        Toast.makeText(FlightsPage.this, "ShareIt selected", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.aboutUs:
                    {
                        Toast.makeText(FlightsPage.this, "AboutUs selected", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                return false;
            }
        });

        StrictMode.ThreadPolicy gfgPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(gfgPolicy);
        toDushanbe = handler.makeServiceCall("http://192.168.0.105:3500/DYU_Arrival");
        toKhujand = handler.makeServiceCall("http://192.168.0.105:3500/LBD_Arrival");
        toKulob = handler.makeServiceCall("http://192.168.0.105:3500/TJU_Arrival");

        fromDushanbe = handler.makeServiceCall("http://192.168.0.105:3500/DYU_Departure");
        fromKhujand = handler.makeServiceCall("http://192.168.0.105:3500/LBD_Departure");
        fromKulob = handler.makeServiceCall("http://192.168.0.105:3500/TJU_Departure");

        SharedPreferences preferences = getSharedPreferences("cityNames", 0);
        cityName = preferences.getString("cityName", "");

        arrival_text.setTextColor(getResources().getColor(R.color.Dip_Yellowish));
        arrival_btn.setImageDrawable(drawable1);
        try {
            if (cityName.equals("Dushanbe")){
                array = new JSONArray(toDushanbe);
            }
            else if (cityName.equals("Khujand")){
                array = new JSONArray(toKhujand);
            }
            else {
                array = new JSONArray(toKulob);
            }
            list.setLayoutManager(linearLayout);
            list.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHomePage = new Intent(FlightsPage.this, MainPage.class);
                startActivity(goHomePage);
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goProfile = new Intent(FlightsPage.this, UserProfile.class);
                startActivity(goProfile);
            }
        });
    }

    public void Arrival(View v) {
        arrival_text.setTextColor(getResources().getColor(R.color.Dip_Yellowish));
        arrival_btn.setImageDrawable(drawable1);
        departure_text.setTextColor(getResources().getColor(R.color.Dip_Blue_darker));
        departure_btn.setImageDrawable(back_drawable2);

        try {
            if (cityName.equals("Dushanbe")){
                array = new JSONArray(toDushanbe);
            }
            else if (cityName.equals("Khujand")){
                array = new JSONArray(toKhujand);
            }
            else {
                array = new JSONArray(toKulob);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        list.setLayoutManager(linearLayout);
        list.setAdapter(adapter);
    }


    public void Departure(View v) {
        departure_text.setTextColor(getResources().getColor(R.color.Dip_Yellowish));
        departure_btn.setImageDrawable(drawable2);
        arrival_text.setTextColor(getResources().getColor(R.color.Dip_Blue_darker));
        arrival_btn.setImageDrawable(back_drawable1);

        try {
            if (cityName.equals("Dushanbe")){
                array = new JSONArray(fromDushanbe);
            }
            else if (cityName.equals("Khujand")){
                array = new JSONArray(fromKhujand);
            }
            else {
                array = new JSONArray(fromKulob);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        list.setLayoutManager(linearLayout);
        list.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }
}