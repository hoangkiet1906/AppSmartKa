package com.rajendra.onlinedailygroceries.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rajendra.onlinedailygroceries.Constants;
import com.rajendra.onlinedailygroceries.ProductDetail;
import com.rajendra.onlinedailygroceries.R;
import com.rajendra.onlinedailygroceries.model.Product;

import java.io.Serializable;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.RecentlyViewedViewHolder> {

    Context context;
    List<Product> productViewedList;
    private  IClickAddToCart iClickAddToCart;

    public ProductAdapter(Context context, List<Product> productViewedList, IClickAddToCart listenner) {
        this.context = context;
        this.productViewedList = productViewedList;
        this.iClickAddToCart = listenner;
    }
    public interface IClickAddToCart {
        void onClickAddToCart(ImageView imageView, Product recentlyViewed);
    }

    @NonNull
    @Override
    public RecentlyViewedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recently_viewed_items, parent, false);

        return new RecentlyViewedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecentlyViewedViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.name.setText(productViewedList.get(position).getName());
        holder.price.setText(String.valueOf(productViewedList.get(position).getPrice()));
        //holder.bg.setBackgroundResource(R.drawable.card2);

//        Glide.with(context).load("http://192.168.1.2:8080/User/assets/images/product/"+
//                productViewedList.get(position).getImage()).into(holder.bg.setBackgroundResource);

        Glide.with(context).load("http://"+ Constants.Host +":8080/User/assets/images/product/"+
                productViewedList.get(position).getImage()).into(holder.imagePro);

        holder.imgAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickAddToCart.onClickAddToCart(holder.imgAddCart, productViewedList.get(position));

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i =new Intent(context, ProductDetail.class);
                i.putExtra("product", (Serializable) productViewedList.get(position));
                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return productViewedList.size();
    }

    public  static class RecentlyViewedViewHolder extends RecyclerView.ViewHolder{

        TextView name, price;
        ImageView imagePro;
        ImageView imgAddCart;
        ConstraintLayout bg;

        public RecentlyViewedViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.price);
            bg = itemView.findViewById(R.id.recently_layout);
            imagePro = itemView.findViewById(R.id.imagePro);
            imgAddCart = itemView.findViewById(R.id.cartIcon);
        }
    }

}
