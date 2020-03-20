package com.mystra77.visualnovel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadScreen extends AppCompatActivity {
    ProgressBar progressBar; //Resource progressBar
    TextView textView; //Resource textView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_screen);
        //Delete Status Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Linking resources to variables
        progressBar = findViewById(R.id.loadingProgressBar);
        textView = findViewById(R.id.loadingTips);
        //Start the animation
        progressAnimation();
    }

    /**
     * This function allows the progress bar to have an animation, its load depends on the duration we indicate and also its start/end point
     */
    public void progressAnimation() {
        LoadingScreenAnimation loadingScreenAnimation = new LoadingScreenAnimation(this, progressBar, textView, 0f, 100f);
        loadingScreenAnimation.setDuration(2000);
        progressBar.setAnimation(loadingScreenAnimation);
    }

}
