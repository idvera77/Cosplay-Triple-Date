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
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mystra77.visualnovel.characters.GirlCharacters;
import com.mystra77.visualnovel.characters.Mature;
import com.mystra77.visualnovel.characters.Neko;
import com.mystra77.visualnovel.characters.Angel;
import com.mystra77.visualnovel.classes.KeyWords;
import com.mystra77.visualnovel.classes.Player;
import com.mystra77.visualnovel.database.MyOpenHelper;
import com.mystra77.visualnovel.stages.Stage;
import com.mystra77.visualnovel.stages.Stage1;
import com.mystra77.visualnovel.stages.Stage2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Game extends AppCompatActivity {
    private MyOpenHelper moh;
    private Player player;
    private SharedPreferences preferencesSettings;
    private ConstraintLayout layoutBackground, layoutTextBox, layoutEndOfStage;
    private LinearLayout layoutScenario, layoutButtons;
    private MediaPlayer mediaPlayerMusic, mediaPlayerSound, soundClick;
    private Button buttonLog, buttonOption1, buttonOption2, buttonOption3;
    private float volumenMusic, volumenSound;
    private boolean explicitImage;
    private String allText, characterSelect;
    private String[] lines;
    private int lengthMusic, counterLog, counterLines;
    private ImageView leftImage, centerImage, rightImage;
    private TextView textDialogBox, textDialogLog, textCharacterName, finalMessage;
    private ScrollView containerText;
    private GirlCharacters mature, neko, angel;
    private KeyWords keyWords;
    private Button btnNext, btnExit;
    private Handler handler;

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
        volumenSound = preferencesSettings.getFloat("volumenSound", 1.0f);
        explicitImage = preferencesSettings.getBoolean("explicitImage", true);

        layoutEndOfStage = findViewById(R.id.containerEndStage);
        layoutBackground = findViewById(R.id.stageID);
        layoutScenario = findViewById(R.id.scenario);
        layoutButtons = findViewById(R.id.containerButtons);
        buttonLog = findViewById(R.id.btnLog);
        buttonOption1 = findViewById(R.id.btnOption1);
        buttonOption2 = findViewById(R.id.btnOption2);
        buttonOption3 = findViewById(R.id.btnOption3);
        leftImage = findViewById(R.id.leftPosition);
        centerImage = findViewById(R.id.centerPosition);
        rightImage = findViewById(R.id.rightPosition);
        textDialogBox = findViewById(R.id.textBox);
        textDialogLog = findViewById(R.id.textBoxLog);
        textCharacterName = findViewById(R.id.nameCharacterText);
        finalMessage = findViewById(R.id.finalMessage);
        btnNext = findViewById(R.id.btnNext);
        btnExit = findViewById(R.id.btnExitGame);
        containerText = findViewById(R.id.containerDialog);
        layoutTextBox = findViewById(R.id.layoutText);
        counterLog = 1;
        counterLines = 0;

        soundClick = MediaPlayer.create(this, R.raw.sound_click);
        soundClick.setVolume(volumenSound, volumenSound);

        keyWords = new KeyWords();
        handler = new Handler();
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
        if (player.getStage() == 1) {
            Stage1 stage1 = new Stage1();
            loadStage(stage1, R.raw.script1);
        }
        if (player.getStage() >= 2) {
            Stage2 stage2 = new Stage2();
            loadStage(stage2, R.raw.script2);
        }

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
                        moh.saveGame(saveFileId, player.getStage(), player.getAngel(), player.getNeko(), player.getMature(), player.getScore());
                    }
                })
                .setNegativeButton(R.string.no, null)
                .show();
    }

    public void openLog(View view) {
        soundClick.start();
        if (counterLog % 2 == 0) {
            containerText.setVisibility(view.GONE);
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

    public void clickNext(View view) {
        lines = allText.split(System.getProperty("line.separator"));
        if (counterLines < lines.length) {
            if (lines[counterLines].equals(keyWords.getKeyNeko())) {
                characterSelect = keyWords.getKeyNeko();
                textCharacterName.setText(neko.getName());
                textDialogLog.setText(textDialogLog.getText()  + "\n" + neko.getName() + "\n");
                counterLines++;
                clickNext(view);
            } else if (lines[counterLines].equals(keyWords.getKeyAngel())) {
                characterSelect = keyWords.getKeyAngel();
                textCharacterName.setText(angel.getName());
                textDialogLog.setText(textDialogLog.getText() + "\n" + angel.getName() + "\n");
                counterLines++;
                clickNext(view);
            } else if (lines[counterLines].equals(keyWords.getKeyMature())) {
                characterSelect = keyWords.getKeyMature();
                textCharacterName.setText(mature.getName());
                textDialogLog.setText(textDialogLog.getText() + "\n" + mature.getName() + "\n");
                counterLines++;
                clickNext(view);
            }else if (lines[counterLines].equals(keyWords.getKeyNormalLeftPosition())) {
                drawLeftGirl(girlSelection(characterSelect), 0);
                counterLines++;
                clickNext(view);
            }else if (lines[counterLines].equals(keyWords.getKeyNormalCenterPosition())) {
                drawCenterGirl(girlSelection(characterSelect), 0);
                counterLines++;
                clickNext(view);
            }else if (lines[counterLines].equals(keyWords.getKeyNormalRightPosition())) {
                drawRightGirl(girlSelection(characterSelect), 0);
                counterLines++;
                clickNext(view);
            }else if (lines[counterLines].equals(keyWords.getKeyHappyLeftPosition())) {
                drawLeftGirl(girlSelection(characterSelect), 1);
                counterLines++;
                clickNext(view);
            }else if (lines[counterLines].equals(keyWords.getKeyHappyCenterPosition())) {
                drawCenterGirl(girlSelection(characterSelect), 1);
                counterLines++;
                clickNext(view);
            }else if (lines[counterLines].equals(keyWords.getKeyHappyRightPosition())) {
                drawRightGirl(girlSelection(characterSelect), 1);
                counterLines++;
                clickNext(view);
            }else if (lines[counterLines].equals(keyWords.getKeyAngryLeftPosition())) {
                drawLeftGirl(girlSelection(characterSelect), 2);
                counterLines++;
                clickNext(view);
            }else if (lines[counterLines].equals(keyWords.getKeyAngryCenterPosition())) {
                drawCenterGirl(girlSelection(characterSelect), 2);
                counterLines++;
                clickNext(view);
            }else if (lines[counterLines].equals(keyWords.getKeyAngryRightPosition())) {
                drawRightGirl(girlSelection(characterSelect), 2);
                counterLines++;
                clickNext(view);
            }else if (lines[counterLines].equals(keyWords.getKeyButtons())){
                counterLines++;
                buttonOption1.setText(lines[counterLines]);
                counterLines++;
                buttonOption2.setText(lines[counterLines]);
                counterLines++;
                buttonOption3.setText(lines[counterLines]);
                layoutButtons.setVisibility(View.VISIBLE);
                btnNext.setVisibility(View.GONE);
            }else{
                textDialogBox.setText(lines[counterLines]);
                textDialogLog.setText(textDialogLog.getText() + lines[counterLines] + "\n");
                counterLines++;
            }
        }else{
            if(player.getStage()==4){
                player.setScore(player.getScore() + 250);
                player.setStage(player.getStage() + 1);
                sexScene();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        finalMessage.setText(R.string.thanksMessage);
                        layoutEndOfStage.setVisibility(View.VISIBLE);
                    }
                }, 20000);
            }else{
                player.setScore(player.getScore() + 250);
                player.setStage(player.getStage() + 1);
                endOfStage();
            }
        }
        disableButtonNext();
    }

    public void clickOption1(View view) {
        counterLines+=1;
        textDialogBox.setText(lines[counterLines]);
        textDialogLog.setText(textDialogLog.getText() + lines[counterLines] + "\n");
        counterLines+=3;
        layoutButtons.setVisibility(View.GONE);
        btnNext.setVisibility(View.VISIBLE);
        afinityGirl(10);
    }

    public void clickOption2(View view) {
        counterLines+=2;
        textDialogBox.setText(lines[counterLines]);
        textDialogLog.setText(textDialogLog.getText() + lines[counterLines] + "\n");
        counterLines+=2;
        layoutButtons.setVisibility(View.GONE);
        btnNext.setVisibility(View.VISIBLE);
        afinityGirl(0);
    }

    public void clickOption3(View view) {
        counterLines+=3;
        textDialogBox.setText(lines[counterLines]);
        textDialogLog.setText(textDialogLog.getText() + lines[counterLines] + "\n");
        counterLines++;
        layoutButtons.setVisibility(View.GONE);
        btnNext.setVisibility(View.VISIBLE);
        afinityGirl(-10);
    }

    public void afinityGirl(int points){
        if(characterSelect.equals(keyWords.getKeyNeko())){
            player.setNeko(player.getNeko() + points + 5);
            System.out.println(player.getNeko());
        }
        if(characterSelect.equals(keyWords.getKeyAngel())){
            player.setAngel(player.getAngel() + points);
            System.out.println(player.getAngel());
        }
        if(characterSelect.equals(keyWords.getKeyMature())){
            player.setMature(player.getMature() + points);
            System.out.println(player.getMature());
        }
    }

    public void disableButtonNext(){
        //TODO GONE
        soundClick.start();
        btnNext.setEnabled(false);
        handler.postDelayed(new Runnable() {
            public void run() {
                btnNext.setEnabled(true);
            }
        }, 1000);
    }

    public void endOfStage(){
        layoutEndOfStage.setVisibility(View.VISIBLE);
        layoutScenario.setVisibility(View.GONE);
        layoutTextBox.setVisibility(View.GONE);
        buttonLog.setVisibility(View.GONE);
    }

    public void sexScene(){
        layoutScenario.setVisibility(View.GONE);
        layoutTextBox.setVisibility(View.GONE);
        buttonLog.setVisibility(View.GONE);

        //TODO INSERTAR MUSICA
        if(characterSelect.equals(keyWords.getKeyNeko())){
            if(explicitImage){
                layoutBackground.setBackground(getDrawable(neko.getSceneSexUncensored()));
            }else{
                layoutBackground.setBackground(getDrawable(neko.getSceneCensored()));
            }
        }
        if(characterSelect.equals(keyWords.getKeyAngel())){
            if(explicitImage){
                layoutBackground.setBackground(getDrawable(angel.getSceneSexUncensored()));
            }else{
                layoutBackground.setBackground(getDrawable(angel.getSceneCensored()));
            }
        }
        if(characterSelect.equals(keyWords.getKeyMature())){
            if(explicitImage){
                layoutBackground.setBackground(getDrawable(mature.getSceneSexUncensored()));
            }else{
                layoutBackground.setBackground(getDrawable(mature.getSceneCensored()));
            }
        }
    }

    public GirlCharacters girlSelection(String character){
        if(characterSelect.equals(keyWords.getKeyNeko())){
            return neko;
        }else if(characterSelect.equals(keyWords.getKeyAngel())){
            return angel;
        }else{
            return mature;
        }
    }

    public void drawLeftGirl(GirlCharacters girl, int emotion){
        if(mediaPlayerSound != null){
            mediaPlayerSound.stop();
            mediaPlayerSound.release();
        }
        if (emotion == 0) {
            leftImage.setBackground(getDrawable(girl.getImageNormalRight()));
            mediaPlayerSound = MediaPlayer.create(this, girl.getSoundNormal());
        }
        if (emotion == 1) {
            leftImage.setBackground(getDrawable(girl.getImageLaughtRight()));
            mediaPlayerSound = MediaPlayer.create(this, girl.getSoundHappy());
        }
        if (emotion == 2) {
            leftImage.setBackground(getDrawable(girl.getImageAngryRight()));
            mediaPlayerSound = MediaPlayer.create(this, girl.getSoundAngry());
        }
        mediaPlayerSound.setVolume(volumenSound, volumenSound);
        mediaPlayerSound.start();
    }

    public void drawCenterGirl(GirlCharacters girl, int emotion){
        if(mediaPlayerSound != null){
            mediaPlayerSound.stop();
            mediaPlayerSound.release();
        }
        if (emotion == 0) {
            centerImage.setBackground(getDrawable(girl.getImageNormaLeft()));
            mediaPlayerSound = MediaPlayer.create(this, girl.getSoundNormal());
        }
        if (emotion == 1) {
            centerImage.setBackground(getDrawable(girl.getImageLaughtLeft()));
            mediaPlayerSound = MediaPlayer.create(this, girl.getSoundHappy());
        }
        if (emotion == 2) {
            centerImage.setBackground(getDrawable(girl.getImageAngryLeft()));
            mediaPlayerSound = MediaPlayer.create(this, girl.getSoundAngry());
        }
        mediaPlayerSound.setVolume(volumenSound, volumenSound);
        mediaPlayerSound.start();
    }

    public void drawRightGirl(GirlCharacters girl, int emotion){
        if(mediaPlayerSound != null){
            mediaPlayerSound.stop();
            mediaPlayerSound.release();
        }
        if (emotion == 0) {
            rightImage.setBackground(getDrawable(girl.getImageNormaLeft()));
            mediaPlayerSound = MediaPlayer.create(this, girl.getSoundNormal());
        }
        if (emotion == 1) {
            rightImage.setBackground(getDrawable(girl.getImageLaughtLeft()));
            mediaPlayerSound = MediaPlayer.create(this, girl.getSoundHappy());
        }
        if (emotion == 2) {
            rightImage.setBackground(getDrawable(girl.getImageAngryLeft()));
            mediaPlayerSound = MediaPlayer.create(this, girl.getSoundAngry());
        }
        mediaPlayerSound.setVolume(volumenSound, volumenSound);
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
