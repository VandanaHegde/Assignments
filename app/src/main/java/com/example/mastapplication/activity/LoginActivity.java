package com.example.mastapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mastapplication.R;
import com.example.mastapplication.fragments.LoginFragment;

/**
 * Created by vandanahegde on 26/05/18.
 */

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        navigateToLoginFragmnet();
    }

    private void navigateToLoginFragmnet() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new LoginFragment()).commit();
    }
}
