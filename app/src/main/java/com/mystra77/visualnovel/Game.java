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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import java.util.ArrayList;

public class Game extends AppCompatActivity {
    private MyOpenHelper moh;
    private Player player;
    private ConstraintLayout layoutBackground, layoutTextBox, layoutEndOfStage, containerText;
    private LinearLayout layoutScenario, layoutButtons;
    private MediaPlayer mediaPlayerMusic, mediaPlayerSound, soundClick;
    private Button buttonLog, buttonOption1, buttonOption2, buttonOption3;
    private float volumenMusic, volumenSound;
    private boolean explicitImage, counterLog;
    private String allText, characterSelect, characterNameAux;
    private String[] lines;
    private int lengthMusic, counterLines;
    private ImageView leftImage, centerImage, rightImage;
    private TextView textDialogBox, textCharacterName, finalMessage;
    private ListView textDialogLog;
    private ArrayAdapter<String> adapterLog;
    private ArrayList<String> logsLines;
    private GirlCharacters mature, neko, angel;
    private KeyWords keyWords;
    private Button btnExit;
    private Handler handler;
    private InputStream stream;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Delete Status Bar and insert Animation
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        setContentView(R.layout.activity_game);

        //Load preferences
        SharedPreferences preferencesSettings = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
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
        btnExit = findViewById(R.id.btnExitGame);
        containerText = findViewById(R.id.containerDialog);
        layoutTextBox = findViewById(R.id.layoutText);
        counterLog = false;
        counterLines = 0;

        soundClick = MediaPlayer.create(this, R.raw.sound_click);
        soundClick.setVolume(volumenSound, volumenSound);

        logsLines = new ArrayList<String>();
        adapterLog = new ArrayAdapter<String>(this, R.layout.log_adapter, logsLines);
        textDialogLog.setAdapter(adapterLog);

