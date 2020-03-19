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

        import java.util.ArrayList;


public class GalleryFragment extends Fragment {
    private HomeActivity activity;
    private View view;
    private ArrayList<Integer> galleryArrayList;
    private ImageView image0, image1, image2, image3, image4, image5, image6, image7, bigImage;
    private int unlockPointDB, unlockPointPreference;

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

        //Load the galleryArrayList with images save in the app
        galleryArrayList = new ArrayList<Integer>();
        galleryArrayList.add(R.mipmap.demon);
        galleryArrayList.add(R.mipmap.albedo);
        galleryArrayList.add(R.mipmap.teacher);
        galleryArrayList.add(R.mipmap.strech);
        galleryArrayList.add(R.mipmap.twob);
        galleryArrayList.add(R.mipmap.tae);
        galleryArrayList.add(R.mipmap.shiranui);
        galleryArrayList.add(R.mipmap.selfie);

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
            image0.setImageResource(galleryArrayList.get(0));
            image0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImage(0);
                }
            });
            image1.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image1.setImageResource(galleryArrayList.get(1));
            image1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImage(1);
                }
            });
        }
        if (unlockPointPreference >= 500) {
            image2.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image2.setImageResource(galleryArrayList.get(2));
            image2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImage(2);
                }
            });
            image3.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image3.setImageResource(galleryArrayList.get(3));
            image3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImage(3);
                }
            });
        }
        if (unlockPointPreference >= 750) {
            image4.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image4.setImageResource(galleryArrayList.get(4));
            image4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImage(4);
                }
            });
            image5.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image5.setImageResource(galleryArrayList.get(5));
            image5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImage(5);
                }
            });
        }
        if (unlockPointPreference >= 1000) {
            image6.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image6.setImageResource(galleryArrayList.get(6));
            image6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImage(6);

                }
            });
            image7.setScaleType(ImageView.ScaleType.CENTER_CROP);
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

    /**
     * Function with a complex dialogue alert which shows the full image on almost the entire screen
     *
     * @param positionImage Indicates the position of the array that has the image
     */
    public void showImage(int positionImage) {
        activity.getSoundClick().start();
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        bigImage = new ImageView(view.getContext());
        bigImage.setImageResource(galleryArrayList.get(positionImage));
        builder.setView(bigImage);
        AlertDialog alert = builder.create();
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
