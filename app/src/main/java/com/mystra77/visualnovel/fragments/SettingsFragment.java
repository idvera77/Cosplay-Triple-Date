package com.mystra77.visualnovel.fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;


import com.mystra77.visualnovel.HomeActivity;
import com.mystra77.visualnovel.R;


public class SettingsFragment extends Fragment {
    private HomeActivity activity;
    private View view;
    private TextView recibirMusica;
    private SeekBar volume;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = (HomeActivity) getActivity();
        view = inflater.inflate(R.layout.fragment_settings, container, false);

        int volumenMusica;
        volumenMusica = activity.getPreferencesSettings().getInt("volumen", 0);

        recibirMusica = view.findViewById(R.id.recibirMusica);
        volume = view.findViewById(R.id.seekBarMusic);
        volume.setProgress(volumenMusica);
        recibirMusica.setText(volumenMusica+"");

        volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int volume = progress;
                recibirMusica.setText(volume+"");
                SharedPreferences.Editor editor = activity.getPreferencesSettings().edit();
                editor.putInt("volumen", volume);
                editor.commit();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        return view;
    }

}
