package com.example.healthAlert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class home extends AppCompatActivity implements View.OnClickListener {

    public CardView option1, option2, option3, option4, option5, option6;



    //    Button btnLogout;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Bottom Nav Bar Routing
        //Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home as Selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        return true;

                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(), settings.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.notifications:
                        startActivity(new Intent(getApplicationContext(), notifications.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });



//        btnLogout = findViewById((R.id.btnLogout));
        mAuth = FirebaseAuth.getInstance();


        //HomeScreen Option Routes

        option1 = (CardView) findViewById(R.id.option1);
        option2 = (CardView) findViewById(R.id.option2);
        option3 = (CardView) findViewById(R.id.option3);
        option4 = (CardView) findViewById(R.id.option4);
        option5 = (CardView) findViewById(R.id.option5);
        option6 = (CardView) findViewById(R.id.option6);

        //Home Screen EventListeners
        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);
        option5.setOnClickListener(this);
        option6.setOnClickListener(this);





//        btnLogout.setOnClickListener(view -> {
//            mAuth.signOut();
//            startActivity(new Intent(home.this, login.class));
//        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null){
            startActivity(new Intent(home.this, login.class));
        }
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){

            case R.id.option1:
                i = new Intent(this, option1.class);
                startActivity(i);
                break;


            case R.id.option2:
                i = new Intent(this, option2.class);
                startActivity(i);
                break;

            case R.id.option3:
                i = new Intent(this, option3.class);
                startActivity(i);
                break;

            case R.id.option4:
                i = new Intent(this, option4.class);
                startActivity(i);
                break;

            case R.id.option5:
                i = new Intent(this, option5.class);
                startActivity(i);
                break;

            case R.id.option6:
                i = new Intent(this, option6.class);
                startActivity(i);
                break;
        }
    }
}