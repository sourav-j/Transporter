package com.enigma.transporter;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener,
        GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener, OnMapReadyCallback {
    private Button Schedule, BookNow;
    ImageView AmbulanceType, EmergencyType;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    ImageView car1, car2, car3, car4, car5, car6;
    private SupportMapFragment mapFragment;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    GoogleMap Gmap;
    SearchView searchView;
    ProgressDialog progressBar;
    private int progressbarStatus = 0;
    private Handler progressBArHandler = new Handler();
    private long filesize = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawerOpen,R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        Schedule = findViewById(R.id.buttonSchedule);
        Schedule.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, ScheduleActivity.class);
                startActivity(intent);
            }
        });

        BookNow = findViewById(R.id.buttonNow);
        BookNow.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v1){
                progressBar = new ProgressDialog(v1.getContext());
                progressBar.setCancelable(true);
                progressBar.setMessage("Searching Nearby Ambulances...");
                progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressBar.setProgress(0);
                progressBar.setMax(100);
                progressBar.show();

                progressbarStatus = 0;
                filesize = 0;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (progressbarStatus < 100) {
                            progressbarStatus = doSomeTasks();
                            try {
                                Thread.sleep(500);

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            progressBArHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(progressbarStatus);
                                }
                            });
                        }
                        if (progressbarStatus >= 100) {
                            try {
                                Thread.sleep(800);
                                Intent i1 = new Intent(MainActivity.this, SuggestionAcitivity.class);
                                startActivity(i1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            progressBar.dismiss();
                        }
                    }

                    private int doSomeTasks() {while (filesize <= 100) {
                        filesize++;
                        if (filesize == 10) {
                            return 10;
                        } else if (filesize == 20) {
                            return 20;
                        } else if (filesize == 40) {
                            return 40;
                        } else if (filesize == 70) {
                            return 70;
                        }
                    }
                        return (int) filesize;
                    }
                }).start();
            }
        });

        car1 = findViewById(R.id.car1);
        car2 = findViewById(R.id.car2);
        car3 = findViewById(R.id.car3);
        car1.setOnClickListener(this);
        car2.setOnClickListener(this);
        car3.setOnClickListener(this);

        AmbulanceType = findViewById(R.id.car2);
        AmbulanceType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.car1:
                        car1.setImageResource(R.drawable.car);
                        car2.setImageResource(R.drawable.car3);
                        car3.setImageResource(R.drawable.car3);
                        break;

                    case R.id.car2:
                        car1.setImageResource(R.drawable.car3);
                        car2.setImageResource(R.drawable.car);
                        car3.setImageResource(R.drawable.car3);
                        break;

                    case R.id.car3:
                        car1.setImageResource(R.drawable.car3);
                        car2.setImageResource(R.drawable.car3);
                        car3.setImageResource(R.drawable.car);
                        break;
                }
            }
        });

        car4 = findViewById(R.id.car4);
        car5 = findViewById(R.id.car5);
        car6 = findViewById(R.id.car6);
        car4.setOnClickListener(this);
        car5.setOnClickListener(this);
        car6.setOnClickListener(this);
        /*
        EmergencyType = findViewById(R.id.car4);
        EmergencyType.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v1) {
                switch (v1.getId()) {
                    case R.id.car4:
                        car4.setImageResource(R.drawable.car);
                        car5.setImageResource(R.drawable.car3);
                        car6.setImageResource(R.drawable.car3);
                        break;

                    case R.id.car5:
                        car4.setImageResource(R.drawable.car3);
                        car5.setImageResource(R.drawable.car);
                        car6.setImageResource(R.drawable.car3);
                        break;

                    case R.id.car6:
                        car4.setImageResource(R.drawable.car3);
                        car5.setImageResource(R.drawable.car3);
                        car6.setImageResource(R.drawable.car);
                        break;
                }
            }
        });
        */

        searchView = (SearchView) findViewById(R.id.searchlocation);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.Gmap);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.Gmap);
        mapFragment.getMapAsync(this);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = searchView.getQuery().toString();
                List<Address> addressList = null;

                if (location != null || !location.equals("")) {
                    Geocoder geocoder = new Geocoder(MainActivity.this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    Gmap.addMarker(new MarkerOptions().position(latLng).title(location));
                    Gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Gmap = googleMap;
        // TODO: Before enabling the My Location layer, you must request
        // location permission from the user. This sample does not include
        // a request for location permission.

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Gmap.setMyLocationEnabled(true);
        } else {
            Toast.makeText(this, "Location Permission Denied \n Go to settings and grant permission manually", Toast.LENGTH_SHORT).show();
            // Show rationale and request permission.
        }

        Gmap.setMyLocationEnabled(true);
        Gmap.setOnMyLocationButtonClickListener(this);
        Gmap.setOnMyLocationClickListener(this);

    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Location:\n" + location, Toast.LENGTH_LONG).show();

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.car4:
                car4.setImageResource(R.drawable.car);
                car5.setImageResource(R.drawable.car3);
                car6.setImageResource(R.drawable.car3);
                break;

            case R.id.car5:
                car4.setImageResource(R.drawable.car3);
                car5.setImageResource(R.drawable.car);
                car6.setImageResource(R.drawable.car3);
                break;

            case R.id.car6:
                car4.setImageResource(R.drawable.car3);
                car5.setImageResource(R.drawable.car3);
                car6.setImageResource(R.drawable.car);
                break;
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                Toast.makeText(MainActivity.this, "Profile Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.trips:
                Toast.makeText(MainActivity.this, "Trips Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.refer:
                Toast.makeText(MainActivity.this, "Refer Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.reportdisaster:
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= 26) {
                    vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    vibrator.vibrate(200);
                }
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Are You Sure? ");
                alertDialogBuilder.setMessage("\nClick Yes to Report Disaster !");
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Disaster Reported", Toast.LENGTH_LONG).show();
                    }
                });
                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                break;
            case R.id.about:
                Toast.makeText(MainActivity.this, "About us Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this, RegisterOrLoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            default:
                break;
        }
        return false;
    }
}