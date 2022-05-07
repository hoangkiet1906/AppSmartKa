package com.rajendra.onlinedailygroceries;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.rajendra.onlinedailygroceries.dialog.ErorrDialog;
import com.rajendra.onlinedailygroceries.dialog.SuccessDialog;
import com.rajendra.onlinedailygroceries.model.User;
import com.rajendra.onlinedailygroceries.network.ApiClient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    List<User> lsdataUser;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_login);
        this.setStatusBarTransparent((AppCompatActivity) this);

        lsdataUser = new ArrayList<>();
        Call<List<User>> lsUser = ApiClient.getNewsService().getAllNews();
        lsUser.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    lsdataUser.addAll(response.body());

                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("error", t.getLocalizedMessage());
            }
        });

        Button signin = findViewById(R.id.button_signin);
        signin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText username = findViewById(R.id.et_username);
                EditText pass = findViewById(R.id.et_password);
                String user = String.valueOf(username.getText());
                String password = String.valueOf(pass.getText());

                if (checkUser(lsdataUser, user, password) == 1){
                    final User ssUser = getUser(lsdataUser, user, password);
                    final SuccessDialog successDialog = new SuccessDialog(LoginActivity.this);
                    successDialog.startDialog();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            successDialog.closeDialog();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("SsUser", ssUser);
                            startActivity(intent);
                        }
                    },4000);


                }
                else {
                    final ErorrDialog erorrDialog = new ErorrDialog(LoginActivity.this);
                    erorrDialog.startDialog();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            erorrDialog.closeDialog();
                        }
                    }, 4000);
                }
            }
        });
//        Button signup = findViewById(R.id.button_signup);
//        signup.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    private final void setStatusBarTransparent(AppCompatActivity activity) {
        if (VERSION.SDK_INT >= 19) {
            activity.getWindow().addFlags(67108864);
        }

        if (VERSION.SDK_INT >= 23) {
            Window var10000 = activity.getWindow();
            View var2 = var10000.getDecorView();
            var2.setSystemUiVisibility(8192);
            var10000 = activity.getWindow();
            var10000.setStatusBarColor(-1);
        }

    }

    public void onClick(View view) {
        if (view.getId() == R.id.button_signup) {
            //Toast.makeText(LoginActivity.this, "signup", Toast.LENGTH_LONG).show();
            this.startActivity(new Intent((Context) this, SignupActivity.class));
        } else if (view.getId() == R.id.button_forgot_password) {
            this.startActivity(new Intent((Context) this, ForgotPasswordActivity.class));
        }
    }

    public int checkUser(List<User> u, String user, String pass) {
        for (User i : u) {
            if (i.getUser_name().equals(user) && i.getPassword().equals(pass)) {
                return 1;
            }
        }
        return 0;
    }
    public User getUser(List<User> u, String user, String pass) {
        for (User i : u) {
            if (i.getUser_name().equals(user) && i.getPassword().equals(pass)) {
                return i;
            }
        }
        return null;
    }
}