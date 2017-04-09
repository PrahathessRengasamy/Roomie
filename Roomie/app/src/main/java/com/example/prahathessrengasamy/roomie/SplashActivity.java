package com.example.prahathessrengasamy.roomie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.widget.ImageView;
/**
 * Created by Llewellyn on 3/27/17.
 */

public class SplashActivity extends Activity {


    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        ImageView ban= (ImageView) findViewById(R.id.banner);
        ban.setImageResource(R.drawable.banner);
        ImageView img = (ImageView) findViewById(R.id.ani);
        img.setBackgroundResource(R.drawable.animate);
        Log.d("splash", "in aplashhhhhh splash act");
        AnimationDrawable splashAnimation = (AnimationDrawable) img.getBackground();
        splashAnimation.start();
        Thread welcomeThread = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                    sleep(3300);  //Delay of 3.3 seconds
                } catch (Exception e) {

                } finally {

                    if (getIntent().getExtras() != null) {
                        System.out.println("");
                        for (String key : getIntent().getExtras().keySet()) {
                            Object value = getIntent().getExtras().get(key);
                            Log.d(TAG, "Key: " + key + " Value: " + value);
                        }
                    }



                    Intent i = new Intent(SplashActivity.this,
                            opening.class);
                    startActivity(i);

                    finish();
                }
            }
        };
        welcomeThread.start();
    }
}
