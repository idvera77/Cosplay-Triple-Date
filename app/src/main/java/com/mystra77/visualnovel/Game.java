package com.mystra77.visualnovel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.mystra77.visualnovel.classes.Player;
import com.mystra77.visualnovel.database.MyOpenHelper;
import com.mystra77.visualnovel.stages.Stage;
import com.mystra77.visualnovel.stages.Stage1;
import com.mystra77.visualnovel.stages.Stage2;

public class Game extends AppCompatActivity {
    private MyOpenHelper moh;
    private Player player;
    private SharedPreferences preferencesSettings;
    private ConstraintLayout layoutBackground;
    private MediaPlayer mediaPlayerMusic, mediaPlayerSound;
    private float volumenMusic, volumenSound;
    private boolean explicitImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Delete Status Bar and insert Animation
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        setContentView(R.layout.activity_game);

        //Load preferences
        preferencesSettings = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        volumenMusic = preferencesSettings.getFloat("volumenMusic", 100);
        volumenSound = preferencesSettings.getFloat("volumenSound", 100);
        explicitImage = preferencesSettings.getBoolean("explicitImage", true);

        layoutBackground = findViewById(R.id.stageID);

        //Open database
        moh = new MyOpenHelper(this);
        moh.getWritableDatabase();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            player = (Player) bundle.getSerializable("player");
        }

        //Load all
        if(player.getStage() >= 0 && player.getStage() < 2){
            Stage1 stage1 = new Stage1();
            loadStage(stage1);
        } else if (player.getStage() >= 2){
            Stage2 stage2 = new Stage2();
            loadStage(stage2);
        }

        /*
         if(player.getScore() < 800){
            startService(new Intent(this, ServiceGallery.class));
        }
        */
        player.setScore(player.getScore() + 200);
        player.setStage(player.getStage() + 1);
    }

    public void save1(View view) {
        saveFile(1);
    }

    public void save2(View view) {
        saveFile(2);
    }

    public void save3(View view) {
        saveFile(3);
    }

    public void saveFile(final int saveFileId) {
        new AlertDialog.Builder(this, R.style.AlertDialogCustom)
                .setMessage(R.string.saveGame)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        moh.saveGame(saveFileId, player.getStage(), player.getTsundere(), player.getNeko(), player.getMature(), player.getScore());
                    }
                })
                .setNegativeButton(R.string.no, null)
                .show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayerMusic.stop();
        mediaPlayerMusic.release();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        startActivity(new Intent(this, HomeActivity.class));
    }

    //SEGUIR CON LA GRAN MADRE DE LAS FUNCIONES
    public void loadStage(Stage stage){
        //Image Background
        layoutBackground.setBackground(getDrawable(stage.getStageBackground()));
        //Music Background
        mediaPlayerMusic = MediaPlayer.create(this, stage.getStageMusic());
        mediaPlayerMusic.setVolume(volumenMusic, volumenMusic);
        mediaPlayerMusic.setLooping(true);
        mediaPlayerMusic.start();
    }

    public void loadMusic(){

    }

    public void loadSound(){

    }

}
