package com.mystra77.visualnovel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.mystra77.visualnovel.database.MyOpenHelper;
import com.mystra77.visualnovel.fragments.ContinueFragment;
import com.mystra77.visualnovel.fragments.GalleryFragment;
import com.mystra77.visualnovel.fragments.GameStartFragment;
import com.mystra77.visualnovel.fragments.SettingsFragment;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity {
    private Button btnStart, btnContinue, btnGallery, btnSettings;
    private ArrayList<Button> arrayButtons;
    private GameStartFragment gameStartFragment;
    private ContinueFragment continueFragment;
    private GalleryFragment galleryFragment;
    private SettingsFragment settingsFragment;
    private FragmentTransaction transaction;
    private Handler handler;
    private MyOpenHelper moh;
    private SharedPreferences preferencesSettings;
    private MediaPlayer soundClick, soundSaveLoad;
    private Float volumenSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Start the preferences setup
        preferencesSettings = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);

        //Delete Status Bar and insert Animation
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        //establish the view
        setContentView(R.layout.activity_home);

        //Save initual values for preferences
        preferencesSettings.getInt("galleryUnlock", 0);
        preferencesSettings.getFloat("volumenMusic", 1.0f);
        preferencesSettings.getInt("volumenMusicBar", 100);
        volumenSound = preferencesSettings.getFloat("volumenSound", 1.0f);
        preferencesSettings.getInt("volumenSoundBar", 100);
        preferencesSettings.getBoolean("explicitImage", true);

        //Prepare the sounds for the general buttons and save/load
        soundClick = MediaPlayer.create(this, R.raw.sound_click);
        soundClick.setVolume(volumenSound, volumenSound);
        soundSaveLoad = MediaPlayer.create(this, R.raw.sound_load);
        soundSaveLoad.setVolume(volumenSound, volumenSound);

        //Start all fragments
        gameStartFragment = new GameStartFragment();
        continueFragment = new ContinueFragment();
        galleryFragment = new GalleryFragment();
        settingsFragment = new SettingsFragment();

        //Start the Start fragment as the initial
        activeFragment(gameStartFragment);

        //Open database and create the tables/entries
        moh = new MyOpenHelper(this);
        moh.getWritableDatabase();

        //Linking Buttons to variables
        btnStart = this.findViewById(R.id.btnStartGame);
        btnContinue = this.findViewById(R.id.btnContinue);
        btnGallery = this.findViewById(R.id.btnGallery);
        btnSettings = this.findViewById(R.id.btnSettings);

        //Since the first fragment is StartFragment, its button is blocked
        btnStart.setEnabled(false);

        //All buttons will be inserted into an array used later
        arrayButtons = new ArrayList<Button>();
        arrayButtons.add(btnStart);
        arrayButtons.add(btnContinue);
        arrayButtons.add(btnGallery);
        arrayButtons.add(btnSettings);
    }

    /**
     * Function that receives a fragment and through an animation shows it
     *
     * @param fragment that it will be shown
     */
    public void activeFragment(Fragment fragment) {
        transaction = getSupportFragmentManager().beginTransaction();
        //Animation
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        transaction.replace(R.id.frameZoneFragment, fragment);
        //Block pressBack
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /**
     * This function activates gameStartFragment and blocks the button
     */
    public void Start(final View view) {
        disableButton(btnStart);
        activeFragment(gameStartFragment);
    }

    /**
     * This function activates continueFragment and blocks the button
     */
    public void Continue(View view) {
        disableButton(btnContinue);
        activeFragment(continueFragment);
    }

    /**
     * This function activates galleryFragment and blocks the button
     */
    public void Gallery(View view) {
        disableButton(btnGallery);
        activeFragment(galleryFragment);
    }

    /**
     * This function activates settingsFragment and blocks the button
     */
    public void Settings(View view) {
        disableButton(btnSettings);
        activeFragment(settingsFragment);
    }

    /**
     * Function that blocks all the buttons for a while when they are pressed, so we can't open fragments without stopping
     *
     * @param button which will be blocked
     */
    public void disableButton(Button button) {
        //Start the sound
        soundClick.start();
        final Button lockButton = button;
        //Block all buttons
        for (Button buttonPosition : arrayButtons) {
            buttonPosition.setEnabled(false);
        }
        //Unlock all buttons when the handler is finished
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                for (Button buttonPosition : arrayButtons) {
                    buttonPosition.setEnabled(true);
                }
                lockButton.setEnabled(false);
            }
        }, 1300);
    }

    /**
     * Shows a dialogue alert that warns us if we want to leave the application or not.
     * Pressing Yes we will leave the application and pressing No we will continue in it
     */
    public void Exit(View view) {
        soundClick.start();
        new AlertDialog.Builder(this, R.style.AlertDialogCustom)
                .setMessage(R.string.exitQuestion)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * Shows a dialogue alert that warns us if we want to leave the application to go to the Patreon page or not.
     * Pressing Yes we will leave the application and pressing No we will continue in it
     */
    public void goToPatreon(View view) {
        soundClick.start();
        new AlertDialog.Builder(this, R.style.AlertDialogCustom)
                .setMessage(R.string.moveToPatreon)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.patreon.com/mystra77")));
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * Shows a dialogue alert that warns us if we want to leave the application to go to the Twitter page or not.
     * Pressing Yes we will leave the application and pressing No we will continue in it
     */
    public void goToTwitter(View view) {
        soundClick.start();
        new AlertDialog.Builder(this, R.style.AlertDialogCustom)
                .setMessage(R.string.moveToTwitter)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/mystra77")));
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * Leaving it empty we can't press the back button and exit the application
     */
    @Override
    public void onBackPressed() {
    }

    /**
     * In the destroy method we make sure that the sound is released and stops consuming resources
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (soundClick != null) {
            soundClick.stop();
            soundClick.release();
        }
        if (soundSaveLoad != null) {
            soundSaveLoad.stop();
            soundSaveLoad.release();
        }
    }

    /**
     * @return the database for use in the fragments
     */
    public MyOpenHelper getMoh() {
        return moh;
    }

    /**
     * @return the preferencesSettings for use in the fragments
     */
    public SharedPreferences getPreferencesSettings() {
        return preferencesSettings;
    }

    /**
     * @return the soundClick for use in the fragments
     */
    public MediaPlayer getSoundClick() {
        return soundClick;
    }

    /**
     * @return the soundSaveLoad for use in the fragments
     */
    public MediaPlayer getSoundSaveLoad() {
        return soundSaveLoad;
    }

}
