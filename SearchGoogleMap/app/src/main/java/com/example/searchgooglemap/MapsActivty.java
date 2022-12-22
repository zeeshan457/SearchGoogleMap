package com.example.searchgooglemap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.maps.GoogleMap;

public class MapsActivty extends AppCompatActivity {

    /**
     *
     * Attributes and calls
     *
     */

    private View map;
    private ImageView search;
    private Button logoutButton;
    private Boolean initialiseMap;
    private EditText location;
    private GoogleMap map;


    /**
     *
     * Instance of Maps layout
     *
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);
        search = findViewById(R.id.searchButton);
        logoutButton = findViewById(R.id.logoutButton);
        location = findViewById(R.id.location);

        checkPermissions();




    }


    /**
     *
     *
     * Action events for buttons and image views.
     *
     *
     */
    public void actionEvents() {


    }

    public void mapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;


    }



}