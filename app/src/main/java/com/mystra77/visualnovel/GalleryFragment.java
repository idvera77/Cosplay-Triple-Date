package com.mystra77.visualnovel;


import android.app.AlertDialog;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.time.LocalDateTime;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment {
    HomeActivity activity;
    View view;
    ArrayList<Integer> galleryArrayList;
    ImageView image0, image1, image2, image3, image4, image5, image6, image7;

    public GalleryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity = (HomeActivity) getActivity();
        view = inflater.inflate(R.layout.fragment_gallery, container, false);

        galleryArrayList = new ArrayList<Integer>();
        galleryArrayList.add(R.mipmap.montana);
        galleryArrayList.add(R.mipmap.montana);
        galleryArrayList.add(R.mipmap.bosque);
        galleryArrayList.add(R.mipmap.montana);
        galleryArrayList.add(R.mipmap.montana);
        galleryArrayList.add(R.mipmap.montana);
        galleryArrayList.add(R.mipmap.montana);
        galleryArrayList.add(R.mipmap.montana);


        image0 = view.findViewById(R.id.imageGallery0);
        image1 = view.findViewById(R.id.imageGallery1);
        image2 = view.findViewById(R.id.imageGallery2);
        image3 = view.findViewById(R.id.imageGallery3);
        image4 = view.findViewById(R.id.imageGallery4);
        image5 = view.findViewById(R.id.imageGallery5);
        image6 = view.findViewById(R.id.imageGallery6);
        image7 = view.findViewById(R.id.imageGallery7);

        image0.setImageResource(galleryArrayList.get(0));
        image1.setImageResource(galleryArrayList.get(1));
        image2.setImageResource(galleryArrayList.get(2));
        image3.setImageResource(galleryArrayList.get(3));
        image4.setImageResource(galleryArrayList.get(4));
        image5.setImageResource(galleryArrayList.get(5));
        image6.setImageResource(galleryArrayList.get(6));
        image7.setImageResource(galleryArrayList.get(7));

        ArrayList<ImageView> imageViewArrayList = new ArrayList<ImageView>();
        imageViewArrayList.add(image0);
        imageViewArrayList.add(image1);

        for (int i = 0; i < imageViewArrayList.size(); i++){
            final int positionImage = i;
            imageViewArrayList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImage(positionImage);

                }
            });
        }
        image0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showImage(0);

            }
        });
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImage(1);

            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImage(2);

            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImage(3);

            }
        });
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImage(4);

            }
        });
        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImage(5);

            }
        });
        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImage(6);

            }
        });
        image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImage(7);

            }
        });
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
        System.out.println("pollo");
    }
}
