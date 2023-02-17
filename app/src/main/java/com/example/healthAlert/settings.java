package com.example.healthAlert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class settings extends AppCompatActivity implements View.OnClickListener{

    public CardView account, profile, feedback, help, signOut;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);


        //Log Out Function
        signOut = findViewById((R.id.signOut));
        mAuth = FirebaseAuth.getInstance();


        account = (CardView) findViewById(R.id.account);
        profile = (CardView) findViewById(R.id.profile);
        feedback = (CardView) findViewById(R.id.feedback);
        help = (CardView) findViewById(R.id.help);
//        option5 = (CardView) findViewById(R.id.option5);
//        option6 = (CardView) findViewById(R.id.option6);

        //Setting Screen EventListeners
        account.setOnClickListener(this);
        profile.setOnClickListener(this);
        feedback.setOnClickListener(this);
        help.setOnClickListener(this);
//        option5.setOnClickListener(this);
//        option6.setOnClickListener(this);

        signOut.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(settings.this, login.class));
        });


        //Set Home as Selected

        bottomNavigationView.setSelectedItemId(R.id.settings);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), home.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.settings:
                        return true;

                    case R.id.notifications:
                        startActivity(new Intent(getApplicationContext(), notifications.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });





    }




    //Setting option functions
    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){

            case R.id.account:
                i = new Intent(this, account.class);
                startActivity(i);
                break;


            case R.id.profile:
                i = new Intent(this, profile.class);
                startActivity(i);
                break;

            case R.id.feedback:
                i = new Intent(this, feedback.class);
                startActivity(i);
                break;

            case R.id.help:
                i = new Intent(this, help.class);
                startActivity(i);
                break;
//
//            case R.id.option5:
//                i = new Intent(this, option5.class);
//                startActivity(i);
//                break;
//
//            case R.id.option6:
//                i = new Intent(this, option6.class);
//                startActivity(i);
//                break;
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null){
            startActivity(new Intent(settings.this, login.class));
        }
    }




}