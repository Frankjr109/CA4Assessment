package com.example.onlineelecstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CustomerPaymentDetails extends AppCompatActivity {

    private Button btnAddProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_payment_details);

        btnAddProduct = findViewById(R.id.buttonGoToAddProduct);
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerPaymentDetails.this, AddProductActivity.class);
                startActivity(intent);
            }
        });
    }
}