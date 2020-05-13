package com.enigma.transporter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class SuggestionAcitivity extends AppCompatActivity implements View.OnClickListener {
    final Context context =this;
    Button selectbasic;
    Button selectadvance;
    Vibrator vibrator;

    ProgressBar pb;

    int counter=0;
    private static int SPLASH_TIME_OUT = 10000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion_acitivity);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        selectbasic=(Button)findViewById(R.id.Buttonselectbasic);
        selectbasic.setOnClickListener(this);

        selectadvance=(Button)findViewById(R.id.Buttonselectadvance);
        selectadvance.setOnClickListener(this);
        //prog();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SuggestionAcitivity.this, UserGiveConfirmationForOkay.class);
                startActivity(intent);

                //
                finish();
            }
        },SPLASH_TIME_OUT);




        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));

        } else {

            vibrator.vibrate(200);
        }



        /* AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("");
        alertDialogBuilder.setMessage("\nNearest ambulance sent to user location");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(SuggestionAcitivity.this, "Disaster Reported", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SuggestionAcitivity.this, SelectHospitalActivity.class);
                startActivity(intent);

                //bookambulance.this.finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show(); */



    }




     /* public void prog(){
        pb = (ProgressBar)findViewById(R.id.progressBar);

        final Timer t =new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run()
            {
                counter++;
                pb.setProgress(counter);




                if(counter == 100)
                   t.cancel();
            }

        };

        t.schedule(tt,0,100);

    } */



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Buttonselectbasic:
                Toast.makeText(this, "Basic Ambulance booked", Toast.LENGTH_LONG).show();
                Intent k = new Intent(this, SelectHospitalActivity.class);
                startActivity(k);
                break;
            case R.id.Buttonselectadvance:
                Toast.makeText(this, "Advance ambulance booked", Toast.LENGTH_LONG).show();
                Intent k2 = new Intent(this, SelectHospitalActivity.class);
                startActivity(k2);
                break;

        }

    }
}
