package com.rajendra.onlinedailygroceries.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rajendra.onlinedailygroceries.Constants;
import com.rajendra.onlinedailygroceries.ProductDetail;
import com.rajendra.onlinedailygroceries.R;
import com.rajendra.onlinedailygroceries.model.Product;
import com.rajendra.onlinedailygroceries.model.Products;

import java.io.Serializable;
import java.util.List;


public class All_Status_All_ProductAdapter extends RecyclerView.Adapter<All_Status_All_ProductAdapter.ProductViewHolder> {

    Context context;
    List<Product> productsList;


    public All_Status_All_ProductAdapter(Context context, List<Product> productsList) {
        this.context = context;
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.all_status_product_row_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, final int position) {

//        Glide.with(context).load("http://192.168.43.12:8080/User/assets/images/product/"+
//                productsList.get(position).getImage()).into(holder.prodImage);
        Glide.with(context).load("http://"+ Constants.Host +":8080/User/assets/images/product/"+
                productsList.get(position).getImage()).into(holder.prodImage);
        holder.prodName.setText(productsList.get(position).getName());
        holder.prodQty.setText(String.valueOf(productsList.get(position).getQuantity()));
        holder.prodPrice.setText(String.valueOf(productsList.get(position).getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i =new Intent(context, ProductDetail.class);
                i.putExtra("product", (Serializable) productsList.get(position));
                context.startActivity(i);

            }
        });


    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public static final class ProductViewHolder extends RecyclerView.ViewHolder{

        ImageView prodImage;
        TextView prodName, prodQty, prodPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            prodImage = itemView.findViewById(R.id.prod_image);
            prodName = itemView.findViewById(R.id.prod_name);
            prodPrice = itemView.findViewById(R.id.prod_price);
            prodQty = itemView.findViewById(R.id.prod_qty);


        }
    }

}

