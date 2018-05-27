package com.example.mastapplication.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by vandanahegde on 26/05/18.
 */

public class CommonUtil {
    private final static String TAG = CommonUtil.class.getName();

    public static String fetchGetResponse(String url) {
        String responseString = null;
        try {
            Response response = null;
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(40, TimeUnit.SECONDS)
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            response = client.newCall(request).execute();
            responseString = response.body().string();
            if (responseString == null) responseString = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(responseString != null) Log.e(TAG, responseString);
        else Log.e(TAG, "Empty response");
        return responseString;
    }

    public static boolean isValidString(String s) {
        return s != null && (!s.isEmpty()) && (!s.trim().isEmpty()) && s.trim().length() > 0 && !s.equalsIgnoreCase("null");
    }

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static boolean checkDataConnection(Context context) {
        try {
            if (context != null) {
                ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                if (activeNetwork != null) {
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void hideSoftKeyboard(View view, Context context) {
        try {
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
