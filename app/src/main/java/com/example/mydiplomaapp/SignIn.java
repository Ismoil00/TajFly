package com.example.mydiplomaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SignIn extends AppCompatActivity {

    EditText email, password;
    TextView forgotPassword, noAccount, signIn, error;
    String eMail, paSSword, backRes;
    HTTPHandler handler;
    JSONArray array;
    JSONObject object;
    ImageView home, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.signin_layout);
        email = findViewById(R.id.emailField);
        password = findViewById(R.id.passwordField);
        signIn = findViewById(R.id.loginButton);
        forgotPassword = findViewById(R.id.forgottenPassword);
        noAccount = findViewById(R.id.signUpButton);
        error = findViewById(R.id.error);
        home = findViewById(R.id.home);
        backBtn = findViewById(R.id.backBtn);
        handler = new HTTPHandler();


        signIn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                eMail = email.getText().toString();
                paSSword = password.getText().toString();

                if (eMail.equals("") || paSSword.equals("")){
                    error.setText("Please, fill in all fields!");
                }
                else {
                    try {
                    StrictMode.ThreadPolicy gfgPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(gfgPolicy);
                    backRes = handler.makeServiceCall("http://192.168.0.105:3500/accountCheck?email=" + eMail + "&password=" + paSSword);

                        array = new JSONArray(backRes);
                        if (array.length() == 0){
                            error.setText("Such Email or Password does not exit!");
                        }
                        else {
                            object = array.getJSONObject(0);
                            SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("Names", 0).edit();
                            editor.putString("userName", object.getString("first_name"));
                            editor.apply();

                            error.setVisibility(View.INVISIBLE);
                            Intent goWelcome = new Intent(SignIn.this, WelcomeActivity.class);
                            startActivity(goWelcome);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goRegister =  new Intent(SignIn.this, SignUp.class);
                startActivity(goRegister);
            }
        });


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHome =  new Intent(SignIn.this, MainPage.class);
                startActivity(goHome);
            }
        });


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBack =  new Intent(SignIn.this, UserProfile.class);
                startActivity(goBack);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignIn.this, "Password Forgetting clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}