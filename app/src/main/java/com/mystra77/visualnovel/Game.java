package com.mystra77.visualnovel;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
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

        System.out.println(player);
        player.setScore(player.getScore() + 1000);
        System.out.println(player);

    }

    public void save1(View view) {
        moh.saveGame(1, player.getStage(), player.getTsundere(), player.getNeko(), player.getMature(), player.getScore());
    }

    public void save2(View view) {
        moh.saveGame(2, player.getStage(), player.getTsundere(), player.getNeko(), player.getMature(), player.getScore());
    }

    public void save3(View view) {
        moh.saveGame(3, player.getStage(), player.getTsundere(), player.getNeko(), player.getMature(), player.getScore());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
