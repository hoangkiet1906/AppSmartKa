package com.rajendra.onlinedailygroceries.network;

import com.rajendra.onlinedailygroceries.model.Blog;
import com.rajendra.onlinedailygroceries.model.Product;
import com.rajendra.onlinedailygroceries.model.SignupResponse;
import com.rajendra.onlinedailygroceries.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {
//    @GET("users/")
//    @GET("api/posts")
//    Call<List<User>> getAllNews();
//
//    @GET("api/addacc")
//    Call<SignupResponse> getMessSignup(@Query("user_name") String user_name,
//                                       @Query("password") String password,
//                                       @Query("Cpassword") String Cpassword);
    @GET("api/getapitag")
    Call<List<Product>> getAllCategory();
    @GET("api/getapiProduct")
    Call<List<Product>> getAllProduct(@Query("status") String status);
    @GET("api/getapiProductTag")
    Call<List<Product>> getAllProductTag(@Query("tag") String tag);

    @GET("api/posts")
    Call<List<User>> getAllNews();

    @GET("api/addacc")
    Call<SignupResponse> getMessSignup(@Query("user_name") String user_name,
                                       @Query("password") String password,
                                       @Query("Cpassword") String Cpassword);
    @GET("api/getapiBlog")
    Call<List<Blog>> getAllBlog();
}
