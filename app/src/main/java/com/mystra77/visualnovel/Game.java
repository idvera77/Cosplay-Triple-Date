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
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.mystra77.visualnovel.characters.GirlCharacters;
import com.mystra77.visualnovel.characters.Mature;
import com.mystra77.visualnovel.characters.Neko;
import com.mystra77.visualnovel.characters.Tsundere;
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
    private MediaPlayer mediaPlayerMusic, mediaPlayerSound, soundClick;
    private float volumenMusic, volumenSound;
    private boolean explicitImage;
    private String textGirl;
    private Button buttonLog, buttonExit;

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
        buttonLog = findViewById(R.id.btnLog);
        buttonExit = findViewById(R.id.btnExit);

        soundClick = MediaPlayer.create(this, R.raw.sound_click);
        soundClick.setVolume(0.4f, 0.4f);

        //Open database
        moh = new MyOpenHelper(this);
        moh.getWritableDatabase();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            player = (Player) bundle.getSerializable("player");
        }

        //Load all
        if (player.getStage() >= 1 && player.getStage() < 2) {
            Stage1 stage1 = new Stage1();
            loadStage(stage1, 0, 0);
        } else if (player.getStage() >= 2) {
            Stage2 stage2 = new Stage2();
            loadStage(stage2, 0, 1);
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

    //SEGUIR CON LA GRAN MADRE DE LAS FUNCIONES
    public void loadStage(Stage stage, int selectGirl, int selectStageText) {
        //Image Background
        layoutBackground.setBackground(getDrawable(stage.getStageBackground()));
        //Music Background
        mediaPlayerMusic = MediaPlayer.create(this, stage.getStageMusic());
        mediaPlayerMusic.setVolume(volumenMusic, volumenMusic);
        mediaPlayerMusic.setLooping(true);
        mediaPlayerMusic.start();

        if (selectGirl == 0) {
            Tsundere tsundere = new Tsundere();
            selectGirlDialog(tsundere, selectStageText);
        }
        if (selectGirl == 1) {
            Neko neko = new Neko();
            selectGirlDialog(neko, selectStageText);
        }
        if (selectGirl == 2) {
            Mature mature = new Mature();
            selectGirlDialog(mature, selectStageText);
        }
    }

    public void selectGirlDialog(GirlCharacters girlCharacters, int selectStage) {
        if (selectStage == 1) {
            textGirl = girlCharacters.getTextStage1();
        }
        if (selectStage == 2) {
            textGirl = girlCharacters.getTextStage2();
        }
        if (selectStage == 3) {
            textGirl = girlCharacters.getTextStage3();
        }
        if (selectStage == 4) {
            textGirl = girlCharacters.getTextStage4();
        }
    }

    public void loadSound(GirlCharacters girl, int emotion) {
        if (emotion == 0) {
            mediaPlayerSound = MediaPlayer.create(this, girl.getImageNormal());
        }
        if (emotion == 1) {
            mediaPlayerSound = MediaPlayer.create(this, girl.getImageLaught());
        }
        if (emotion == 2) {
            mediaPlayerSound = MediaPlayer.create(this, girl.getImageLaught());
        }
        mediaPlayerSound.setVolume(volumenMusic, volumenMusic);
        mediaPlayerSound.setLooping(true);
        mediaPlayerSound.start();
    }

    public void openLog(View view) {
    }

    public void backToMainMenu(View view) {
        soundClick.start();
        new AlertDialog.Builder(this, R.style.AlertDialogCustom)
                .setMessage(R.string.exitQuestion)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        back();
                    }
                })
                .setNegativeButton(R.string.no, null)
                .show();
    }

    public void back() {
        this.finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void onBackPressed() {

    }

    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayerMusic != null) {
            mediaPlayerMusic.stop();
            mediaPlayerMusic.release();
        }
        if (mediaPlayerSound != null) {
            mediaPlayerSound.stop();
            mediaPlayerSound.release();
        }
        if (soundClick != null) {
            soundClick.release();
        }
    }
}