        //Load Animation
        animation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

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
            loadStage(stage1, 1);
        }
        if (player.getStage() >= 2) {
            Stage2 stage2 = new Stage2();
            if (player.getNeko() > player.getMature()) {
                if (player.getMature() > player.getAngel()) {
                    loadStage(stage2, 1);
                } else {
                    loadStage(stage2, 2);
                }
            }
            if (player.getNeko() < player.getMature()) {
                if (player.getNeko() > player.getAngel()) {
                    loadStage(stage2, 1);
                } else {
                    loadStage(stage2, 3);
                }
            }
        }

        textDialogBox.setText(R.string.tap);

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
        if (counterLog) {
            containerText.setVisibility(view.GONE);
            layoutTextBox.setVisibility(View.VISIBLE);
            btnExit.setVisibility(View.VISIBLE);
            counterLog = false;
        } else {
            textDialogLog.setSelection(textDialogLog.getAdapter().getCount() - 1);
            containerText.setVisibility(view.VISIBLE);
            layoutTextBox.setVisibility(View.GONE);
            btnExit.setVisibility(View.GONE);
            counterLog = true;
        }
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
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    public void loadStage(Stage stage, int scriptOption) {
        //Image Background
        layoutBackground.setBackground(getDrawable(stage.getStageBackground()));
        //Music Background
        mediaPlayerMusic = MediaPlayer.create(this, stage.getStageMusic());
        mediaPlayerMusic.setVolume(volumenMusic, volumenMusic);
        mediaPlayerMusic.setLooping(true);
        //Load all text
        if (scriptOption == 1) {
            stream = getResources().openRawResource(stage.getScriptPlot1());
        }
        if (scriptOption == 2) {
            stream = getResources().openRawResource(stage.getScriptPlot2());
        }
        if (scriptOption == 3) {
            stream = getResources().openRawResource(stage.getScriptPlot3());
        }
        allText = convertStreamToString(stream);
        mediaPlayerMusic.start();
    }

    public void clickNext(View view) {
        soundClick.start();
        lines = allText.split(System.getProperty("line.separator"));
        counterLines++;
        if (counterLines < lines.length) {
            if (lines[counterLines].equals(keyWords.getKeyNeko())) {
                characterSelect = keyWords.getKeyNeko();
                changeCharacterName(neko);
                textCharacterName.setTextColor(getColor(R.color.neko));
            } else if (lines[counterLines].equals(keyWords.getKeyAngel())) {
                characterSelect = keyWords.getKeyAngel();
                changeCharacterName(angel);
                textCharacterName.setTextColor(getColor(R.color.angel));
            } else if (lines[counterLines].equals(keyWords.getKeyMature())) {
                characterSelect = keyWords.getKeyMature();
                changeCharacterName(mature);
                textCharacterName.setTextColor(getColor(R.color.mature));
            } else if (lines[counterLines].equals(keyWords.getKeyNormalLeftPosition())) {
                drawGirl(girlSelection(), 0, 0, false);
            } else if (lines[counterLines].equals(keyWords.getKeyNormalCenterPosition())) {
                drawGirl(girlSelection(), 1, 0, false);
            } else if (lines[counterLines].equals(keyWords.getKeyNormalRightPosition())) {
                drawGirl(girlSelection(), 2, 0, false);
            } else if (lines[counterLines].equals(keyWords.getKeyHappyLeftPosition())) {
                drawGirl(girlSelection(), 0, 1, false);
            } else if (lines[counterLines].equals(keyWords.getKeyHappyCenterPosition())) {
                drawGirl(girlSelection(), 1, 1, false);
            } else if (lines[counterLines].equals(keyWords.getKeyHappyRightPosition())) {
                drawGirl(girlSelection(), 2, 1, false);
            } else if (lines[counterLines].equals(keyWords.getKeyAngryLeftPosition())) {
                drawGirl(girlSelection(), 0, 2, false);
            } else if (lines[counterLines].equals(keyWords.getKeyAngryCenterPosition())) {
                drawGirl(girlSelection(), 1, 2, false);
            } else if (lines[counterLines].equals(keyWords.getKeyAngryRightPosition())) {
                drawGirl(girlSelection(), 2, 2, false);
            } else if (lines[counterLines].equals(keyWords.getKeyButtons())) {
                counterLines++;
                buttonOption1.setText(lines[counterLines]);
                counterLines++;
                buttonOption2.setText(lines[counterLines]);
                counterLines++;
                buttonOption3.setText(lines[counterLines]);
                enableDisableAnswerButtons(true);
            } else {
                if (counterLines == lines.length - 1) {
                    textCharacterName.setVisibility(View.INVISIBLE);
                    characterNameAux = getString(R.string.system);
                }
                updateText();
            }
        } else {
            if (player.getStage() == 4) {
                player.setScore(player.getScore() + 250);
                player.setStage(player.getStage() + 1);
                sexScene();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        finalMessage.setText(R.string.thanksMessage);
                        layoutEndOfStage.setVisibility(View.VISIBLE);
                    }
                }, 20000);
            } else {
                player.setScore(player.getScore() + 250);
                player.setStage(player.getStage() + 1);
                endOfStage();
            }
        }
    }

    public void updateText() {
        textDialogBox.setText(lines[counterLines] + getString(R.string.nextDraw));
        if (characterNameAux == null) {
            logsLines.add(getString(R.string.you) + lines[counterLines]);
        } else if (characterNameAux.contains(getString(R.string.system))) {
            //Nothing for now
        } else {
            logsLines.add(characterNameAux + getString(R.string.nameGirlLog) + lines[counterLines]);
        }
        adapterLog.notifyDataSetChanged();
    }

    public void changeCharacterName(GirlCharacters girl) {
        textCharacterName.setVisibility(View.VISIBLE);
        textCharacterName.setText(girl.getName());
        characterNameAux = girl.getName();
        adapterLog.notifyDataSetChanged();
        clickNext(this.textDialogBox);
    }

    public void enableDisableAnswerButtons(boolean enable) {
        if (enable) {
            layoutButtons.setVisibility(View.VISIBLE);
            layoutTextBox.setClickable(false);
            layoutTextBox.setEnabled(false);
        } else {
            layoutButtons.setVisibility(View.GONE);
            layoutTextBox.setClickable(true);
            layoutTextBox.setEnabled(true);
        }
    }

    public void clickOption1(View view) {
        soundClick.start();
        logsLines.add(getString(R.string.you) + "\"" + buttonOption1.getText().toString() + "\"");
        counterLines++;
        answerEmotion();
        counterLines++;
        updateText();
        counterLines += 4;
        afinityGirl(10);
        enableDisableAnswerButtons(false);
    }

    public void clickOption2(View view) {
        soundClick.start();
        logsLines.add(getString(R.string.you) + "\"" + buttonOption2.getText().toString() + "\"");
        adapterLog.notifyDataSetChanged();
        counterLines += 3;
        answerEmotion();
        counterLines++;
        updateText();
        counterLines += 2;
        afinityGirl(0);
        enableDisableAnswerButtons(false);
    }

    public void clickOption3(View view) {
        soundClick.start();
        logsLines.add(getString(R.string.you) + "\"" + buttonOption3.getText().toString() + "\"");
        counterLines += 5;
        answerEmotion();
        counterLines++;
        updateText();
        afinityGirl(-10);
        enableDisableAnswerButtons(false);
    }

    public void afinityGirl(int points) {
        if (characterSelect.equals(keyWords.getKeyNeko())) {
            player.setNeko(player.getNeko() + points);
        }
        if (characterSelect.equals(keyWords.getKeyAngel())) {
            player.setAngel(player.getAngel() + points - 3);
        }
        if (characterSelect.equals(keyWords.getKeyMature())) {
            player.setMature(player.getMature() + points + 5);
        }
    }

    public void endOfStage() {
        layoutEndOfStage.setVisibility(View.VISIBLE);
        layoutScenario.setVisibility(View.GONE);
        layoutTextBox.setVisibility(View.GONE);
        buttonLog.setVisibility(View.GONE);
    }

    public void sexScene() {
        layoutScenario.setVisibility(View.GONE);
        layoutTextBox.setVisibility(View.GONE);
        buttonLog.setVisibility(View.GONE);
        if (characterSelect.equals(keyWords.getKeyNeko())) {
            if (explicitImage) {
                layoutBackground.setBackground(getDrawable(neko.getSceneSexUncensored()));
            } else {
                layoutBackground.setBackground(getDrawable(neko.getSceneCensored()));
            }
        }
        if (characterSelect.equals(keyWords.getKeyAngel())) {
            if (explicitImage) {
                layoutBackground.setBackground(getDrawable(angel.getSceneSexUncensored()));
            } else {
                layoutBackground.setBackground(getDrawable(angel.getSceneCensored()));
            }
        }
        if (characterSelect.equals(keyWords.getKeyMature())) {
            if (explicitImage) {
                layoutBackground.setBackground(getDrawable(mature.getSceneSexUncensored()));
            } else {
                layoutBackground.setBackground(getDrawable(mature.getSceneCensored()));
            }
        }
    }

    public GirlCharacters girlSelection() {
        if (characterSelect.equals(keyWords.getKeyNeko())) {
            return neko;
        } else if (characterSelect.equals(keyWords.getKeyAngel())) {
            return angel;
        } else {
            return mature;
        }
    }

    public void drawGirl(GirlCharacters girl, int position, int emotion, boolean answer) {
        stopSound();
        if (emotion == 0) {
            if (position == 0) {
                rightImage.setAnimation(null);
                leftImage.setAnimation(animation);
                leftImage.setBackground(getDrawable(girl.getImageNormalRight()));
            } else if (position == 1) {
                centerImage.setAnimation(animation);
                centerImage.setBackground(getDrawable(girl.getImageNormaLeft()));
            } else if (position == 2) {
                leftImage.setAnimation(null);
                rightImage.setAnimation(animation);
                rightImage.setBackground(getDrawable(girl.getImageNormaLeft()));
            }
            mediaPlayerSound = MediaPlayer.create(this, girl.getSoundNormal());
        }
        if (emotion == 1) {
            if (position == 0) {
                rightImage.setAnimation(null);
                leftImage.setAnimation(animation);
                leftImage.setBackground(getDrawable(girl.getImageLaughtRight()));
            } else if (position == 1) {
                centerImage.setAnimation(animation);
                centerImage.setBackground(getDrawable(girl.getImageLaughtLeft()));
            } else if (position == 2) {
                leftImage.setAnimation(null);
                rightImage.setAnimation(animation);
                rightImage.setBackground(getDrawable(girl.getImageLaughtLeft()));
            }
            mediaPlayerSound = MediaPlayer.create(this, girl.getSoundHappy());
        }
        if (emotion == 2) {
            if (position == 0) {
                rightImage.setAnimation(null);
                leftImage.setAnimation(animation);
                leftImage.setBackground(getDrawable(girl.getImageAngryRight()));
            } else if (position == 1) {
                centerImage.setAnimation(animation);
                centerImage.setBackground(getDrawable(girl.getImageAngryLeft()));
            } else if (position == 2) {
                leftImage.setAnimation(null);
                rightImage.setAnimation(animation);
                rightImage.setBackground(getDrawable(girl.getImageAngryLeft()));
            }
            mediaPlayerSound = MediaPlayer.create(this, girl.getSoundAngry());
        }
        animation.start();
        mediaPlayerSound.setVolume(volumenSound, volumenSound);
        mediaPlayerSound.start();
        if (!answer) {
            clickNext(this.layoutTextBox);
        }
    }

    public void answerEmotion() {
        if (lines[counterLines].equals(keyWords.getKeyNormalLeftPosition())) {
            drawGirl(girlSelection(), 0, 0, true);
        } else if (lines[counterLines].equals(keyWords.getKeyNormalCenterPosition())) {
            drawGirl(girlSelection(), 1, 0, true);
        } else if (lines[counterLines].equals(keyWords.getKeyNormalRightPosition())) {
            drawGirl(girlSelection(), 2, 0, true);
        } else if (lines[counterLines].equals(keyWords.getKeyHappyLeftPosition())) {
            drawGirl(girlSelection(), 0, 1, true);
        } else if (lines[counterLines].equals(keyWords.getKeyHappyCenterPosition())) {
            drawGirl(girlSelection(), 1, 1, true);
        } else if (lines[counterLines].equals(keyWords.getKeyHappyRightPosition())) {
            drawGirl(girlSelection(), 2, 1, true);
        } else if (lines[counterLines].equals(keyWords.getKeyAngryLeftPosition())) {
            drawGirl(girlSelection(), 0, 2, true);
        } else if (lines[counterLines].equals(keyWords.getKeyAngryCenterPosition())) {
            drawGirl(girlSelection(), 1, 2, true);
        } else if (lines[counterLines].equals(keyWords.getKeyAngryRightPosition())) {
            drawGirl(girlSelection(), 2, 2, true);
        }
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

    public void stopSound() {
        if (mediaPlayerSound != null) {
            mediaPlayerSound.stop();
            mediaPlayerSound.release();
        }
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
        stopSound();
        if (soundClick != null) {
            soundClick.release();
        }
    }
}
