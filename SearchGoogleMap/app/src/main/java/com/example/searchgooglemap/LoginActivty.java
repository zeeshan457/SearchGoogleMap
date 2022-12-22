package com.example.searchgooglemap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivty extends AppCompatActivity {

    /**
     *
     * Attributes and classes
     *
     */

    private EditText username, password;
    private Button loginButton, clearButton;


    /**
     *
     * setting the login view as the main page when the application is opened.
     *
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.registerButton);
        clearButton = findViewById(R.id.clearButton);
    }
}