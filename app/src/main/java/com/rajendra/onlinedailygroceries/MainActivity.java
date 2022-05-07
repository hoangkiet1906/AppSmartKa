package com.rajendra.onlinedailygroceries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.bumptech.glide.Glide;
import com.github.rubensousa.floatingtoolbar.FloatingToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rajendra.onlinedailygroceries.adapter.CategoryAdapter;
import com.rajendra.onlinedailygroceries.adapter.BlogAdapter;
import com.rajendra.onlinedailygroceries.adapter.ProductAdapter;
import com.rajendra.onlinedailygroceries.adapter.SliderAdapter;
import com.rajendra.onlinedailygroceries.animation.addToCart;
import com.rajendra.onlinedailygroceries.model.Blog;
import com.rajendra.onlinedailygroceries.model.DiscountedProducts;
import com.rajendra.onlinedailygroceries.model.Product;
import com.rajendra.onlinedailygroceries.model.User;
import com.rajendra.onlinedailygroceries.network.ApiClient;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.rajendra.onlinedailygroceries.R.drawable.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView discountRecyclerView,newRecyclerView, bestRecyclerView, saleRecyclerView, categoryRecyclerView;
    BlogAdapter discountedProductAdapter;
    List<Blog> blogList;

    CategoryAdapter categoryAdapter;
    List<Product> categoryList;
    List<Product> productList;

    ProductAdapter adapterNewArrival;
    ProductAdapter adapterBestsaller;
    ProductAdapter adapterSaleItem;
    List<Product> listNewArrival;
    List<Product> listBestsaller;
    List<Product> listSaleItem;

    TextView allCategory;
    TextView allnewArrival,allbestSeller,allsaleItem;
    ImageView avtUserOnTop;

//    Toolbar toolbar;
    FloatingToolbar floatingToolbar;
    FloatingActionButton fabMenu;
    SliderView sliderView;
    int[] images = {R.drawable.slide_item1,
                    R.drawable.slide_item2,
                    R.drawable.slide_item3,
                    R.drawable.slide_item4};

    // animation add to cart
    ImageView startAnimation, cartIcon, endAnimation;
    int countCartItem = 0;
    AHBottomNavigation ahBottomNavigation;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User ssUser = (User) getIntent().getSerializableExtra("SsUser");
        //Toast.makeText(MainActivity.this, ssUser.toString(), Toast.LENGTH_SHORT).show();

        discountRecyclerView = findViewById(R.id.discountedRecycler);
        categoryRecyclerView = findViewById(R.id.categoryRecycler);
        allCategory = findViewById(R.id.allCategoryImage);
        allnewArrival = findViewById(R.id.allnewArrival);
        allbestSeller = findViewById(R.id.allbestSeller);
        allsaleItem = findViewById(R.id.allsaleItem);

        newRecyclerView = findViewById(R.id.listNewArrivals);
        bestRecyclerView = findViewById(R.id.listBestSeller);
        saleRecyclerView = findViewById(R.id.listSaleItem);
        avtUserOnTop = findViewById(R.id.avtUserOnTop);
        Glide.with(this).load("http://"+ Constants.Host +":8080/User/assets/images/avatar/"+
                ssUser.getAvatar()).into(avtUserOnTop);

        allCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> intentCate = new ArrayList<String>();
                for (Product item : categoryList) {
                    intentCate.add(item.getImage());
                }
                Intent i = new Intent(MainActivity.this, AllCategory.class);
                i.putExtra("allCategory", intentCate);
                startActivity(i);
            }
        });

        allnewArrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> intentstatusCate = new ArrayList<String>();
                for (Product item : categoryList) {
                    intentstatusCate.add(item.getTag());
                }
                Intent i = new Intent(MainActivity.this, AllStatusProduct.class);
                i.putExtra("status", "New Arrivals");
                i.putExtra("allstatusCategory", intentstatusCate);

                startActivity(i);
            }
        });
        allbestSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> intentstatusCate = new ArrayList<String>();
                for (Product item : categoryList) {
                    intentstatusCate.add(item.getTag());
                }
                Intent i = new Intent(MainActivity.this, AllStatusProduct.class);
                i.putExtra("status", "Best Sellers");
                i.putExtra("allstatusCategory", intentstatusCate);
                startActivity(i);
            }
        });
        allsaleItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> intentstatusCate = new ArrayList<String>();
                for (Product item : categoryList) {
                    intentstatusCate.add(item.getTag());
                }
                Intent i = new Intent(MainActivity.this, AllStatusProduct.class);
                i.putExtra("status", "Sale Items");
                i.putExtra("allstatusCategory", intentstatusCate);
                startActivity(i);
            }
        });

        // adding data to model
        blogList = new ArrayList<>();
        Call<List<Blog>> lsblogList = ApiClient.getNewsService().getAllBlog();
        lsblogList.enqueue(new Callback<List<Blog>>() {
            @Override
            public void onResponse(Call<List<Blog>> call, Response<List<Blog>> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_LONG).show();
                    blogList.addAll(response.body());
                    setDiscountedRecycler(blogList);
                }
            }

            @Override
            public void onFailure(Call<List<Blog>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_LONG).show();

            }
        });


        // adding data to model
        categoryList = new ArrayList<>();

        Call<List<Product>> lscategoryList = ApiClient.getNewsService().getAllCategory();
        lscategoryList.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_LONG).show();
                    categoryList.addAll(response.body());
                    setCategoryRecycler(categoryList);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_LONG).show();

            }
        });
        // adding data to model
        listNewArrival = new ArrayList<>();
        listBestsaller = new ArrayList<>();
        listSaleItem = new ArrayList<>();

        // adding data to model
        productList = new ArrayList<>();

        Call<List<Product>> lsproductList = ApiClient.getNewsService().getAllProduct("New Arrivals");
        lsproductList.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_LONG).show();
                    listNewArrival.addAll(response.body());
                    setRecentlyViewedRecycler(listNewArrival);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_LONG).show();

            }
        });

        Call<List<Product>> lsproductList1 = ApiClient.getNewsService().getAllProduct("Best Sellers");
        lsproductList1.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_LONG).show();
                    listBestsaller.addAll(response.body());
                    setRecentlyViewedRecycler1(listBestsaller);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_LONG).show();

            }
        });

        Call<List<Product>> lsproductList2 = ApiClient.getNewsService().getAllProduct("Sale Items");
        lsproductList2.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_LONG).show();
                    listSaleItem.addAll(response.body());
                    setRecentlyViewedRecycler2(listSaleItem);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_LONG).show();

            }
        });




