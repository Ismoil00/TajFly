package com.example.mydiplomaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.logging.ConsoleHandler;
import android.os.Handler;

public class WelcomeActivity extends AppCompatActivity {

    Handler handler;
    ImageView text;
    Animation textAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);
        text = findViewById(R.id.welcomeText);
        handler = new Handler();
        textAnim = AnimationUtils.loadAnimation(this, R.anim.welcome_text);
        text.setAnimation(textAnim);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent goHome = new Intent(WelcomeActivity.this, MainPage.class);
                startActivity(goHome);
                finish();
            }
        },2000);
    }
}