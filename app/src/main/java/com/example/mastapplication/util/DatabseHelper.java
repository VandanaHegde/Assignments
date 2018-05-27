package com.example.mastapplication.util;

import android.content.Context;

import com.example.mastapplication.model.LoginOutput;

/**
 * Created by vandanahegde on 27/05/18.
 */

public class DatabseHelper {
    private Context context;

    public DatabseHelper(Context context) {
        this.context = context;
    }

    public void insertUserData(LoginOutput loginOutput) {
        DBAdapter dbAdapter = new DBAdapter(context);
        try {
            dbAdapter.open();
            dbAdapter.insertUserData(loginOutput);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbAdapter.close();
        }
    }

    public LoginOutput fetchUserData() {
        LoginOutput output = null;
        DBAdapter dbAdapter = new DBAdapter(context);
        try {
            dbAdapter.open();
            output = dbAdapter.fetchUserData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbAdapter.close();
        }
        return output;
    }

    public void deleteUserData(int id) {
        DBAdapter dbAdapter = new DBAdapter(context);
        try {
            dbAdapter.open();
            dbAdapter.deleteUserData(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbAdapter.close();
        }
    }
}
