package com.example.libraryofpeers.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.libraryofpeers.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button signinBtn = (Button) findViewById(R.id.signInButton);
        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });

        Button registerBtn = (Button) findViewById(R.id.registerButton);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });
    }

    public void Login(){
        EditText usernameInputLogin = (EditText) findViewById(R.id.usernameInputLogin);
        EditText passwordInputLogin = (EditText) findViewById(R.id.passwordInputLogin);
        String userNameLogin = usernameInputLogin.getText().toString();
        String passwordLogin = passwordInputLogin.getText().toString();
        System.out.println(userNameLogin);
        System.out.println(passwordLogin);

        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }

    public void Register(){
        Intent registerIntent = new Intent(this, RegisterActivity.class);
        startActivity(registerIntent);
    }
}