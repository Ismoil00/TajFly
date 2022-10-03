package com.example.mydiplomaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.provider.Contacts;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.zip.Inflater;

public class MainPage extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    TextView cityList, btn_toFlights;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuClick, account;
    String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main_page);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationLayout);
        menuClick = findViewById(R.id.menu_image);
        cityList = findViewById(R.id.cityList);
        btn_toFlights = findViewById(R.id.btn_toFlights);
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
                        Toast.makeText(MainPage.this, "Settings selected", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.privacyPolicy:
                    {
                        Toast.makeText(MainPage.this, "Privacy policy selected", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.support:
                    {
                        Toast.makeText(MainPage.this, "Support selected", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.FAQ:
                    {
                        Toast.makeText(MainPage.this, "FAQ selected", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.appRating:
                    {
                        Toast.makeText(MainPage.this, "AppRating selected", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.sharing:
                    {
                        Toast.makeText(MainPage.this, "ShareIt selected", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.aboutUs:
                    {
                        Toast.makeText(MainPage.this, "AboutUs selected", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                return false;
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goProfile = new Intent(MainPage.this, UserProfile.class);
                startActivity(goProfile);
            }
        });
    }

    public void goFlights(View view){
        if (cityList.getText() == ""){
            AlertDialog.Builder flight = new AlertDialog.Builder(this);
            flight.setMessage("Please, choose a city!")
                    .setCancelable(true);
            AlertDialog alertDialog = flight.create();
            alertDialog.show();
        }
        else {
            @SuppressLint("CommitPrefEdits")
            SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("cityNames", 0).edit();
            editor.putString("cityName", cityName);
            editor.apply();

            Intent goFlights = new Intent(this, FlightsPage.class);
            startActivity(goFlights);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    public void popupMenu(View view){
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.Dushanbe:
                cityList.setText(item.getTitle());
                cityName = item.getTitle().toString();
                return true;
            case R.id.Khujand:
                cityList.setText(item.getTitle());
                cityName = item.getTitle().toString();
            case R.id.Kulob:
                cityList.setText(item.getTitle());
                cityName = item.getTitle().toString();
                return true;
            default:
                return false;
        }
    }
}