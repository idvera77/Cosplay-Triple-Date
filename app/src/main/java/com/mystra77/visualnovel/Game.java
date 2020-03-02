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
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mystra77.visualnovel.characters.GirlCharacters;
import com.mystra77.visualnovel.characters.Mature;
import com.mystra77.visualnovel.characters.Neko;
import com.mystra77.visualnovel.characters.Angel;
import com.mystra77.visualnovel.classes.Player;
import com.mystra77.visualnovel.database.MyOpenHelper;
import com.mystra77.visualnovel.stages.Stage;
import com.mystra77.visualnovel.stages.Stage1;
import com.mystra77.visualnovel.stages.Stage2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends AppCompatActivity {
    private MyOpenHelper moh;
    private Player player;
    private SharedPreferences preferencesSettings;
    private ConstraintLayout layoutBackground;
    private MediaPlayer mediaPlayerMusic, mediaPlayerSound, soundClick;
    private float volumenMusic, volumenSound;
    private boolean explicitImage;
    private String allText;
    private int lengthMusic;
    private ImageView leftImage, centerImage, rightImage;
    private TextView textDialogBox, textDialogLog, textCharacterName;
    private ScrollView containerText;
    private int counterLog;
    private GirlCharacters mature, neko, angel;
    private int counterLines;
    private Button btnNext, btnExit;
    private ConstraintLayout layoutTextBox;

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
        leftImage = findViewById(R.id.leftPosition);
        centerImage = findViewById(R.id.centerPosition);
        rightImage = findViewById(R.id.rightPosition);
        textDialogBox = findViewById(R.id.textBox);
        textDialogLog = findViewById(R.id.textBoxLog);
        textCharacterName = findViewById(R.id.nameCharacterText);
        btnNext = findViewById(R.id.btnNext);
        btnExit = findViewById(R.id.btnExitGame);
        containerText = findViewById(R.id.containerDialog);
        layoutTextBox = findViewById(R.id.layoutText);
        counterLog = 1;
        counterLines = 0;

        soundClick = MediaPlayer.create(this, R.raw.sound_click);
        soundClick.setVolume(0.4f, 0.4f);

        neko = new Neko();
        angel = new Angel();
        mature = new Mature();

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
            loadStage(stage1, R.raw.script1);
            rightImage.setBackground(getDrawable(mature.getImageNormaLeft()));
            leftImage.setBackground(getDrawable(neko.getImageNormalRight()));
        } else if (player.getStage() >= 2) {
            Stage2 stage2 = new Stage2();
            loadStage(stage2, R.raw.script1);
            rightImage.setBackground(getDrawable(mature.getImageNormaLeft()));
            leftImage.setBackground(getDrawable(neko.getImageNormalRight()));
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
        soundClick.start();
        saveFile(1);
    }

    public void save2(View view) {
        soundClick.start();
        saveFile(2);
    }

    public void save3(View view) {
        soundClick.start();
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

    public void openLog(View view) {
        soundClick.start();
        if (counterLog % 2 == 0) {
            containerText.setVisibility(view.INVISIBLE);
            layoutTextBox.setVisibility(View.VISIBLE);
            btnExit.setVisibility(View.VISIBLE);
            btnNext.setVisibility(View.VISIBLE);
        } else {
            containerText.setVisibility(view.VISIBLE);
            layoutTextBox.setVisibility(View.GONE);
            btnExit.setVisibility(View.GONE);
            btnNext.setVisibility(View.GONE);
        }
        counterLog++;
    }

    public void backToMainMenu(View view) {
        soundClick.start();
        new AlertDialog.Builder(this, R.style.AlertDialogCustom)
                .setMessage(R.string.exitQuestion)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mediaPlayerMusic.stop();
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
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    public void loadStage(Stage stage, int File) {
        //Image Background
        layoutBackground.setBackground(getDrawable(stage.getStageBackground()));
        //Music Background
        mediaPlayerMusic = MediaPlayer.create(this, stage.getStageMusic());
        mediaPlayerMusic.setVolume(volumenMusic, volumenMusic);
        mediaPlayerMusic.setLooping(true);
        mediaPlayerMusic.start();
        //Load all text
        InputStream stream = getResources().openRawResource(File);
        allText = convertStreamToString(stream);
    }
    //todo
    //todo
    public void clickNext(View view) {
        soundClick.start();
        String[] lines = allText.split(System.getProperty("line.separator"));
        if(counterLines < lines.length){
            if(lines[counterLines].equals("*lameruzo")){
                loadVoice(neko, 1);
                leftImage.setBackground(getDrawable(neko.getImageLaughtRight()));
                rightImage.setBackground(getDrawable(mature.getImageLaughtLeft()));
                textCharacterName.setText(neko.getName());
                counterLines++;
            }else{
                textDialogBox.setText(lines[counterLines]);
                textDialogLog.setText(textDialogLog.getText() + lines[counterLines] + "\n");
                textCharacterName.setText(mature.getName());
                counterLines++;
            }
        }
    }

    public void loadVoice(GirlCharacters girl, int emotion) {
        if(mediaPlayerSound != null){
            mediaPlayerSound.stop();
            mediaPlayerSound.release();
        }
        if (emotion == 0) {
            mediaPlayerSound = MediaPlayer.create(this, girl.getSoundNormal());
        }
        if (emotion == 1) {
            mediaPlayerSound = MediaPlayer.create(this, girl.getSoundHappy());
        }
        if (emotion == 2) {
            mediaPlayerSound = MediaPlayer.create(this, girl.getSoundAngry());
        }
        mediaPlayerSound.setVolume(volumenMusic, volumenMusic);
        mediaPlayerSound.start();
    }

    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append((line + "\n"));
            }
        } catch (IOException e) {
        } finally {
            try {
                is.close();
            } catch (IOException e) {
            }
        }
        return sb.toString();
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayerMusic.pause();
        lengthMusic = mediaPlayerMusic.getCurrentPosition();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayerMusic.seekTo(lengthMusic);
        mediaPlayerMusic.start();
        mediaPlayerMusic.setLooping(true);
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