//        setRecentlyViewedRecycler(listNewArrival);
//        setRecentlyViewedRecycler1(listBestsaller);
//        setRecentlyViewedRecycler2(listSaleItem);
        // slide
        sliderView = findViewById(R.id.image_slider);

        SliderAdapter sliderAdapter = new SliderAdapter(images);

        // bottom bar
        ahBottomNavigation = findViewById(R.id.bottomBar);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Home", ic_home, R.color.white);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Blog", ic_article, R.color.white);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Cart", ic_cart, R.color.white);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("Feedback", ic_feedback, R.color.white);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem("Profile", ic_person, R.color.white);

        ahBottomNavigation.setAccentColor(getResources().getColor(R.color.bottombar));
        ahBottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE_FORCE);
        ahBottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);
//        ahBottomNavigation.setInactiveColor(getResources().getColor(R.color.black));

        ahBottomNavigation.addItem(item1);
        ahBottomNavigation.addItem(item2);
        ahBottomNavigation.addItem(item3);
        ahBottomNavigation.addItem(item4);
        ahBottomNavigation.addItem(item5);

        ahBottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                return true;
            }
        });

        // add cart animation
        startAnimation = findViewById(R.id.addCartAnimation);
        endAnimation = findViewById(R.id.endAnimation);
        cartIcon = findViewById(R.id.cartIcon);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
    }

    private void setDiscountedRecycler(List<Blog> dataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        discountRecyclerView.setLayoutManager(layoutManager);
        discountedProductAdapter = new BlogAdapter(this,dataList);
        discountRecyclerView.setAdapter(discountedProductAdapter);
    }


    private void setCategoryRecycler(List<Product> categoryDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(this,categoryDataList,getTag());
        categoryRecyclerView.setAdapter(categoryAdapter);
    }

    private void setRecentlyViewedRecycler(List<Product> dataNewArrival) {
        RecyclerView.LayoutManager lmNewArrival = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        newRecyclerView.setLayoutManager(lmNewArrival);
        adapterNewArrival = new ProductAdapter(this, dataNewArrival, new ProductAdapter.IClickAddToCart() {
            @Override
            public void onClickAddToCart(ImageView carticon, Product recentlyViewed) {
                addToCart.translateAnimation(startAnimation, carticon, endAnimation, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        setCountProductInCart(countCartItem +1);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
        newRecyclerView.setAdapter(adapterNewArrival);
    }
    private void setRecentlyViewedRecycler1(List<Product> dataBestsaller) {
        RecyclerView.LayoutManager lmBestSaller = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        bestRecyclerView.setLayoutManager(lmBestSaller);
        adapterBestsaller = new ProductAdapter(this, dataBestsaller, new ProductAdapter.IClickAddToCart() {
            @Override
            public void onClickAddToCart(ImageView carticon, Product recentlyViewed) {
                addToCart.translateAnimation(startAnimation, carticon, endAnimation, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        setCountProductInCart(countCartItem +1);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
        bestRecyclerView.setAdapter(adapterBestsaller);
    }
    private void setRecentlyViewedRecycler2(List<Product> dataSaleItem) {
        RecyclerView.LayoutManager lmSalteItem = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        saleRecyclerView.setLayoutManager(lmSalteItem);
        adapterSaleItem = new ProductAdapter(this, dataSaleItem, new ProductAdapter.IClickAddToCart() {
            @Override
            public void onClickAddToCart(ImageView carticon, Product recentlyViewed) {
                addToCart.translateAnimation(startAnimation, carticon, endAnimation, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        setCountProductInCart(countCartItem +1);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
        saleRecyclerView.setAdapter(adapterSaleItem);
    }


    public void setCountProductInCart(int count){
        countCartItem = count;
        AHNotification notification = new AHNotification.Builder()
                .setText(String.valueOf(countCartItem))
                .build();
        ahBottomNavigation.setNotification(notification, 2);
    }

    public ArrayList<String> getTag(){
        ArrayList<String> intentstatusCate = new ArrayList<String>();
        for (Product item : categoryList) {
            intentstatusCate.add(item.getTag());
        }
        return intentstatusCate;
    }
}
