package com.rajendra.onlinedailygroceries;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rajendra.onlinedailygroceries.adapter.All_Status_All_CategoryAdapter;
import com.rajendra.onlinedailygroceries.adapter.All_Status_All_MenuAdapter;
import com.rajendra.onlinedailygroceries.adapter.All_Status_All_ProductAdapter;
import com.rajendra.onlinedailygroceries.model.All_Status_Item_Product;
import com.rajendra.onlinedailygroceries.model.Product;
import com.rajendra.onlinedailygroceries.model.Products;
import com.rajendra.onlinedailygroceries.network.ApiClient;

import static com.rajendra.onlinedailygroceries.R.drawable.*;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllStatusProduct extends AppCompatActivity {

    RecyclerView productCatRecycler, prodItemRecycler, allMenuRecyclerView;
    All_Status_All_CategoryAdapter allproductCategoryAdapter;
    All_Status_All_ProductAdapter allproductAdapter;
    All_Status_All_MenuAdapter allMenuAdapter;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_status_product);
        //intent
        TextView textView5 = findViewById(R.id.textView5);
        String getIn = getIntent().getStringExtra("status");
        textView5.setText(getIn);
        List<String> productCategoryList = getIntent().getStringArrayListExtra("allstatusCategory");




        setProductRecycler(productCategoryList);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent back = new Intent(AllCategory.this, MainActivity.class);
//                startActivity(back);
                finish();
            }
        });

        if(getIn.equals("New Arrivals") || getIn.equals("Best Sellers") || getIn.equals("Sale Items"))
        {
            Call<List<Product>> lsproductList = ApiClient.getNewsService().getAllProduct(getIn);
            lsproductList.enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    if (response.isSuccessful()) {
//                    Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_LONG).show();
                        List<Product> productsList = new ArrayList<>();
                        productsList.addAll(response.body());
                        setProdItemRecycler(productsList);
                        getAllMenu(productsList);
                    }
                }

                @Override


                public void onFailure(Call<List<Product>> call, Throwable t) {
                    Toast.makeText(AllStatusProduct.this, "fail", Toast.LENGTH_LONG).show();

                }
            });
        }
        else {
            Call<List<Product>> lsproductList = ApiClient.getNewsService().getAllProductTag(getIn);
            lsproductList.enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    if (response.isSuccessful()) {
//                    Toast.makeText(AllStatusProduct.this, response.body().toString(), Toast.LENGTH_LONG).show();
                        List<Product> productsList = new ArrayList<>();
                        productsList.addAll(response.body());
                        setProdItemRecycler(productsList);
                        getAllMenu(productsList);
                    }
                }

                @Override


                public void onFailure(Call<List<Product>> call, Throwable t) {
                    Toast.makeText(AllStatusProduct.this, "fail", Toast.LENGTH_LONG).show();

                }
            });
        }



    }


    private void setProductRecycler(List<String> productCategoryList){
        productCatRecycler = findViewById(R.id.cat_recycler);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        productCatRecycler.setLayoutManager(layoutManager);
        allproductCategoryAdapter = new All_Status_All_CategoryAdapter(this, productCategoryList);
        productCatRecycler.setAdapter(allproductCategoryAdapter);

    }
    private void setProdItemRecycler(List<Product> productsList){
        prodItemRecycler = findViewById(R.id.product_recycler);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        prodItemRecycler.setLayoutManager(layoutManager);
        allproductAdapter = new All_Status_All_ProductAdapter(this, productsList);
        prodItemRecycler.setAdapter(allproductAdapter);

    }
    private void  getAllMenu(List<Product> allmenuList){
        allMenuRecyclerView = findViewById(R.id.all_menu_recycler);
        allMenuAdapter = new All_Status_All_MenuAdapter(this, allmenuList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        allMenuRecyclerView.setLayoutManager(layoutManager);
        allMenuRecyclerView.setAdapter(allMenuAdapter);
        allMenuAdapter.notifyDataSetChanged();
    }

}
