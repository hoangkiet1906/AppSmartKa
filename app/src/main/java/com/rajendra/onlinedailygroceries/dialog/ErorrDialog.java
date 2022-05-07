package com.rajendra.onlinedailygroceries.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.rajendra.onlinedailygroceries.R;

public class ErorrDialog {
    Activity activity;
    AlertDialog dialog;

    public ErorrDialog(Activity myactivity){
        activity = myactivity;
    }
    public void startDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.w_dialog_error,null));
        builder.setCancelable(false);
        dialog = builder.show();
        dialog.show();
    }
    public void closeDialog(){
        dialog.dismiss();
    }
}
