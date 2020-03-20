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
    private SeekBar musicSeekBar;
    private SeekBar soundSeekBar;
    private CheckBox musicOff;
    private CheckBox soundOff;
    private CheckBox explicitOff;
    private int volumenMusicBar;
    private int volumeSoundBar;
    private float volumenMusic;
    private float volumenSound;
    private MediaPlayer mediaPlayerMusic;
    private MediaPlayer mediaPlayerSound;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Establish the view
        view = inflater.inflate(R.layout.fragment_settings, container, false);

        //add the activity of HomeActivity
        activity = (HomeActivity) getActivity();

        //Linking Resources(seekBar and checkButtons) to variables
        musicSeekBar = view.findViewById(R.id.seekBarMusic);
        soundSeekBar = view.findViewById(R.id.seekBarSound);
        musicOff = view.findViewById(R.id.checkBoxOffMusic);
        soundOff = view.findViewById(R.id.checkBoxOffSound);
        explicitOff = view.findViewById(R.id.checkBoxOffExplicit);

        //Insert preferences value in variables
        volumenMusic = activity.getPreferencesSettings().getFloat("volumenMusic", 1.0f);
        volumenMusicBar = activity.getPreferencesSettings().getInt("volumenMusicBar", 100);
        volumenSound = activity.getPreferencesSettings().getFloat("volumenSound", 1.0f);
        volumeSoundBar = activity.getPreferencesSettings().getInt("volumenSoundBar", 100);

        /*
        When the fragment is created, the checks are marked if the following conditions are met
        */
        if (volumenMusicBar == 0) {
            musicOff.setChecked(true);
        }
        if (volumeSoundBar == 0) {
            soundOff.setChecked(true);
        }
        if (!activity.getPreferencesSettings().getBoolean("explicitImage", true)) {
            explicitOff.setChecked(true);
        }

        /*
        When the fragment is created, the progressBar set your progress if the following conditions are met
        */
        musicSeekBar.setProgress(volumenMusicBar);
        soundSeekBar.setProgress(volumeSoundBar);

        /*
        Audio tracks are added
        */
        mediaPlayerMusic = MediaPlayer.create(view.getContext(), R.raw.test_music);
        mediaPlayerSound = MediaPlayer.create(view.getContext(), R.raw.test_sound);

        /**
         * musicSeekBar event
         * Increases or decreases the volume of the background music
         */
        musicSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // The progress of the bar is inserted into a variable and this is also transformed into a float variable
                volumenMusicBar = progress;
                volumenMusic = (float) (1 - (Math.log(100 - progress) / Math.log(100)));
                //Both variables are stored in preference
                SharedPreferences.Editor editor = activity.getPreferencesSettings().edit();
                editor.putFloat("volumenMusic", volumenMusic);
                editor.putInt("volumenMusicBar", volumenMusicBar);
                editor.commit();
                //If the condition is met, check or uncheck the musicButtonCheck
                if (progress == 0) {
                    musicOff.setChecked(true);
                } else {
                    musicOff.setChecked(false);
                }
                // Adjusts the volume and leaves it in a loop
                mediaPlayerMusic.setVolume(volumenMusic, volumenMusic);
                mediaPlayerSound.setLooping(true);
            }

            //Moving or pressing the bar starts the music
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mediaPlayerMusic.start();
            }

            //When the movement stops, the song pauses
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayerMusic.pause();
            }
        });

        /**
         * soundSeekBar event
         * Increases or decreases the volume of the sounds
         */
        soundSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // The progress of the bar is inserted into a variable and this is also transformed into a float variable
                volumeSoundBar = progress;
                volumenSound = (float) (1 - (Math.log(100 - progress) / Math.log(100)));
                //Both variables are stored in preference
                SharedPreferences.Editor editor = activity.getPreferencesSettings().edit();
                editor.putFloat("volumenSound", volumenSound);
                editor.putInt("volumenSoundBar", volumeSoundBar);
                editor.commit();
                //If the condition is met, check or uncheck the musicButtonCheck
                if (progress == 0) {
                    soundOff.setChecked(true);
                } else {
                    soundOff.setChecked(false);
                }
                //Changes the value of the sounds used in all the fragments
                soundChanges();
                // Adjusts the volume and leaves it in a loop
                mediaPlayerSound.setVolume(volumenSound, volumenSound);
                mediaPlayerSound.setLooping(true);
            }

            //Moving or pressing the bar starts the sound
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mediaPlayerSound.start();
            }

            //When the movement stops, the sound pauses
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayerSound.pause();
                soundChanges();
            }
        });

        /**
         * checkButton musicOff Event
         */
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

        /**
         * checkButton soundOff Event
         * If we mark the button it decreases the music volume to 0 and saves it in preferences
         * It also leaves the music bar at 0
         */
        soundOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CompoundButton) view).isChecked()) {
                    SharedPreferences.Editor editor = activity.getPreferencesSettings().edit();
                    editor.putFloat("volumenSound", 0);
                    editor.putInt("volumenSoundBar", 0);
                    editor.commit();
                    soundSeekBar.setProgress(0);
                }
            }
        });

        /**
         * checkButton explicitOff Event
         * If we mark the button saves the Boolean value in preferences, this will be used later to not show images with explicit sexual content
         */
        explicitOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CompoundButton) view).isChecked()) {
                    SharedPreferences.Editor editor = activity.getPreferencesSettings().edit();
                    editor.putBoolean("explicitImage", false);
                    editor.commit();
                } else {
                    SharedPreferences.Editor editor = activity.getPreferencesSettings().edit();
                    editor.putBoolean("explicitImage", true);
                    editor.commit();
                }
            }
        });

        return view;
    }

    /**
     * This function changes the value of the sounds used in all the fragments
     */
    public void soundChanges() {
        activity.getSoundClick().setVolume(volumenSound, volumenSound);
        activity.getSoundSaveLoad().setVolume(volumenSound, volumenSound);
    }

    /**
     * In the destroy method we make sure that the sound is released and stops consuming resources
     */
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
