package com.example.searchgooglemap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivty extends AppCompatActivity implements OnMapReadyCallback {

    /**
     *
     * Attributes and calls
     *
     */

    private SearchView search;
    private Button logoutButton;
    private GoogleMap map;
    private SupportMapFragment fragment;


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
        logoutButton = findViewById(R.id.logoutButton);
        search = findViewById(R.id.searchView);
        fragment = SupportMapFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.googleMap, fragment).commit();
        fragment.getMapAsync(this);

        actionEvents();

       // onMapReady(googleMap);
    }


    /**
     *
     *
     * Action events for buttons and image views.
     *
     *
     */
    public void actionEvents() {
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivty.class);
                startActivity(intent);
            }
        });

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String findLocation = search.getQuery().toString();
                List<Address> addressList = null;
                if (findLocation != null || !findLocation.equals("")) {
                    Geocoder geo = new Geocoder(MapsActivty.this);

                        try {
                            addressList = geo.getFromLocationName(findLocation, 1);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Address address = addressList.get(0);
                    LatLng data = new LatLng(address.getLatitude(), address.getLongitude());
                    map.addMarker(new MarkerOptions().position(data).title(findLocation));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(data, 10));

                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        fragment.getMapAsync(this);

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
    }
}