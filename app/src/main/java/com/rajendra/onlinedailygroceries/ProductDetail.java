package com.rajendra.onlinedailygroceries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.rajendra.onlinedailygroceries.adapter.FragmentProAdapter;
import com.rajendra.onlinedailygroceries.adapter.SliderProductDetailAdapter;
import com.rajendra.onlinedailygroceries.fragment.comment_pro_Fragment;
import com.rajendra.onlinedailygroceries.fragment.info_pro_Fragment;
import com.rajendra.onlinedailygroceries.fragment.related_pro_Fragment;
import com.rajendra.onlinedailygroceries.model.Product;
import com.rajendra.onlinedailygroceries.model.SliderProductPhoto;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class ProductDetail extends AppCompatActivity {

    //slider product detail
    ViewPager viewPager;
    SliderProductDetailAdapter imgadpter;
    CircleIndicator indicator;

    RatingBar ratingBar;
    float myRating = 0;
    TabLayout tabLayout;
    ViewPager viewPager_tablayout;

    TextView pro_Name,textView7,textView12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Product pro = (Product) getIntent().getSerializableExtra("product");
        //Slider Photo Product detail
        viewPager = findViewById(R.id.viewPager_slider);
        indicator = findViewById(R.id.indidcator_slider);
        imgadpter = new SliderProductDetailAdapter(this, getListSlider(pro));
        viewPager.setAdapter(imgadpter);
        indicator.setViewPager(viewPager);
        imgadpter.registerDataSetObserver(indicator.getDataSetObserver());

        ratingBar = findViewById(R.id.ratingBar_pro);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                int rating = (int) v;
                String message = null;
                myRating = ratingBar.getRating();
                switch (rating) {
                    case 0:
                        message = "Sorry to hear that! :(((";
                        break;
                    case 1:
                        message = "Sorry to hear that! :(((";
                        break;
                    case 2:
                        message = "We always accept suggestions >.<";
                        break;
                    case 3:
                        message = "Woww :3 Thanks!";
                        break;
                    case 4:
                        message = "Great! Thank you :3";
                        break;
                    case 5:
                        message = "Awesome! You are the best :3";
                        break;
                }
                Toast.makeText(ProductDetail.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        //Fragment
        tabLayout = findViewById(R.id.tablayout_pro);
        viewPager_tablayout = findViewById(R.id.viewTabLayout);
        FragmentProAdapter fragmentProAdapter = new FragmentProAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        fragmentProAdapter.addFragment(new info_pro_Fragment(), "info");
        fragmentProAdapter.addFragment(new comment_pro_Fragment(), "comment");
        fragmentProAdapter.addFragment(new related_pro_Fragment(), "related");
        viewPager_tablayout.setAdapter(fragmentProAdapter);
        tabLayout.setupWithViewPager(viewPager_tablayout);


        pro_Name = findViewById(R.id.pro_Name);
        textView7 = findViewById(R.id.textView7);
        textView12 = findViewById(R.id.textView12);

        pro_Name.setText(pro.getName());
        textView7.setText(pro.getTag());
        textView12.setText(String.format("$ %s", String.valueOf(pro.getPrice())));



    }

    //get list photo slider Product detail
    private List<SliderProductPhoto> getListSlider(Product pro) {
        List<SliderProductPhoto> list = new ArrayList<>();
        list.add(new SliderProductPhoto(pro.getImage()));
        list.add(new SliderProductPhoto(pro.getImage()));
        list.add(new SliderProductPhoto(pro.getImage()));
        list.add(new SliderProductPhoto(pro.getImage()));
        return list;
    }
}
