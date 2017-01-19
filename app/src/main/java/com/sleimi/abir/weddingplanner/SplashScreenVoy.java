package com.sleimi.abir.weddingplanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreenVoy extends Activity {
    /*
        Created by Abir Sleimi
        Git AbirSleimi
         */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_voy);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashScreenVoy.this,Voyage.class);
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

