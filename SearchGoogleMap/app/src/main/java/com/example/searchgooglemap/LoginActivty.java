package com.example.searchgooglemap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.searchgooglemap.Data.Database;

public class LoginActivty extends AppCompatActivity {

    /**
     *
     * Attributes and classes
     *
     */

    private EditText username, password;
    private Button loginButton, clearButton, navigateButton;
    private Database database;


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
        navigateButton = findViewById(R.id.redirectButton2);
        clearButton = findViewById(R.id.clearButton);
        database = new Database(this);
        actionEvents();
    }


    /**
     *
     * Button action events for 3 buttons, login, navigate, and clear
     *
     *
     */
    public void actionEvents() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(LoginActivty.this, "One or more field are empty", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean login = database.Login(user, pass);
                    if (login == true) {
                        Toast.makeText(LoginActivty.this, "Welcome " + user, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MapsActivty.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivty.this, "Username or password was incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        navigateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username.setText("");
                password.setText("");
            }
        });
    }
}