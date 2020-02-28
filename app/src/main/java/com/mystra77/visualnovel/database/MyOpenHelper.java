package com.mystra77.visualnovel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mystra77.visualnovel.classes.Player;

import java.util.ArrayList;


public class MyOpenHelper extends SQLiteOpenHelper {
    public MyOpenHelper(Context context) {
        super(context, Constants.getDatabaseName(), null, Constants.getDatabaseVersion());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.getCreateTableGame());
        db.execSQL("INSERT INTO " + Constants.getTableGame() + "(" + Constants.getKeyId() + ") VALUES ( 1 )");
        db.execSQL("INSERT INTO " + Constants.getTableGame() + "(" + Constants.getKeyId() + ") VALUES ( 2 )");
        db.execSQL("INSERT INTO " + Constants.getTableGame() + "(" + Constants.getKeyId() + ") VALUES ( 3 )");
        db.execSQL(Constants.getUpdateTimeTrigger());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.getTableGame());
        onCreate(db);
    }

    public void saveGame(int id, int stage, int tsundere, int neko, int mature, int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.getSTAGE(), stage);
        values.put(Constants.getTSUNDERE(), tsundere);
        values.put(Constants.getNEKO(), neko);
        values.put(Constants.getMATURE(), mature);
        values.put(Constants.getSCORE(), score);
        db.update(Constants.getTableGame(), values, Constants.getKeyId() + " = (" + id + " );", null);
    }

    public Player loadGame(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Player player;
        Cursor result = db.query(Constants.getTableGame(), null, Constants.getKeyId() + "=" + id, null, null, null, null);
        result.moveToFirst();
        player = new Player(result.getInt(result.getColumnIndex(Constants.getSTAGE())),
                result.getInt(result.getColumnIndex(Constants.getTSUNDERE())),
                result.getInt(result.getColumnIndex(Constants.getNEKO())),
                result.getInt(result.getColumnIndex(Constants.getMATURE())),
                result.getInt(result.getColumnIndex(Constants.getSCORE())));
        return player;
    }

    public Player loadLastSave() {
        SQLiteDatabase db = this.getReadableDatabase();
        Player player;
        Cursor result = db.query(Constants.getTableGame(), null, null, null, null, null, Constants.getTIME() + " DESC");
        result.moveToFirst();
        player = new Player(result.getInt(result.getColumnIndex(Constants.getSTAGE())),
                result.getInt(result.getColumnIndex(Constants.getTSUNDERE())),
                result.getInt(result.getColumnIndex(Constants.getNEKO())),
                result.getInt(result.getColumnIndex(Constants.getMATURE())),
                result.getInt(result.getColumnIndex(Constants.getSCORE())));
        return player;
    }

    public void deleteSaveGame(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.getTableGame(), Constants.getKeyId() + "=" + id, null);
        db.execSQL("INSERT INTO " + Constants.getTableGame() + "(" + Constants.getKeyId() + ") VALUES (" + id + ")");
    }

    public int unlockGallerySelect() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.query(Constants.getTableGame(), null, null, null, null, null, Constants.getSCORE() + " DESC");
        if (result.moveToFirst()) {
            return result.getInt(result.getColumnIndex(Constants.getSCORE()));
        } else {
            return 0;
        }
    }

    public ArrayList<String> fillLoadButton() {
        ArrayList<String> dateLoadString = new ArrayList<String>();
        String timeData, stageData, scoreData;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.query(Constants.getTableGame(), null, null, null, null, null, Constants.getKeyId());
        if (result.moveToFirst()) {
            do {
                stageData = "STAGE: " + result.getString(result.getColumnIndex(Constants.getSTAGE()));
                scoreData = "SCORE: " + result.getString(result.getColumnIndex(Constants.getSCORE()));
                timeData = result.getString(result.getColumnIndex(Constants.getTIME()));
                if (!timeData.equals("0")) {
                    dateLoadString.add(stageData + "\n\n" + scoreData + "\n\n" + timeData);
                } else {
                    dateLoadString.add(".");
                }
            } while (result.moveToNext());
            return dateLoadString;
        } else {
            return dateLoadString;
        }
    }
}



