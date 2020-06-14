package com.sachtech.datingapp.utils;

import android.app.ProgressDialog;
import android.content.Context;

public final class Commons {

 public static  Context context;
    public Commons(Context context)
    {
        Commons.context =context;
    }
   static ProgressDialog progressDialog;
   public static void showProgress()
    {
        progressDialog=new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

 public  static void hideProgress()
    {
        if(progressDialog!=null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }
}
