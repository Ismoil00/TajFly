package com.example.mydiplomaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

public class SignUp extends AppCompatActivity {

    EditText firstName, lastName, email, password;
    TextView haveAccount, signUp, error;
    HTTPHandler handler;
    String emailMatch, passwordMatch, register;
    JSONArray arrayEmail, arrayPass;
    ImageView home, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.signup_layout);
        firstName = findViewById(R.id.firstNameField);
        lastName = findViewById(R.id.lastNameField);
        email = findViewById(R.id.emailField);
        password = findViewById(R.id.passwordField);
        signUp = findViewById(R.id.signUpButton);
        haveAccount = findViewById(R.id.haveAccount);
        error = findViewById(R.id.error);
        home = findViewById(R.id.home);
        backBtn = findViewById(R.id.backBtn);
        handler = new HTTPHandler();



        signUp.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String firstname = firstName.getText().toString();
                String lastname = lastName.getText().toString();
                String eMail = email.getText().toString();
                String paSSword = password.getText().toString();

                if (firstname.equals("") || lastname.equals("") || eMail.equals("") || paSSword.equals("")){
                    error.setText("Please, fill in all fields!");
                }
                else {
                    StrictMode.ThreadPolicy gfgPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(gfgPolicy);
                    emailMatch = handler.makeServiceCall("http://192.168.0.105:3500/signUpEmailMatch?email=" + eMail);
                    passwordMatch = handler.makeServiceCall("http://192.168.0.105:3500/signUpPasswordMatch?password=" + paSSword);

                    try {
                        arrayEmail = new JSONArray(emailMatch);
                        arrayPass = new JSONArray(passwordMatch);
                        if (arrayEmail.length() != 0 || arrayPass.length() != 0){
                            error.setText("Such Email or Password already exits");
                        }
                        else {
                            error.setVisibility(View.INVISIBLE);
                            register = handler.makeServiceCall("http://192.168.0.105:3500/addAccountData?first_name=" + firstname + "&last_name=" + lastname + "&password=" + paSSword + "&email=" + eMail);
                            Toast.makeText(SignUp.this, "Your data is saved", Toast.LENGTH_SHORT).show();

                            Intent goSignIn = new Intent(SignUp.this, SignIn.class);
                            startActivity(goSignIn);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goSignIn = new Intent(SignUp.this, SignIn.class);
                startActivity(goSignIn);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHome =  new Intent(SignUp.this, MainPage.class);
                startActivity(goHome);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goSignIn = new Intent(SignUp.this, SignIn.class);
                startActivity(goSignIn);
            }
        });
    }
}