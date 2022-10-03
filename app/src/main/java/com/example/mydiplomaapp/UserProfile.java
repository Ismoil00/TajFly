package com.example.mydiplomaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

public class UserProfile extends AppCompatActivity {

    RelativeLayout loginBtn, signOutBtn, loginLayout, SignOutLayout;
    TextView userName, status;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.userprofile_layout);
        loginBtn = findViewById(R.id.loginLayout);
        signOutBtn = findViewById(R.id.signOutLayout);
        loginLayout = findViewById(R.id.loginLowerLayout);
        SignOutLayout = findViewById(R.id.signOutLowerLayout);
        userName = findViewById(R.id.firstName);
        status = findViewById(R.id.status);

        SharedPreferences names = getSharedPreferences("Names", 0);
        name = names.getString("userName", "");

        if (!name.equals("")){
            userName.setText(name);
            userName.setTextColor(Color.rgb(204,255, 255));
            loginLayout.setVisibility(View.INVISIBLE);
            SignOutLayout.setVisibility(View.VISIBLE);
            status.setVisibility(View.VISIBLE);
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goLogging = new Intent(UserProfile.this, SignIn.class);
                startActivity(goLogging);
            }
        });

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                Toast.makeText(UserProfile.this, "You are signed out", Toast.LENGTH_SHORT).show();
                Intent signingOut = new Intent(UserProfile.this, MainPage.class);
                startActivity(signingOut);

                userName.setText("User name");
                userName.setTextColor(Color.WHITE);
                loginLayout.setVisibility(View.VISIBLE);
                SignOutLayout.setVisibility(View.INVISIBLE);
                status.setVisibility(View.INVISIBLE);

                SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("Names", 0).edit();
                editor.putString("userName", null);
                editor.apply();
            }
        });
    }

    public  void goHome(View view){
        Intent goHome = new Intent(this, MainPage.class);
        startActivity(goHome);
    }

    public void Functions(View view){
        Toast.makeText(this, "Profile field is selected", Toast.LENGTH_SHORT).show();
    }
}