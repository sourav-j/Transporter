package com.enigma.transporter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PaymentActivity extends AppCompatActivity {
    private TextView Paymentdone;
    private Button gpay;
    String GPayuser = "com.google.android.apps.nbu.paisa.user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        gpay = (Button) findViewById(R.id.Buttontakepayment);

        gpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = getPackageManager().getLaunchIntentForPackage(GPayuser);
                startActivity(intent1);
            }
        });

        Button Feedback = findViewById(R.id.buttonFeedback);
        Feedback.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(PaymentActivity.this, FeedbackActivity.class);
                startActivity(intent);
            }
        });
    }
}
