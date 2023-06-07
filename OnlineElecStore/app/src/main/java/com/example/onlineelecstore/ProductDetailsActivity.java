package com.example.onlineelecstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;

import java.util.UUID;

public class ProductDetailsActivity extends AppCompatActivity {

    EditText titleEditText, manufacturerEdittext, priceEditText, categoryEditText;
    ImageButton saveProductBtn;
    TextView pageTitleTextView;
    String title,manufacturer,price,category,docId;
    boolean isEditMode = false;
    TextView deleteProductTextViewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        titleEditText = findViewById(R.id.product_title_text);
        manufacturerEdittext = findViewById(R.id.product_manufacturer_text);
        priceEditText = findViewById(R.id.product_price_text);
        categoryEditText = findViewById(R.id.product_category_text);

        pageTitleTextView = findViewById(R.id.product_title);
        deleteProductTextViewBtn = findViewById(R.id.delete_product_text_view_btn);

        //receive data
        title = getIntent().getStringExtra("title");
        manufacturer = getIntent().getStringExtra("manufacturer");
        price = getIntent().getStringExtra("price");
        category = getIntent().getStringExtra("category");
        docId = getIntent().getStringExtra("docId");

        if(docId != null && !docId.isEmpty()){
            isEditMode = true;
        }

        titleEditText.setText(title);
        manufacturerEdittext.setText(manufacturer);
        priceEditText.setText(price);
        categoryEditText.setText(category);
        if(isEditMode){
            pageTitleTextView.setText("Edit your product");
            deleteProductTextViewBtn.setVisibility(View.VISIBLE);
        }

        deleteProductTextViewBtn.setOnClickListener((v)-> deleteProductFromFirebase());

        saveProductBtn = findViewById(R.id.save_product_btn);
        saveProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProduct();
            }
        });
    }


    void saveProduct(){
        String productTitle = titleEditText.getText().toString();
        String productManufacturer = manufacturerEdittext.getText().toString();
        String productPrice = priceEditText.getText().toString();
        String productCategory = categoryEditText.getText().toString();

        if(productTitle.isEmpty() || productManufacturer.isEmpty() || productPrice.isEmpty() || productCategory.isEmpty()){
            Toast.makeText(ProductDetailsActivity.this, "All fields are required!", Toast.LENGTH_SHORT).show();
        }

        String productID = UUID.randomUUID().toString();

        ProductModel productModel = new ProductModel();
        productModel.setProductID(productID);
        productModel.setTitle(productTitle);
        productModel.setManufacturer(productManufacturer);
        productModel.setPrice(productPrice);
        productModel.setCategory(productCategory);

        saveProductToFirebase(productModel);
    }

    void saveProductToFirebase(ProductModel productModel){
        DocumentReference documentReference;
        if(isEditMode){
            //update product
            documentReference = Utility.getCollectionReferenceForProducts().document(docId);
        }else{
            //create new product
            documentReference = Utility.getCollectionReferenceForProducts().document();
        }
        //documentReference = Utility.getCollectionReferenceForProducts().document();

        documentReference.set(productModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    //product added
                    Utility.showToast(ProductDetailsActivity.this, "Product added successfully");
                }else{
                    Utility.showToast(ProductDetailsActivity.this, "failed while adding product");
                }
            }
        });
    }


    void deleteProductFromFirebase(){

        DocumentReference documentReference;

            documentReference = Utility.getCollectionReferenceForProducts().document(docId);

        documentReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    //product deleted
                    Utility.showToast(ProductDetailsActivity.this, "Product deleted successfully");
                }else{
                    Utility.showToast(ProductDetailsActivity.this, "failed while deleting product");
                }
            }
        });










    }
}