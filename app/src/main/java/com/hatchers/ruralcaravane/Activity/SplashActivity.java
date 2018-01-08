package com.hatchers.ruralcaravane.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.hatchers.ruralcaravane.CustomerRegistration.CustomerRegistrationActivity;
import com.hatchers.ruralcaravane.Pref_Manager.PrefManager;
import com.hatchers.ruralcaravane.R;
import com.hatchers.ruralcaravane.user_login.LoginActivity;

public class SplashActivity extends AppCompatActivity {
    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        prefManager=new PrefManager(this);

        int SPLASH_TIME_OUT = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                check();
            }
        }, SPLASH_TIME_OUT);


        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window =getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.DarkBrown));
        }
    }


    public void check()
    {
        prefManager = new PrefManager(this);

        if (prefManager.isLoggedIn()) {

            Intent intent=new Intent(SplashActivity.this, CustomerRegistrationActivity.class);
            startActivity(intent);
            finish();

        }
        else
        {
            Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
