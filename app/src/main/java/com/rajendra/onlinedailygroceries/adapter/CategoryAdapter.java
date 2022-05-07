package com.rajendra.onlinedailygroceries.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rajendra.onlinedailygroceries.AllStatusProduct;
import com.rajendra.onlinedailygroceries.Constants;
import com.rajendra.onlinedailygroceries.MainActivity;
import com.rajendra.onlinedailygroceries.R;
import com.rajendra.onlinedailygroceries.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    Context context;
    List<Product> categoryList;
    ArrayList<String> categoryListTag;

    public CategoryAdapter(Context context, List<Product> categoryList, ArrayList<String> categoryListTag) {
        this.context = context;
        this.categoryList = categoryList;
        this.categoryListTag = categoryListTag;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.category_row_items, parent, false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder,@SuppressLint("RecyclerView") final int position) {

        //holder.categoryImage.setImageResource(categoryList.get(position).getImageurl());

        Glide.with(context).load("http://"+ Constants.Host +":8080/User/assets/images/product/"+
                categoryList.get(position).getImage()).into(holder.categoryImage);


        holder.categoryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AllStatusProduct.class);
                i.putExtra("status", categoryList.get(position).getTag());
                i.putExtra("allstatusCategory", categoryListTag);
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public  static class CategoryViewHolder extends RecyclerView.ViewHolder{

        ImageView categoryImage;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryImage = itemView.findViewById(R.id.categoryImage);

        }
    }

}

// lets import all the category images