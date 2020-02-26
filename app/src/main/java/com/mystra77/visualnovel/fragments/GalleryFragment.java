package com.mystra77.visualnovel.fragments;


import android.app.AlertDialog;
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

import java.util.ArrayList;

import static android.view.View.GONE;


public class GalleryFragment extends Fragment {
    private HomeActivity activity;
    private View view;
    private ArrayList<Integer> galleryArrayList;
    private ImageView image0, image1, image2, image3, image4, image5, image6, image7, bigImage;
    private int unlockPoint;

    public GalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = (HomeActivity) getActivity();
        view = inflater.inflate(R.layout.fragment_gallery, container, false);

        image0 = view.findViewById(R.id.imageGallery0);
        image1 = view.findViewById(R.id.imageGallery1);
        image2 = view.findViewById(R.id.imageGallery2);
        image3 = view.findViewById(R.id.imageGallery3);
        image4 = view.findViewById(R.id.imageGallery4);
        image5 = view.findViewById(R.id.imageGallery5);
        image6 = view.findViewById(R.id.imageGallery6);
        image7 = view.findViewById(R.id.imageGallery7);

        galleryArrayList = new ArrayList<Integer>();
        galleryArrayList.add(R.mipmap.demon);
        galleryArrayList.add(R.mipmap.albedo);
        galleryArrayList.add(R.mipmap.teacher);
        galleryArrayList.add(R.mipmap.strech);
        galleryArrayList.add(R.mipmap.twob);
        galleryArrayList.add(R.mipmap.tae);
        galleryArrayList.add(R.mipmap.shiranui);
        galleryArrayList.add(R.mipmap.selfie);

        unlockPoint = activity.getMoh().unlockGallerySelect();

        if (unlockPoint >= 100) {
            image0.setImageResource(galleryArrayList.get(0));
            image0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImage(0);
                }
            });
        }
        if (unlockPoint >= 200) {
            image1.setImageResource(galleryArrayList.get(1));
            image1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImage(1);
                }
            });
        }
        if (unlockPoint >= 300) {
            image2.setImageResource(galleryArrayList.get(2));
            image2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImage(2);

                }
            });
        }
        if (unlockPoint >= 400) {
            image3.setImageResource(galleryArrayList.get(3));
            image3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImage(3);

                }
            });
        }
        if (unlockPoint >= 500) {
            image4.setImageResource(galleryArrayList.get(4));
            image4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImage(4);

                }
            });
        }
        if (unlockPoint >= 600) {
            image5.setImageResource(galleryArrayList.get(5));
            image5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImage(5);

                }
            });
        }
        if (unlockPoint >= 700) {
            image6.setImageResource(galleryArrayList.get(6));
            image6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImage(6);

                }
            });
        }
        if (unlockPoint >= 800) {
            image7.setImageResource(galleryArrayList.get(7));
            image7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImage(7);

                }
            });
        }
        return view;
    }

    public void showImage(int positionImage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        bigImage = new ImageView(view.getContext());
        bigImage.setImageResource(galleryArrayList.get(positionImage));
        bigImage.setAdjustViewBounds(true);
        builder.setView(bigImage);
        AlertDialog alert = builder.create();
        alert.show();
        alert.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = alert.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
    }

}
