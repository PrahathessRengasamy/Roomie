package com.example.prahathessrengasamy.roomie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;
/**
 * Created by Llewellyn on 3/27/17.
 */

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        ImageView img = (ImageView) findViewById(R.id.ani);
        img.setBackgroundResource(R.drawable.animate);
        AnimationDrawable splashAnimation = (AnimationDrawable) img.getBackground();
        splashAnimation.start();
        Thread welcomeThread = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                    sleep(3000);  //Delay of 10 seconds
                } catch (Exception e) {

                } finally {

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
