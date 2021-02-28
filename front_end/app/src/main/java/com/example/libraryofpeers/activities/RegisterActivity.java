package com.example.libraryofpeers.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.libraryofpeers.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button registerBtn = (Button) findViewById(R.id.registerButton);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });
    }

    public void Register() {
        EditText firstNameInput = (EditText) findViewById(R.id.firstNameInput);
        EditText lastNameInput = (EditText) findViewById(R.id.lastNameInput);
        EditText usernameInputRegister = (EditText) findViewById(R.id.usernameInputRegister);
        EditText passwordInputRegister = (EditText) findViewById(R.id.passwordInputRegister);
        EditText confirmPasswordInputRegister = (EditText) findViewById(R.id.confirmPasswordInputRegister);
        String firstNameRegister = firstNameInput.getText().toString();
        String lastNameRegister = lastNameInput.getText().toString();
        String userNameRegister = usernameInputRegister.getText().toString();
        String passwordRegister = passwordInputRegister.getText().toString();
        String confirmPasswordRegister = confirmPasswordInputRegister.getText().toString();

        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
}