package com.mystra77.visualnovel.fragments;


import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.mystra77.visualnovel.HomeActivity;
import com.mystra77.visualnovel.R;


public class GalleryFragment extends Fragment {
    private HomeActivity activity;
    private View view;
    private ImageView image0;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private ImageView image5;
    private ImageView image6;
    private ImageView image7;
    private ImageView bigImage;
    private int unlockPointDB;
    private int unlockPointPreference;
    private AlertDialog alert;

    public GalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Establish the view
        view = inflater.inflate(R.layout.fragment_gallery, container, false);

        //add the activity of HomeActivity
        activity = (HomeActivity) getActivity();

        //Linking ImageView to variables
        image0 = view.findViewById(R.id.imageGallery0);
        image1 = view.findViewById(R.id.imageGallery1);
        image2 = view.findViewById(R.id.imageGallery2);
        image3 = view.findViewById(R.id.imageGallery3);
        image4 = view.findViewById(R.id.imageGallery4);
        image5 = view.findViewById(R.id.imageGallery5);
        image6 = view.findViewById(R.id.imageGallery6);
        image7 = view.findViewById(R.id.imageGallery7);

        /*
        Call the database to use the function unlockGallery
        The maximum number of points is stored in the variable unlockPointDB
        */
        unlockPointDB = activity.getMoh().unlockGallerySelect();
        //If unlockPointDB has more points than those saved in the program preferences, it updates this preference
        if (unlockPointDB > activity.getPreferencesSettings().getInt("galleryUnlock", 0)) {
            SharedPreferences.Editor editor = activity.getPreferencesSettings().edit();
            editor.putInt("galleryUnlock", unlockPointDB);
            editor.commit();
        }

        // Store the points saved in preferences
        unlockPointPreference = activity.getPreferencesSettings().getInt("galleryUnlock", 0);
        // Every 250 points 2 images of the gallery are unlocked, the buttons are enabled to access them
        if (unlockPointPreference >= 250) {
            image0.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image0.setImageResource(R.drawable.image0short);
            image0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImage(R.drawable.image0full);
                }
            });
            image1.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image1.setImageResource(R.drawable.image1short);
            image1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImage(R.drawable.image1full);
                }
            });
        }
        if (unlockPointPreference >= 500) {
            image2.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image2.setImageResource(R.drawable.image2short);
            image2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImage(R.drawable.image2full);
                }
            });
            image3.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image3.setImageResource(R.drawable.image3short);
            image3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImage(R.drawable.image3full);
                }
            });
        }
        if (unlockPointPreference >= 750) {
            image4.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image4.setImageResource(R.drawable.image4short);
            image4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImage(R.drawable.image4full);
                }
            });
            image5.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image5.setImageResource(R.drawable.image5short);
            image5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImage(R.drawable.image5full);
                }
            });
        }
        if (unlockPointPreference >= 1000) {
            image6.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image6.setImageResource(R.drawable.image6short);
            image6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImage(R.drawable.image6full);

                }
            });
            image7.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image7.setImageResource(R.drawable.image7short);
            image7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImage(R.drawable.image7full);

                }
            });
        }
        return view;
    }

    /**
     * Function with a complex dialogue alert which shows the full image on almost the entire screen
     *
     * @param imageResource Indicates the position of the image
     */
    public void showImage(int imageResource) {
        activity.getSoundClick().start();
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        bigImage = new ImageView(view.getContext());
        bigImage.setImageResource(imageResource);
        builder.setView(bigImage);
        alert = builder.create();
        alert.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        alert.getWindow().getDecorView().setBackgroundResource(R.color.transparentBlack);
        alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alert.show();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = alert.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.gc();
    }

}
