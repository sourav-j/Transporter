package com.enigma.transporter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserGiveConfirmationForOkay extends AppCompatActivity {
    private Button giveconfirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_give_confirmation_for_okay);

        giveconfirmation = (Button) findViewById(R.id.Buttonoktoconfirm);

        giveconfirmation.setOnClickListener(new View.OnClickListener()
        {
            public void onClick (View v) {
                Intent i = new Intent(UserGiveConfirmationForOkay.this, SelectHospitalActivity.class);
                startActivity(i);
            }
        });
    }
}
