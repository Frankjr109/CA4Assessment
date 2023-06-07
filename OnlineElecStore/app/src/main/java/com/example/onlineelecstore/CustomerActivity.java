package com.example.onlineelecstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CustomerActivity extends AppCompatActivity {

    private TextView registerCustomerLink;

    private EditText loginCustomerEmail;
    private EditText loginCustomerPassword;

    private Button buttonLogin;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        mAuth = FirebaseAuth.getInstance();
        //if user exists or not
        if (mAuth.getCurrentUser() != null){
            finish();
            return;
        }

        loginCustomerEmail = findViewById(R.id.loginCustomerEmail);
        loginCustomerPassword = findViewById(R.id.loginCustomerPassword);

        registerCustomerLink = findViewById(R.id.registerCustomerLink);
        registerCustomerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerActivity.this, CustomerRegister.class);
                startActivity(intent);
            }
        });

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginCustomerEmail.getText().toString();
                String password = loginCustomerPassword.getText().toString();

                if(email.isEmpty() || password.isEmpty()){
                    Toast.makeText(CustomerActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }else if(password.length() < 5){
                    Toast.makeText(CustomerActivity.this, "Password length must be greater than 5", Toast.LENGTH_SHORT).show();
                }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(CustomerActivity.this, "Valid email address is required", Toast.LENGTH_SHORT).show();
                }else{

                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(CustomerActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(CustomerActivity.this, "customer signed in successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CustomerActivity.this, CustomerPaymentDetails.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(CustomerActivity.this, "Customer login failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });






                }





            }
        });
    }
}