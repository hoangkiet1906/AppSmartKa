package com.rajendra.onlinedailygroceries.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.rajendra.onlinedailygroceries.Constants;
import com.rajendra.onlinedailygroceries.R;
import com.rajendra.onlinedailygroceries.model.SliderProductPhoto;

import java.util.List;

public class SliderProductDetailAdapter extends PagerAdapter {

    Context context;
    List<SliderProductPhoto> listPhoto;

    public SliderProductDetailAdapter(Context context, List<SliderProductPhoto> listPhoto) {
        this.context = context;
        this.listPhoto = listPhoto;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.slider_product_item, container, false);
        ImageView imgView = view.findViewById(R.id.img_proDe_item);
        SliderProductPhoto img = listPhoto.get(position);
        if(img != null) {
//            Glide.with(context).load(img.getResourceId()).into(imgView);
            Glide.with(context).load("http://"+ Constants.Host +":8080/User/assets/images/product/"+
                    img.getResourceId()).into(imgView);
        }
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        if(listPhoto != null) {
            return listPhoto.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
