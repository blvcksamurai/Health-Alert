package com.example.healthAlert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    TextInputEditText etLoginEmail;
    TextInputEditText etLoginPass;
    TextView tvSignUp;
    Button btnLogin;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLoginEmail = findViewById(R.id.etLoginEmail);
        etLoginPass = findViewById(R.id.etLoginPass);
        tvSignUp = findViewById(R.id.tvSignUp);
        btnLogin = findViewById(R.id.btnLogin);

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(view -> {
            loginUser();
        });
        tvSignUp.setOnClickListener(view -> {
            startActivity(new Intent(login.this,signup.class));
        });
    }



    private void loginUser(){
        String email = etLoginEmail.getText().toString();
        String password = etLoginPass.getText().toString();

            if (TextUtils.isEmpty((email))) {
            etLoginEmail.setError("Please enter your email address");
            etLoginEmail.requestFocus();
        }
        else if (TextUtils.isEmpty(password)){
            etLoginPass.setError("Kindly input password");
            etLoginPass.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(login.this,"User logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(login.this, home.class));
                    }else{
                        Toast.makeText(login.this, "Login Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            }
    }
}