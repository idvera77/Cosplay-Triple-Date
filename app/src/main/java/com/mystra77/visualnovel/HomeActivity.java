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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferencesSettings = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);

        //Delete Status Bar and insert Animation
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.activity_home);

        //Save initual values for preferences
        preferencesSettings.getInt("galleryUnlock", 0);
        preferencesSettings.getFloat("volumenMusic", 1.0f);
        preferencesSettings.getInt("volumenMusicBar", 100);
        preferencesSettings.getFloat("volumenSound", 1.0f);
        preferencesSettings.getInt("volumenSoundBar", 100);
        preferencesSettings.getBoolean("explicitImage", true);

        //SoundClick
        soundClick = MediaPlayer.create(this, R.raw.sound_click);
        soundClick.setVolume(0.4f, 0.4f);
        soundSaveLoad = MediaPlayer.create(this, R.raw.start_save_load);
        soundSaveLoad.setVolume(0.4f, 0.4f);

        //Start Fragment
        gameStartFragment = new GameStartFragment();
        continueFragment = new ContinueFragment();
        galleryFragment = new GalleryFragment();
        settingsFragment = new SettingsFragment();

        activeFragment(gameStartFragment);

        //Open database
        moh = new MyOpenHelper(this);
        moh.getWritableDatabase();

        //
        btnStart = this.findViewById(R.id.btnStartGame);
        btnContinue = this.findViewById(R.id.btnContinue);
        btnGallery = this.findViewById(R.id.btnGallery);
        btnSettings = this.findViewById(R.id.btnSettings);
        btnStart.setEnabled(false);

        arrayButtons = new ArrayList<Button>();
        arrayButtons.add(btnStart);
        arrayButtons.add(btnContinue);
        arrayButtons.add(btnGallery);
        arrayButtons.add(btnSettings);

    }

    public void Start(final View view) {
        disableButton(btnStart);
        activeFragment(gameStartFragment);
    }

    public void Continue(View view) {
        disableButton(btnContinue);
        activeFragment(continueFragment);
    }

    public void Gallery(View view) {
        disableButton(btnGallery);
        activeFragment(galleryFragment);
    }

    public void Settings(View view) {
        disableButton(btnSettings);
        activeFragment(settingsFragment);
    }

    public void Exit(View view) {
        soundClick.start();
        new AlertDialog.Builder(this, R.style.AlertDialogCustom)
                .setMessage(R.string.exitQuestion)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                })
                .setNegativeButton(R.string.no, null)
                .show();
    }

    public void goToPatreon(View view) {
        soundClick.start();
        new AlertDialog.Builder(this, R.style.AlertDialogCustom)
                .setMessage(R.string.moveToPatreon)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.patreon.com/mystra77")));
                    }
                })
                .setNegativeButton(R.string.no, null)
                .show();
    }

    public void goToTwitter(View view) {
        soundClick.start();
        new AlertDialog.Builder(this, R.style.AlertDialogCustom)
                .setMessage(R.string.moveToTwitter)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/mystra77")));
                    }
                })
                .setNegativeButton(R.string.no, null)
                .show();
    }

    public void disableButton(Button button) {
        soundClick.start();
        final Button lockButton = button;
        for (Button buttonPosition : arrayButtons) {
            buttonPosition.setEnabled(false);
        }
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

    public void activeFragment(Fragment fragment) {
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        transaction.replace(R.id.frameZoneFragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public MyOpenHelper getMoh() {
        return moh;
    }

    public SharedPreferences getPreferencesSettings() {
        return preferencesSettings;
    }

    public MediaPlayer getSoundClick() {
        return soundClick;
    }

    public MediaPlayer getSoundSaveLoad() {
        return soundSaveLoad;
    }

    @Override
    public void onBackPressed() {

    }

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
}
