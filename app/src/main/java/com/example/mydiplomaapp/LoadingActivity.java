package com.example.mydiplomaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class LoadingActivity extends AppCompatActivity {

    Handler handler;
    Animation photoAnim, textAnim;
    ImageView photo, text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.loading_activity);
        photo = findViewById(R.id.topPhoto);
        text = findViewById(R.id.bottomText);

        photoAnim = AnimationUtils.loadAnimation(this, R.anim.photo_animation);
        textAnim = AnimationUtils.loadAnimation(this, R.anim.text_animation);
        photo.setAnimation(photoAnim);
        text.setAnimation(textAnim);

        handler = new Handler();
        int SPLASH_SCREEN = 2500;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadingActivity.this, MainPage.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);
    }
}