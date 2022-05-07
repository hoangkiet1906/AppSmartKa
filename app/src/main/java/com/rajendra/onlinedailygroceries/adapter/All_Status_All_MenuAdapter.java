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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rajendra.onlinedailygroceries.Constants;
import com.rajendra.onlinedailygroceries.ProductDetail;
import com.rajendra.onlinedailygroceries.R;
import com.rajendra.onlinedailygroceries.model.All_Status_Item_Product;
import com.rajendra.onlinedailygroceries.model.Product;

import java.io.Serializable;
import java.util.List;

public class All_Status_All_MenuAdapter extends RecyclerView.Adapter<All_Status_All_MenuAdapter.AllMenuViewHolder> {

    Context context;
    List<Product> allmenuList;

    public All_Status_All_MenuAdapter(Context context, List<Product> allmenuList) {
        this.context = context;
        this.allmenuList = allmenuList;
    }

    @NonNull
    @Override
    public AllMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.all_status_menu_item, parent, false);

        return new AllMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllMenuViewHolder holder, @SuppressLint("RecyclerView") final int position) {
//
        holder.allMenuName.setText(allmenuList.get(position).getName());
        holder.allMenuPrice.setText(String.valueOf(allmenuList.get(position).getPrice()));
        //holder.allMenuImage.setBackgroundResource(allmenuList.get(position).getImageUrl());
        Glide.with(context).load("http://"+ Constants.Host +":8080/User/assets/images/product/"+
                allmenuList.get(position).getImage()).into(holder.allMenuImage);

//        Glide.with(context).load(allmenuList.get(position).getImageUrl()).into(holder.allMenuImage);
//
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(context, Product_item_details.class);
//                i.putExtra("name", allmenuList.get(position).getName());
//                i.putExtra("price", allmenuList.get(position).getPrice());
//                i.putExtra("rating", allmenuList.get(position).getRating());
//                i.putExtra("image", allmenuList.get(position).getImageUrl());
//
//                context.startActivity(i);
//            }
//        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i =new Intent(context, ProductDetail.class);
                i.putExtra("product", (Serializable) allmenuList.get(position));
                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return allmenuList.size();
    }

    public static class AllMenuViewHolder extends RecyclerView.ViewHolder{

        TextView allMenuName, allMenuRating, allMenuPrice;
        ImageView allMenuImage;

        public AllMenuViewHolder(@NonNull View itemView) {
            super(itemView);

            allMenuName = itemView.findViewById(R.id.all_menu_name);
            allMenuRating = itemView.findViewById(R.id.all_menu_rating);
            allMenuPrice = itemView.findViewById(R.id.all_menu_price);
            allMenuImage = itemView.findViewById(R.id.all_menu_image);
        }
    }

}
