package com.sleimi.abir.weddingplanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/*
    Created by Abir Sleimi
    Git AbirSleimi
     */

public class SplashScreenCoiff extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_coiff);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashScreenCoiff.this,CoiffureF.class);
                    startActivity(intent);
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
