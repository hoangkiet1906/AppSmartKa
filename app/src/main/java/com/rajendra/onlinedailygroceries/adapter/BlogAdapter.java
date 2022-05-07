package com.rajendra.onlinedailygroceries.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rajendra.onlinedailygroceries.Constants;
import com.rajendra.onlinedailygroceries.R;
import com.rajendra.onlinedailygroceries.model.Blog;
import com.rajendra.onlinedailygroceries.model.DiscountedProducts;

import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.BlogViewHolder> {

    Context context;
    List<Blog> BlogList;

    public BlogAdapter(Context context, List<Blog> BlogList) {
        this.context = context;
        this.BlogList = BlogList;
    }

    @NonNull
    @Override
    public BlogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.blog_row_items, parent, false);
        return new BlogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogViewHolder holder, int position) {
        holder.blogTitle.setText(BlogList.get(position).getTitle());
        Glide.with(context).load("http://"+ Constants.Host +":8080/User/assets/images/blog/"+
                BlogList.get(position).getImage()).into(holder.discountImageView);

    }

    @Override
    public int getItemCount() {
        return BlogList.size();
    }

    public static class BlogViewHolder extends  RecyclerView.ViewHolder{

        ImageView discountImageView;
        TextView blogTitle;

        public BlogViewHolder(@NonNull View itemView) {
            super(itemView);

            discountImageView = itemView.findViewById(R.id.discountImage);
            blogTitle = itemView.findViewById(R.id.blogTitle);
        }
    }
}
