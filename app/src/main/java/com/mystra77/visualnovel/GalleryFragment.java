package com.mystra77.visualnovel;


import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment {
    HomeActivity activity;
    View view;
    ArrayList<Integer> galleryArrayList;
    ImageView image0, image1, image2, image3, image4, image5, image6, image7;
    int unlockImage;

    public GalleryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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

        image0.setImageResource(R.mipmap.sinacceso);
        image1.setImageResource(R.mipmap.sinacceso);
        image2.setImageResource(R.mipmap.sinacceso);
        image3.setImageResource(R.mipmap.sinacceso);
        image4.setImageResource(R.mipmap.sinacceso);
        image5.setImageResource(R.mipmap.sinacceso);
        image6.setImageResource(R.mipmap.sinacceso);
        image7.setImageResource(R.mipmap.sinacceso);

        galleryArrayList = new ArrayList<Integer>();
        galleryArrayList.add(R.mipmap.cementerio);
        galleryArrayList.add(R.mipmap.bosque);
        galleryArrayList.add(R.mipmap.montana);
        galleryArrayList.add(R.mipmap.cementerio);
        galleryArrayList.add(R.mipmap.bosque);
        galleryArrayList.add(R.mipmap.montana);
        galleryArrayList.add(R.mipmap.cementerio);
        galleryArrayList.add(R.mipmap.bosque);

        if (activity.unlockGallery() >= 1){
            image0.setImageResource(galleryArrayList.get(0));
            image0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImage(0);
                }
            });
        }
        if (activity.unlockGallery() >= 2){
            image1.setImageResource(galleryArrayList.get(1));
            image1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImage(1);
                }
            });
        }
        if (activity.unlockGallery() >= 3){
            image2.setImageResource(galleryArrayList.get(2));
            image2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImage(2);

                }
            });
        }
        if (activity.unlockGallery() >= 4){
            image3.setImageResource(galleryArrayList.get(3));
            image3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImage(3);

                }
            });
        }
        if (activity.unlockGallery() >= 5){
            image4.setImageResource(galleryArrayList.get(4));
            image4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImage(4);

                }
            });
        }
        if (activity.unlockGallery() >= 6){
            image5.setImageResource(galleryArrayList.get(5));
            image5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImage(5);

                }
            });
        }
        if (activity.unlockGallery() >= 7){
            image6.setImageResource(galleryArrayList.get(6));
            image6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImage(6);

                }
            });
        }
        if (activity.unlockGallery() >= 8){
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

    public void showImage(int positionImage){
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        ImageView imageView = new ImageView(view.getContext());
        imageView.setImageResource(galleryArrayList.get(positionImage));
        imageView.setAdjustViewBounds(true);
        builder.setView(imageView);
        AlertDialog alert = builder.create();
        alert.show();

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = alert.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);

    }
}
