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

public class CustomerLogin2 extends AppCompatActivity {

    private TextView registerCustomerLink2;

    private EditText loginCustomerEmail2;
    private EditText loginCustomerPassword2;

    private Button buttonLogin2;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login2);

        mAuth = FirebaseAuth.getInstance();

        loginCustomerEmail2 = findViewById(R.id.loginCustomerEmail2);
        loginCustomerPassword2 = findViewById(R.id.loginCustomerPassword2);

        registerCustomerLink2 = findViewById(R.id.registerCustomerLink2);
        registerCustomerLink2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerLogin2.this, CustomerRegister.class);
                startActivity(intent);
            }
        });


        buttonLogin2 = findViewById(R.id.buttonLogin2);
        buttonLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginCustomerEmail2.getText().toString();
                String password = loginCustomerPassword2.getText().toString();

                if(email.isEmpty() || password.isEmpty()){
                    Toast.makeText(CustomerLogin2.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }else if(password.length() < 5){
                    Toast.makeText(CustomerLogin2.this, "Password length must be greater than 5", Toast.LENGTH_SHORT).show();
                }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(CustomerLogin2.this, "Valid email address is required", Toast.LENGTH_SHORT).show();
                }else{

                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(CustomerLogin2.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(CustomerLogin2.this, "customer signed in successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CustomerLogin2.this, CustomerPaymentDetails.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(CustomerLogin2.this, "Customer login failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });






                }





            }
        });








    }
}