package com.enigma.transporter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Button FeedbackSubmit = findViewById(R.id.buttonSubmit);

        FeedbackSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FeedbackActivity.this, "Feedback Submitted.\nThank You for using Transporter", Toast.LENGTH_LONG).show();

                Intent i = new Intent(FeedbackActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
