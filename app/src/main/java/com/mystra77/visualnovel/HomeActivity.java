package com.mystra77.visualnovel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.mystra77.visualnovel.database.MyOpenHelper;
import com.mystra77.visualnovel.fragments.ContinueFragment;
import com.mystra77.visualnovel.fragments.GalleryFragment;
import com.mystra77.visualnovel.fragments.GameStartFragment;
import com.mystra77.visualnovel.fragments.SettingsFragment;


public class HomeActivity extends AppCompatActivity {
    private int unlockImageGallery;
    private Button btnStart, btnContinue, btnGallery, btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Delete Status Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.activity_home);

        //First Fragment
        GameStartFragment fragment = new GameStartFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameZoneFragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

        //Open database
        MyOpenHelper moh = new MyOpenHelper(this);
        SQLiteDatabase database = moh.getWritableDatabase();

        moh.saveGame(database, 1, 0, 0,0,0,0);

        btnStart = this.findViewById(R.id.btnStartGame);
        btnStart.setEnabled(false);
        btnContinue = this.findViewById(R.id.btnContinue);
        btnGallery = this.findViewById(R.id.btnGallery);
        btnSettings = this.findViewById(R.id.btnSettings);

    }

    public void Start(final View view) {
        disableButton(btnStart);
        GameStartFragment fragment = new GameStartFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        transaction.replace(R.id.frameZoneFragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();


        /*
        startActivity(new Intent(view.getContext(), GameStart.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

          requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }
         */
    }

    public void Continue(View view) {
        disableButton(btnContinue);
        ContinueFragment fragment = new ContinueFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        transaction.replace(R.id.frameZoneFragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void Gallery(View view) {
        disableButton(btnGallery);
        GalleryFragment fragment = new GalleryFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        transaction.replace(R.id.frameZoneFragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        unlockImageGallery++;

    }

    public void Settings(View view) {
        disableButton(btnSettings);
        SettingsFragment fragment = new SettingsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        transaction.replace(R.id.frameZoneFragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void Exit(View view) {
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

    public int unlockGallery() {
        return unlockImageGallery;
    }


    public void goToPatreon(View view) {
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

    @Override
    public void onBackPressed() {

    }

    public void disableButton(Button button){
        btnStart.setEnabled(true);
        btnContinue.setEnabled(true);
        btnGallery.setEnabled(true);
        btnSettings.setEnabled(true);
        btnStart.setEnabled(true);
        btnStart.setEnabled(true);
        if(button == btnStart){
            btnStart.setEnabled(false);
        }
        if(button == btnContinue){
            btnContinue.setEnabled(false);
        }
        if(button == btnGallery){
            btnGallery.setEnabled(false);
        }
        if(button == btnSettings){
            btnSettings.setEnabled(false);
        }
    }
}
