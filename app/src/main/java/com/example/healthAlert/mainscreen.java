package com.example.healthAlert;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class mainscreen extends AppCompatActivity {

    public Button button;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);

     button = (Button) findViewById(R.id.loginbtn);



    //Sign Up Button Event Listener
     button.setOnClickListener(new View.OnClickListener(){

         public void onClick(View v){
             Intent intent = new Intent(mainscreen.this,signup.class);

             startActivity(intent);
         }


     });
     //Login Button Event Listener
        button.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent intent = new Intent(mainscreen.this,login.class);

                startActivity(intent);
            }


        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseUser user = mAuth.getCurrentUser();
//        if(user == null){
//            startActivity(new Intent(mainscreen.this,login.class));
//        }
//    }
}