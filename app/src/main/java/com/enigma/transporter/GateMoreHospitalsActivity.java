package com.enigma.transporter;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GateMoreHospitalsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gate_more_hospitals);

        Button Confirm1 = findViewById(R.id.buttonConfirm1);
        Confirm1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(GateMoreHospitalsActivity.this, NavigateDriverActivity.class);
                startActivity(intent);
            }
        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.Amap);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng customLocation1 = new LatLng(22.578169, 88.476966);
        mMap.addMarker(new MarkerOptions().position(customLocation1).title("Ohio Hospital"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(customLocation1, 12.0f));

        LatLng customLocation2 = new LatLng(22.576986, 88.480416);
        mMap.addMarker(new MarkerOptions().position(customLocation2).title("Tata Medical Center"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(customLocation2, 12.0f));

        LatLng customLocation3 = new LatLng(22.580144, 88.475795);
        mMap.addMarker(new MarkerOptions().position(customLocation3).title("Bhagirathi Neotia Woman and Child Care Centre"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(customLocation3, 12.0f));

        LatLng customLocation4 = new LatLng(22.635035, 88.478558);
        mMap.addMarker(new MarkerOptions().position(customLocation4).title("Lotus Hospital"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(customLocation4, 12.0f));

        LatLng customLocation5 = new LatLng(22.625859, 88.435112);
        mMap.addMarker(new MarkerOptions().position(customLocation5).title("Charnock Hospital"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(customLocation5, 12.0f));

        LatLng customLocation6 = new LatLng(22.574842, 88.401405);
        mMap.addMarker(new MarkerOptions().position(customLocation6).title("Apollo Gleneagles Hospital"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(customLocation6, 12.0f));

        LatLng customLocation7 = new LatLng(22.584836, 88.423009);
        mMap.addMarker(new MarkerOptions().position(customLocation7).title("Anandalok Hospital"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(customLocation7, 12.0f));

        LatLng customLocation8 = new LatLng(22.575710, 88.4247030);
        mMap.addMarker(new MarkerOptions().position(customLocation8).title("ECHS Polyclinic Salt Lake"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(customLocation8, 12.0f));

        LatLng customLocation9 = new LatLng(22.561861, 88.411688);
        mMap.addMarker(new MarkerOptions().position(customLocation9).title("AMRI Hospital, Salt Lake"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(customLocation9, 12.0f));

        LatLng customLocation10 = new LatLng(22.575313, 88.418314);
        mMap.addMarker(new MarkerOptions().position(customLocation10).title("Calcutta Heart Clinic & Hospital"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(customLocation10, 12.0f));

        LatLng customLocation11 = new LatLng(22.575195, 88.417251);
        mMap.addMarker(new MarkerOptions().position(customLocation11).title("Parkview Super Speciality Hospital"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(customLocation11, 12.0f));

        LatLng customLocation12 = new LatLng(22.572492, 88.412826);
        mMap.addMarker(new MarkerOptions().position(customLocation12).title("Columbia Asia Hospital Salt Lake"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(customLocation12, 12.0f));

        LatLng customLocation13 = new LatLng(22.589252, 88.410891);
        mMap.addMarker(new MarkerOptions().position(customLocation13).title("ILS Hospitals"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(customLocation13, 12.0f));

        LatLng customLocation14 = new LatLng(22.562523, 88.398561);
        mMap.addMarker(new MarkerOptions().position(customLocation14).title("Beleghata I.D. And B.G. Hospital"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(customLocation14, 12.0f));
    }
}
