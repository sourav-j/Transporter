package com.enigma.transporter;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SelectHospitalActivity extends FragmentActivity implements GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener, OnMapReadyCallback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_hospital);

        Button Confirm = findViewById(R.id.buttonConfirm);
        Confirm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(SelectHospitalActivity.this, NavigateDriverActivity.class);
                startActivity(intent);
            }
        });

        Button GetMoreHospitals = findViewById(R.id.buttonGetMoreHospitals);
        GetMoreHospitals.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(SelectHospitalActivity.this, GateMoreHospitalsActivity.class);
                startActivity(intent);
            }
        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
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
        GoogleMap mMap = googleMap;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            Toast.makeText(this, "Location Permission Denied \n Go to settings and grant permission manually", Toast.LENGTH_SHORT).show();
            // Show rationale and request permission.
        }
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);

        double latsh = 0,lonsh=0;
        double shdist;
        double latu= 22.569382 ,lonu=88.416289;  //CU
        double lat1= 22.574842,lon1= 88.401405;  //appollo
        double lat2= 22.584836,lon2= 88.423009;  // anadalok
        double lat3= 22.561861,lon3= 88.411688;  //amri
        double lat4= 22.589252,lon4= 88.410891;  //ils
        double lat5= 22.578169 ,lon5= 88.476966;  //ohio
        double lat6= 22.625859,lon6= 88.435112 ;  //charnock
        double lat7= 22.575710 ,lon7= 88.4247030;  //ECHS
        double lat8= 22.576986,lon8= 88.480416;    // Tata medical


        double d1= haversine(latu,lonu,lat1,lon1);
        double d2= haversine(latu,lonu,lat2,lon2);
        double d3= haversine(latu,lonu,lat3,lon3);
        double d4= haversine(latu,lonu,lat4,lon4);
        double d5= haversine(latu,lonu,lat5,lon5);
        double d6= haversine(latu,lonu,lat6,lon6);
        double d7= haversine(latu,lonu,lat7,lon7);
        double d8= haversine(latu,lonu,lat8,lon8);


        shdist= Math.min(d1,Math.min(d2,Math.min(d3,Math.min(d6,Math.min(d5,Math.min(d6,Math.min(d7,d8)))))));

        if (shdist==d1)
        {
            latsh = lat1;
            lonsh=lon1;
        }
        else if (shdist==d2)
        {
            latsh=lat2;
            lonsh=lon2;
        }
        else if (shdist==d3)
        {
            latsh=lat3;
            lonsh=lon3;
        }
        else if (shdist==d4)
        {
            latsh=lat4;
            lonsh=lon4;
        }
        else if (shdist==d5)
        {
            latsh=lat5;
            lonsh=lon5;
        }
        else if (shdist==d6)
        {
            latsh=lat6;
            lonsh=lon6;
        }
        else if (shdist==d7)
        {
            latsh=lat7;
            lonsh=lon7;
        }
        else if (shdist==d8)
        {
            latsh=lat8;
            lonsh=lon8;
        }

        String s1,s2,s3;
        s1=Double.toString(latsh);
        s2=Double.toString(lonsh);
        s3= s1+","+s2;
        String s4="";

        if (shdist==d1)
        {
            s4="Apollo";
        }
        else if (shdist==d2)
        {
            s4="Anandalok";
        }
        else if (shdist==d3)
        {
            s4="Amri Hospital";
        }
        else if (shdist==d4)
        {
            s4="ILS";
        }
        else if (shdist==d5)
        {
            s4="Ohio";
        }
        else if (shdist==d6)
        {
            s4= "Charnock";
        }
        else if (shdist==d7)
        {
            s4="ECHS";
        }
        else if (shdist==d8)
        {
            s4="Tata Medical";
        }


        LatLng customLocation1 = new LatLng(latsh, lonsh);
        mMap.addMarker(new MarkerOptions().position(customLocation1).title(s4));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(customLocation1, 12.0f));











        /* LatLng customLocation1 = new LatLng(22.578169, 88.476966);
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
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(customLocation14, 12.0f)); */
    }

    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation", Toast.LENGTH_SHORT).show();
        return false;
    }

    public void onMyLocationClick(Location location) {
        Toast.makeText(this, "Location:\n" + location, Toast.LENGTH_LONG).show();

    }

    static double haversine(double lat11, double lon11,
                            double lat22, double lon22)
    {
        // distance between latitudes and longitudes
        double dLat = Math.toRadians(lat22 - lat11);
        double dLon = Math.toRadians(lon22 - lon11);

        // convert to radians
        lat11 = Math.toRadians(lat11);
        lat22= Math.toRadians(lat22);

        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat11) *
                        Math.cos(lat22);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }

}
