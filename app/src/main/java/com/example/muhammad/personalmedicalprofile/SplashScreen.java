package com.example.muhammad.personalmedicalprofile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {

    private ProgressBar progressBar;
    private int setProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        progressBar = findViewById(R.id.progressBarId);

        Thread thread =  new Thread(new Runnable() {
            @Override
            public void run() {
              doWork();
              startApp();
            }
        });

        thread.start();
    }

    public void doWork(){

        for(setProgress =20; setProgress<=100; setProgress=setProgress+20){
            try {
                Thread.sleep(1000);
                progressBar.setProgress(setProgress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startApp(){
        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
