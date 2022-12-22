package com.example.searchgooglemap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.searchgooglemap.Data.Database;


public class MainActivity extends AppCompatActivity {

    /**
     *
     * Attributes and classes
     *
     */

    private EditText username, password, repassword;
    private Button clearButton, registerButton, navigateButton;
    private Database database;



    /**
     *
     * setting the login view as the main page when the application is opened.
     *
     *
     * @param savedInstanceState passing this
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        // finding ID and assigning to local variables for access.
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repeatpassword);
        navigateButton = findViewById(R.id.redirectButton);
        clearButton = findViewById(R.id.clearButton);
        registerButton = findViewById(R.id.registerButton);
        database = new Database(this);

        actionEvents();

    }

    /**
     *
     * Button action events for 3 buttons, register, navigate, and clear
     *
     *
     */
    public void actionEvents() {
        registerButton.setOnClickListener(v -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();
            String repass = repassword.getText().toString();

            if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass)) {
                Toast.makeText(MainActivity.this, "One or more field are empty", Toast.LENGTH_SHORT).show();
            } else if (!pass.equals(repass)) {
                Toast.makeText(MainActivity.this, "Password must match", Toast.LENGTH_SHORT).show();
            } else {
                Boolean checkUserExists = database.checkUsernameExists(user);
                if (!checkUserExists) {
                    Boolean register = database.Register(user, pass, repass);
                    if (register) {
                        Toast.makeText(MainActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), LoginActivty.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();

                }
            }
        });
        navigateButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivty.class);
            startActivity(intent);
        });
        clearButton.setOnClickListener(v -> {
            username.setText("");
            password.setText("");
            repassword.setText("");
        });
    }
}