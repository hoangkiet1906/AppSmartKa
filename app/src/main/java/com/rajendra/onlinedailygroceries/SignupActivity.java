package com.rajendra.onlinedailygroceries;

import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.rajendra.onlinedailygroceries.model.SignupResponse;
import com.rajendra.onlinedailygroceries.network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar)this.findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        ActionBar var10000 = this.getSupportActionBar();
        if (var10000 != null) {
            var10000.setDisplayShowTitleEnabled(false);
        }

        toolbar.setNavigationOnClickListener((OnClickListener)(new OnClickListener() {
            public final void onClick(View it) {
                SignupActivity.super.onBackPressed();
            }
        }));
        this.setStatusBarWhite((AppCompatActivity)this);

        Button signup = findViewById(R.id.button_signin);
        signup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText username = findViewById(R.id.et_username);
                EditText password = findViewById(R.id.et_password);
                EditText Cpassword = findViewById(R.id.et_confirm_password);
                String susername = String.valueOf(username.getText());
                String spassword = String.valueOf(password.getText());
                String sCpassword = String.valueOf(Cpassword.getText());
                //Toast.makeText(SignupActivity.this, "an", Toast.LENGTH_SHORT).show();
                callapi(susername,spassword,sCpassword);

            }
        });
    }

    private final void setStatusBarWhite(AppCompatActivity activity) {
        if (VERSION.SDK_INT >= 23) {
            Window var10000 = activity.getWindow();
            View var2 = var10000.getDecorView();
            var2.setSystemUiVisibility(8192);
            var10000 = activity.getWindow();
            var10000.setStatusBarColor(-1);
        }

    }
    private void callapi(String u,String p,String cp){
        ApiClient.getNewsService().getMessSignup(u,p,cp).enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SignupActivity.this, response.body().getMess(), Toast.LENGTH_SHORT).show();
                    if (response.body().getMess().equals("Sign Up Success")){
                        Intent intent = new Intent(SignupActivity.this, StartActivity.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                Log.e("error", t.getLocalizedMessage());
            }
        });
    }

}