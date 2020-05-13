package com.enigma.transporter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import java.util.Calendar;

public class ScheduleActivity extends AppCompatActivity implements View.OnClickListener {
    TextView time, date;
    TimePickerDialog tPickerDialog;
    Calendar calendar;
    int mYear, mMonth, mDay, mHour, mMinute;
    String ampm;
    Button buttonredirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        date=findViewById(R.id.TextViewDate);
        time=findViewById(R.id.textViewTime);

        date.setOnClickListener(this);
        time.setOnClickListener(this);

    }

    public void onClick(View v) {

        if (v == date) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == time) {

            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            if(hourOfDay >= 12) {
                                ampm = "PM";
                            }
                            else {
                                ampm = "AM";
                            }

                            time.setText(hourOfDay + ":" + minute + " " + ampm);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        buttonredirect = (Button) findViewById(R.id.buttontimepickup);
        buttonredirect.setOnClickListener( new View.OnClickListener(){
            public void onClick(View view){
                Toast.makeText(ScheduleActivity.this, "Ambulance Scheduled for Pickup.\nChoose a Drop Hospital Now", Toast.LENGTH_LONG).show();
                Intent i = new Intent(ScheduleActivity.this, SelectHospitalActivity.class);
                startActivity(i);
            }
        });
    }
}