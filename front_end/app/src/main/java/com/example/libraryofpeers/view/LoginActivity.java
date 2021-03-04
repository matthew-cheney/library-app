package com.example.libraryofpeers.view;

import Request.LoginRequest;
import Response.LoginResponse;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.libraryofpeers.R;
import com.example.libraryofpeers.async_tasks.LoginTask;
import com.example.libraryofpeers.presenters.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginPresenter.View, LoginTask.LoginRequestObserver{
    private LoginPresenter presenter;

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

        presenter = new LoginPresenter(this);

        LoginRequest loginRequest = new LoginRequest(userNameLogin, passwordLogin);
        LoginTask loginTask = new LoginTask(this, presenter);
        loginTask.execute(loginRequest);
    }

    public void Register(){
        Intent registerIntent = new Intent(this, RegisterActivity.class);
        startActivity(registerIntent);
    }

    @Override
    public void onLoginSuccess(LoginResponse response) {
        System.out.println("login was a success");
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }

    @Override
    public void onLoginFailure(LoginResponse response) {
        System.out.println("login was a failure");
        Toast.makeText(this, "Invalid Credentials!", Toast.LENGTH_LONG).show();
    }
}