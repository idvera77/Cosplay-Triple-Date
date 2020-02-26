package com.mystra77.visualnovel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.mystra77.visualnovel.classes.Player;
import com.mystra77.visualnovel.database.MyOpenHelper;

public class Game extends AppCompatActivity {
    private MyOpenHelper moh;
    private Player player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Delete Status Bar and insert Animation
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.activity_game);

        //Open database
        moh = new MyOpenHelper(this);
        moh.getWritableDatabase();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            player = (Player) bundle.getSerializable("player");
        }

        /*
         if(player.getScore() < 800){
            startService(new Intent(this, ServiceGallery.class));
        }
        */
        player.setScore(player.getScore() + 200);
        System.out.println(player);
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
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        startActivity(new Intent(this, HomeActivity.class));
    }
}
