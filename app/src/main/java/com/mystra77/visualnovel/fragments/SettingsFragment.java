package com.mystra77.visualnovel.fragments;


import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;


import com.mystra77.visualnovel.HomeActivity;
import com.mystra77.visualnovel.R;


public class SettingsFragment extends Fragment {
    private HomeActivity activity;
    private View view;
    private SeekBar musicSeekBar, soundSeekBar;
    private CheckBox musicOff, soundOff, explicitOff;
    private int volumenMusicBar, volumeSoundBar;
    private float volumenMusic, volumenSound;
    private MediaPlayer mediaPlayerMusic, mediaPlayerSound;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = (HomeActivity) getActivity();
        view = inflater.inflate(R.layout.fragment_settings, container, false);

        musicSeekBar = view.findViewById(R.id.seekBarMusic);
        soundSeekBar = view.findViewById(R.id.seekBarSound);
        musicOff = view.findViewById(R.id.checkBoxOffMusic);
        soundOff = view.findViewById(R.id.checkBoxOffSound);
        explicitOff = view.findViewById(R.id.checkBoxOffExplicit);

        volumenMusic = activity.getPreferencesSettings().getFloat("volumenMusic", 1.0f);
        volumenMusicBar = activity.getPreferencesSettings().getInt("volumenMusicBar", 100);
        volumenSound = activity.getPreferencesSettings().getFloat("volumenSound", 1.0f);
        volumeSoundBar = activity.getPreferencesSettings().getInt("volumenSoundBar", 100);

        if (volumenMusicBar == 0) {
            musicOff.setChecked(true);
        }

        if (volumeSoundBar == 0) {
            soundOff.setChecked(true);
        }

        if (!activity.getPreferencesSettings().getBoolean("explicitImage", true)) {
            explicitOff.setChecked(true);
        }

        musicSeekBar.setProgress(volumenMusicBar);
        soundSeekBar.setProgress(volumeSoundBar);
        mediaPlayerMusic = MediaPlayer.create(view.getContext(), R.raw.test_music);
        mediaPlayerSound = MediaPlayer.create(view.getContext(), R.raw.test_sound);

        musicSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                volumenMusicBar = progress;
                volumenMusic = (float) (1 - (Math.log(100 - progress) / Math.log(100)));
                SharedPreferences.Editor editor = activity.getPreferencesSettings().edit();
                editor.putFloat("volumenMusic", volumenMusic);
                editor.putInt("volumenMusicBar", volumenMusicBar);
                editor.commit();
                if (progress == 0) {
                    musicOff.setChecked(true);
                } else {
                    musicOff.setChecked(false);
                }
                mediaPlayerMusic.setVolume(volumenMusic, volumenMusic);
                mediaPlayerSound.setLooping(true);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mediaPlayerMusic.start();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayerMusic.pause();
            }
        });

        soundSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                volumeSoundBar = progress;
                volumenSound = (float) (1 - (Math.log(100 - progress) / Math.log(100)));
                SharedPreferences.Editor editor = activity.getPreferencesSettings().edit();
                editor.putFloat("volumenSound", volumenSound);
                editor.putInt("volumenSoundBar", volumeSoundBar);
                editor.commit();
                if (progress == 0) {
                    soundOff.setChecked(true);
                } else {
                    soundOff.setChecked(false);
                }
                mediaPlayerSound.setVolume(volumenSound, volumenSound);
                mediaPlayerSound.setLooping(true);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mediaPlayerSound.start();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayerSound.pause();
            }
        });

        musicOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CompoundButton) view).isChecked()) {
                    SharedPreferences.Editor editor = activity.getPreferencesSettings().edit();
                    editor.putFloat("volumenMusic", 0);
                    editor.putInt("volumenMusicBar", 0);
                    editor.commit();
                    musicSeekBar.setProgress(0);
                }
            }
        });

        soundOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CompoundButton) view).isChecked()) {
                    SharedPreferences.Editor editor = activity.getPreferencesSettings().edit();
                    editor.putFloat("volumenSound", 0);
                    editor.putInt("volumenSound", 0);
                    editor.commit();
                    soundSeekBar.setProgress(0);
                }
            }
        });

        explicitOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CompoundButton) view).isChecked()) {
                    SharedPreferences.Editor editor = activity.getPreferencesSettings().edit();
                    editor.putBoolean("explicitImage", false);
                    editor.commit();
                }
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayerMusic != null) {
            mediaPlayerMusic.release();
        }
        if (mediaPlayerSound != null) {
            mediaPlayerSound.release();
        }
    }
}
