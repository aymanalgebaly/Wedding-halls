package com.nuller.developer.hall;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.nuller.developer.hall.fragment.MainActivity;

import java.util.Locale;

public class SplashActivity extends AppCompatActivity {

    private ProgressBar mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Locale locale = new Locale("en_US");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getResources().updateConfiguration(config, null);
        setContentView(R.layout.activity_splash);


        mProgress = (ProgressBar) findViewById(R.id.splash_screen_progress_bar);

        // Start lengthy operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                doWork();
                startApp();
                finish();
            }
        }).start();
    }

    private void doWork() {
        for (int progress=0; progress<100; progress+=20) {
            try {
                Thread.sleep(1000);
                mProgress.setProgress(progress);
            } catch (Exception e) {
                e.printStackTrace();
                //Timber.e(e.getMessage());
            }
        }
    }

    private void startApp() {
        Intent intent = new Intent(SplashActivity.this,
                MainActivity.class);
        startActivity(intent);
    }

}

