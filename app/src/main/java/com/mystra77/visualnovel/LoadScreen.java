package com.mystra77.visualnovel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadScreen extends AppCompatActivity {
    ProgressBar progressBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressBar = findViewById(R.id.loadingProgressBar);
        textView = findViewById(R.id.loadingTips);

        progressAnimation();

    }

    public void progressAnimation(){
        LoadingScreenAnimation loadingScreenAnimation = new LoadingScreenAnimation(this, progressBar, textView, 0f, 100f);
        loadingScreenAnimation.setDuration(8000);
        progressBar.setAnimation(loadingScreenAnimation);
    }
}
