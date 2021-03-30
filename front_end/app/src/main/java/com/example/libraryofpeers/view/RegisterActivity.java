package com.example.libraryofpeers.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.libraryofpeers.R;
import com.example.libraryofpeers.async_tasks.RegisterTask;
import com.example.libraryofpeers.presenters.RegisterPresenter;

import Request.RegisterRequest;
import Response.RegisterResponse;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity implements RegisterPresenter.View, RegisterTask.RegisterRequestObserver {
    private RegisterPresenter presenter;
    private String imageUrl = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button addImageBtn = (Button) findViewById(R.id.addImageButton);
        addImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartImageSelectorActivity();
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

    public void StartImageSelectorActivity() {
        Intent intent = new Intent(this, ImageSelectorActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            imageUrl = data.getStringExtra("IMAGE_URL");
        }
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

        presenter = new RegisterPresenter(this);

        if (passwordRegister.equals(confirmPasswordRegister)) {
            RegisterRequest registerRequest = new RegisterRequest(userNameRegister, passwordRegister, firstNameRegister,
                    lastNameRegister, "", "", getImageUrl());
            RegisterTask registerTask = new RegisterTask(this, presenter);
            registerTask.execute(registerRequest);
        } else {
            Toast.makeText(this, "Passwords must be the same!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRegisterSuccess(RegisterResponse registerResponse) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }

    @Override
    public void onRegisterFailure(RegisterResponse registerResponse) {
        Log.e("", "Error with Register");
        Toast.makeText(this, "Register failed. Try again!", Toast.LENGTH_LONG).show();
    }

    private String getImageUrl() {
        return (imageUrl == null) ? "" : imageUrl;
    }
}
