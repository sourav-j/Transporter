package com.enigma.transporter;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class NavigateDriverActivity extends FragmentActivity implements View.OnClickListener, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener, OnMapReadyCallback {
    private android.webkit.WebView m;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigate_driver);
        m = (WebView)findViewById(R.id.web);
        WebSettings webSettings = m.getSettings();
        webSettings.setJavaScriptEnabled(true);
        m.setWebViewClient(new WebViewClient());
        m.loadUrl("https://www.google.com/search?q=first+aid+manual");

        Button Call = findViewById(R.id.buttonCall);
        Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = "07631116004";
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + number));
                if(ActivityCompat.checkSelfPermission(NavigateDriverActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    return;
                }
                startActivity(intent);
            }
        });

        Button Payment = findViewById(R.id.buttonContinuePayment);
        Payment.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(NavigateDriverActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.Navigatemap);
        mapFragment.getMapAsync(this);
    }

    public void onMapReady(GoogleMap googleMap) {
        GoogleMap Gmap = googleMap;
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
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation", Toast.LENGTH_SHORT).show();
        return false;
    }


    public void onMyLocationClick(Location location) {
        Toast.makeText(this, "Location:\n" + location, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View v) {

    }
}
