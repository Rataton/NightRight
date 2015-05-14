package com.petitemasrata.nightright.UserInterface.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.petitemasrata.nightright.R;

public class SplashActivity extends Activity {

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_splash);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{

                    SharedPreferences sharedPref = getSharedPreferences("fb_user_prefs", MODE_PRIVATE);
                    boolean isLogin = sharedPref.getBoolean("is_login", false);

                    if (isLogin){
                        i = new Intent("com.petitemasrata.nightright.MAINACTIVITY");
                    } else {
                        i = new Intent("com.petitemasrata.nightright.FACEACTIVITY");
                    }
                    startActivity(i);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}
