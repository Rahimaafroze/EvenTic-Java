package com.example.signupin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText username, regemail, regpassword, NID;
    private MaterialButton registerBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Manual Firebase SignUp
        mAuth = FirebaseAuth.getInstance();
        username = findViewById(R.id.registerusername);
        regemail = findViewById(R.id.regemail);
        regpassword = findViewById(R.id.regpassword);
        NID = findViewById(R.id.nidEt);
        registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Register();
            }
        });


    }

    private void Register() {
        String usernamereg = username.getText().toString().trim();
        String email = regemail.getText().toString().trim();
        String password = regpassword.getText().toString().trim();
        String regNid = NID.getText().toString().trim();

        if(usernamereg.isEmpty()) {
            username.setError("Name can not be empty");
        }
        if(email.isEmpty()) {
            regemail.setError("Email can not be empty");
        }
        if(password.isEmpty()) {
            regpassword.setError("Password can not be empty");
        }
        if(regNid.isEmpty()) {
            NID.setError("NID can not be empty");
        }

        else {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this,ScanActivity.class));
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }




        }
}