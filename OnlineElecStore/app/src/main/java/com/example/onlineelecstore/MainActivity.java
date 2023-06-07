package com.example.onlineelecstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textViewCustomer;
    private TextView textViewAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewCustomer = findViewById(R.id.textViewCustomer);
        textViewCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Welcome to Customer Activity!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, CustomerLogin2.class);
                startActivity(intent);
                //finish();
            }
        });

        textViewAdmin = findViewById(R.id.textViewAdmin);
        textViewAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Welcome to your Admin Activity!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });

    }
}