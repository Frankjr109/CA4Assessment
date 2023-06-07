package com.example.onlineelecstore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ProductAdapter extends FirestoreRecyclerAdapter<ProductModel, ProductAdapter.ProductViewHolder> {

    Context context;
    public ProductAdapter(@NonNull FirestoreRecyclerOptions<ProductModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull ProductModel productModel) {
        holder.titleTextView.setText(productModel.getTitle());
        holder.manufacturerTextView.setText(productModel.getManufacturer());
        holder.priceTextView.setText(productModel.getPrice());
        holder.categoryTextView.setText(productModel.getCategory());

        holder.itemView.setOnClickListener((v)->{
            Intent intent = new Intent(context,ProductDetailsActivity.class);
            intent.putExtra("title",productModel.getTitle());
            intent.putExtra("manufacturer",productModel.getManufacturer());
            intent.putExtra("price",productModel.getPrice());
            intent.putExtra("category",productModel.getCategory());
            String docId = this.getSnapshots().getSnapshot(position).getId();
            intent.putExtra("docId",docId);
            context.startActivity(intent);
        });
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_product_item,parent,false);
        return new ProductViewHolder(view);
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{

        TextView titleTextView, manufacturerTextView, priceTextView, categoryTextView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.product_title_text_view);
            manufacturerTextView = itemView.findViewById(R.id.product_manufacturer_text_view);
            priceTextView = itemView.findViewById(R.id.product_price_text_view);
            categoryTextView = itemView.findViewById(R.id.product_category_text_view);
        }
    }
}
