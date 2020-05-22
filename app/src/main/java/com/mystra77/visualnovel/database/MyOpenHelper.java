package com.mystra77.visualnovel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mystra77.visualnovel.R;
import com.mystra77.visualnovel.classes.Player;

import java.util.ArrayList;


public class MyOpenHelper extends SQLiteOpenHelper {

    /**
     * Strings variables that helps us to insert resources from the Android application
     */
    private String gameCompleted;
    private String stage;
    private String date;

    /**
     * Builder with the name of the database and its version, as well as String variables
     * that receive resources from Android app
     */
    public MyOpenHelper(Context context) {
        super(context, Constants.getDatabaseName(), null, Constants.getDatabaseVersion());
        gameCompleted = context.getResources().getString(R.string.gameCompleted);
        stage = context.getResources().getString(R.string.chapter);
        date = context.getResources().getString(R.string.date);
    }

    /**
     * The first time the application is run, it creates the database and inserts three entries
     * that generate the save points. It also adds the trigger
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.getCreateTableGame());
        db.execSQL("INSERT INTO " + Constants.getTableGame() + "(" + Constants.getKeyId() + ") VALUES ( 1 )");
        db.execSQL("INSERT INTO " + Constants.getTableGame() + "(" + Constants.getKeyId() + ") VALUES ( 2 )");
        db.execSQL("INSERT INTO " + Constants.getTableGame() + "(" + Constants.getKeyId() + ") VALUES ( 3 )");
        db.execSQL(Constants.getUpdateTimeTrigger());
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.getTableGame());
        onCreate(db);
    }

    /**
     * Function to save the game. The data is inserted in the game indicated by the id
     *
     * @param id       Integer type variable, indicates the id
     * @param stage    Integer type variable, indicates the chapter in the game
     * @param tsundere Integer type variable, indicates the point obtained by the character tsundere(angel)
     * @param neko     Integer type variable, indicates the point obtained by the character neko
     * @param witch   Integer type variable, indicates the point obtained by the character witch
     * @param score    Integer type variable, indicates the score to save to unlock images
     */
    public void saveGame(int id, int stage, int tsundere, int neko, int witch, int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.getSTAGE(), stage);
        values.put(Constants.getANGEL(), tsundere);
        values.put(Constants.getNEKO(), neko);
        values.put(Constants.getWITCH(), witch);
        values.put(Constants.getSCORE(), score);
        db.update(Constants.getTableGame(), values, Constants.getKeyId() + " = (" + id + " );", null);
    }

    /**
     * Function to load the game.
     *
     * @param id Integer type variable to indicate the position of the database entry to be retrieved
     * @return player object
     */
    public Player loadGame(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Player player;
        Cursor result = db.query(Constants.getTableGame(), null, Constants.getKeyId() + "=" + id,
                null, null, null, null);
        result.moveToFirst();
        player = new Player(result.getInt(result.getColumnIndex(Constants.getSTAGE())),
                result.getInt(result.getColumnIndex(Constants.getANGEL())),
                result.getInt(result.getColumnIndex(Constants.getNEKO())),
                result.getInt(result.getColumnIndex(Constants.getWITCH())),
                result.getInt(result.getColumnIndex(Constants.getSCORE())));
        return player;
    }

    /**
     * Function to load the last save game, this is possible using the variable timestamp
     *
     * @return player object
     */
    public Player loadLastSave() {
        SQLiteDatabase db = this.getReadableDatabase();
        Player player;
        Cursor result = db.query(Constants.getTableGame(), null, null, null,
                null, null, Constants.getTIME() + " DESC");
        result.moveToFirst();
        player = new Player(result.getInt(result.getColumnIndex(Constants.getSTAGE())),
                result.getInt(result.getColumnIndex(Constants.getANGEL())),
                result.getInt(result.getColumnIndex(Constants.getNEKO())),
                result.getInt(result.getColumnIndex(Constants.getWITCH())),
                result.getInt(result.getColumnIndex(Constants.getSCORE())));
        return player;
    }

    /**
     * Function to delete a save
     *
     * @param id Integer type variable to indicate the position of the database entry to be deleted
     */
    public void deleteSaveGame(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.getTableGame(), Constants.getKeyId() + "=" + id, null);
        db.execSQL("INSERT INTO " + Constants.getTableGame() + "(" + Constants.getKeyId() + ") VALUES (" + id + ")");
    }

    /**
     * Function that searches for the highest score stored in the database
     *
     * @return Integer value that helps us to unlock images in the gallery
     */
    public int unlockGallerySelect() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.query(Constants.getTableGame(), null, null, null,
                null, null, Constants.getSCORE() + " DESC");
        if (result.moveToFirst()) {
            return result.getInt(result.getColumnIndex(Constants.getSCORE()));
        } else {
            return 0;
        }
    }

    /**
     * Function that helps us to recover the data of the inserted games, with these data we will fill the buttons of the loading screen
     *
     * @return String with the game data to fill the text of the buttons
     */
    public ArrayList<String> fillLoadButton() {
        ArrayList<String> dateLoadString = new ArrayList<String>();
        String timeData, stageData;
        String[] parts;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.query(Constants.getTableGame(), null, null, null,
                null, null, Constants.getKeyId());
        if (result.moveToFirst()) {
            do {
                stageData = stage + result.getString(result.getColumnIndex(Constants.getSTAGE()));
                timeData = result.getString(result.getColumnIndex(Constants.getTIME()));
                if (stageData.equals(stage + "5")) {
                    dateLoadString.add(gameCompleted);
                } else {
                    //little trick that will help us when we call the function in the ContinueFragment
                    if (!timeData.equals("0")) {
                        parts = timeData.split(" ");
                        String part1 = parts[0]; // 004
                        String part2 = parts[1]; //
                        dateLoadString.add(stageData + "\n\n" + date + part1 + "\n'" + part2 + "'");
                    } else {
                        dateLoadString.add(".");
                    }
                }
            } while (result.moveToNext());
            return dateLoadString;
        } else {
            return dateLoadString;
        }
    }
}



