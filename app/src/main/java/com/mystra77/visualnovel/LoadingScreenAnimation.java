package com.mystra77.visualnovel;

import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;


public class LoadingScreenAnimation extends Animation {
    private Context context;
    private ProgressBar progressBar; //Resource progressBar
    private TextView textView; //Resource textView
    private float from, to; //Variable type float to indicate the start and end time

    /**
     * Builder
     *
     * @param progressBar in which we want to insert the animation
     * @param textView    shows the progress number
     * @param from        initial position
     * @param to          end position
     */
    public LoadingScreenAnimation(Context context, ProgressBar progressBar, TextView textView, float from, float to) {
        this.context = context;
        this.progressBar = progressBar;
        this.textView = textView;
        this.from = from;
        this.to = to;
    }

    /**
     * Function that receives the float (from, to) and starts drawing the progress on the bar depending on the time elapsed
     */
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = from + (to - from) * interpolatedTime;
        progressBar.setProgress((int) value);
        textView.setText((int) value + " %");
        if (value == to) {
            context.startActivity(new Intent(context, HomeActivity.class));
        }
    }
}
