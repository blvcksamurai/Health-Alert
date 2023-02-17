package com.example.healthAlert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Random;

public class new_appointment extends AppCompatActivity {

    TextInputEditText type, loc, time, date;
    String appointment_details;
    Button book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_appointment);

        type = findViewById(R.id.appointment_type_edit);
        loc = findViewById(R.id.loc_edit);
        time = findViewById(R.id.time_edit);
        date = findViewById(R.id.date_edit);

        //Book Appointment button function
        book = findViewById(R.id.book);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type_name = type.getText().toString();
                String loc_name = loc.getText().toString();
                String time_name = time.getText().toString();
                String date_name = time.getText().toString();


                HashMap<String, Object> map = new HashMap<>();
                map.put("Type", type_name);
                map.put("Location", loc_name);
                map.put("Time", time_name);
                map.put("Date", date_name);

                if (type_name.isEmpty()){
                    Toast.makeText(new_appointment.this, "No Appointment Type Entered", Toast.LENGTH_SHORT).show();
                } else if (loc_name.isEmpty()){
                    Toast.makeText(new_appointment.this, "No Appointment Location Entered", Toast.LENGTH_SHORT).show();
                }
                else if (time_name.isEmpty()){
                    Toast.makeText(new_appointment.this, "No Appointment Time Entered", Toast.LENGTH_SHORT).show();
                }
                else if (date_name.isEmpty()){
                    Toast.makeText(new_appointment.this, "No Appointment Date Entered", Toast.LENGTH_SHORT).show();
                }
                else{
                    FirebaseDatabase.getInstance().getReference().child("Appointments").push().child("Appointment Details").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if(!task.isSuccessful()) {
                               Log.e("firebase", "User Already Exists", task.getException());

                                Toast.makeText(new_appointment.this, "Appointment Already Exists!", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.d("firebase", "Safe to create user", task.getException());

                                // Adding the appointment to the firebase
                                FirebaseDatabase.getInstance().getReference().child("Appointments").child("Appointment Details").setValue(map);
                                Toast.makeText(new_appointment.this, "Appointment added", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    });
                }

            }
        });

    }
}