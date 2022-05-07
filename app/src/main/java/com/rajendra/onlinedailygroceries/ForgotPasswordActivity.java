package com.rajendra.onlinedailygroceries;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ForgotPasswordActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_forgot_password);
        Toolbar toolbar = (Toolbar)this.findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        ActionBar var10000 = this.getSupportActionBar();
        if (var10000 != null) {
            var10000.setDisplayShowTitleEnabled(false);
        }

        toolbar.setNavigationOnClickListener((OnClickListener)(new OnClickListener() {
            public void onClick(View it) {
                ForgotPasswordActivity.super.onBackPressed();
            }
        }));
        this.setStatusBarWhite((AppCompatActivity)this);
    }

    public  void setStatusBarWhite(AppCompatActivity activity) {
        if (VERSION.SDK_INT >= 23) {
            Window var10000 = activity.getWindow();
            View var2 = var10000.getDecorView();
            var2.setSystemUiVisibility(8192);
            var10000 = activity.getWindow();
            var10000.setStatusBarColor(-1);
        }
    }
}
