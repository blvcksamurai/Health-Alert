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

public class signup extends AppCompatActivity {

    TextInputEditText etRegEmail;
    TextInputEditText etFullName;
    TextInputEditText etUsername;
    TextInputEditText etRegPass;
    TextView tvLoginHere;
    Button btnRegister;

    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etRegEmail = findViewById(R.id.etRegEmail);
        etFullName = findViewById(R.id.etFullname);
        etUsername = findViewById(R.id.etUsername);
        etRegPass = findViewById(R.id.etRegPass);
        tvLoginHere = findViewById(R.id.tvLoginHere);
        btnRegister = findViewById(R.id.btnRegister);

        mAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(view ->{
            createUser();
        });
        tvLoginHere.setOnClickListener(view -> {
            startActivity(new Intent(signup.this,login.class));
        });
    }

    private void createUser(){
        String name = etFullName.getText().toString();
        String username = etUsername.getText().toString();
        String email = etRegEmail.getText().toString();
        String password = etRegPass.getText().toString();

        if (TextUtils.isEmpty((name))) {

        etFullName.setError("Please enter your name");
        etFullName.requestFocus();
        }
        else if (TextUtils.isEmpty((username))) {
            etUsername.setError("Kindly enter your preferred username");
            etUsername.requestFocus();
        }

        else if (TextUtils.isEmpty((email))) {
            etRegEmail.setError("Please enter your email address");
            etRegEmail.requestFocus();
        }
        else if (TextUtils.isEmpty(password)){
            etRegPass.setError("Kindly input password");
            etRegPass.requestFocus();
        }
        else{
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(signup.this, "You have signed up successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(signup.this, login.class));
                    }else{
                        Toast.makeText(signup.this, "Signup Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }
}