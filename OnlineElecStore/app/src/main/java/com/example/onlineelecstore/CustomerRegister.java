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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CustomerRegister extends AppCompatActivity {

    private EditText registerFullName;
    private EditText registerEmail;
    private EditText registerNumber;
    private EditText registerCountry;
    private EditText registerPassword;

    private TextView loginCustomerLink;

    private Button buttonRegisterCustomer;

    private FirebaseAuth mAuth;

    private FirebaseFirestore fStore;

    private String customerID;

    //private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_register);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        registerFullName = findViewById(R.id.registerFullName);
        registerEmail = findViewById(R.id.registerEmail);
        registerNumber = findViewById(R.id.registerNumber);
        registerCountry = findViewById(R.id.registerCountry);
        registerPassword = findViewById(R.id.registerPassword);

        loginCustomerLink = findViewById(R.id.loginCustomerLink);
        loginCustomerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerRegister.this, CustomerActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonRegisterCustomer = findViewById(R.id.buttonRegisterCustomer);
        buttonRegisterCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerCustomer();
            }
        });

    }

    private void registerCustomer() {

        String fullName = registerFullName.getText().toString();
        String number = registerNumber.getText().toString();
        String email = registerEmail.getText().toString();
        String password = registerPassword.getText().toString();
        String country = registerCountry.getText().toString();

        if (fullName.isEmpty() || number.isEmpty() || email.isEmpty() || password.isEmpty() || country.isEmpty()) {
            Toast.makeText(CustomerRegister.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else if (number.length() < 10) {
            Toast.makeText(CustomerRegister.this, "Phone number should be up to 10 characters", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 5) {
            Toast.makeText(CustomerRegister.this, "The minimum password length is 5 characters", Toast.LENGTH_SHORT).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(CustomerRegister.this, "Please provide a valid email address", Toast.LENGTH_SHORT).show();
        }else{
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        customerID = mAuth.getCurrentUser().getUid();
                        Toast.makeText(CustomerRegister.this, "Customer created", Toast.LENGTH_SHORT).show();
                        DocumentReference documentReference = fStore.collection("users").document(customerID);
                        Map<String, Object> customerObject = new HashMap<>();
                        customerObject.put("id", customerID);
                        customerObject.put("fullName", fullName);
                        customerObject.put("number", number);
                        customerObject.put("email", email);
                        customerObject.put("country", country);
                        documentReference.set(customerObject).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(CustomerRegister.this, "Successful!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CustomerRegister.this, CustomerActivity.class);
                                startActivity(intent);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(CustomerRegister.this, "Failed!", Toast.LENGTH_SHORT).show();
                            }
                        });


                    }else{
                        Toast.makeText(CustomerRegister.this, "Failed to register to cloud!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}