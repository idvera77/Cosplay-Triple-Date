package com.mystra77.visualnovel.database;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



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

    public void saveGame(SQLiteDatabase db, int id, int level, int tsundere, int neko, int mature, int score) {
        ContentValues values = new ContentValues();
        values.put(Constants.getLEVEL(), level);
        values.put(Constants.getTSUNDERE(), tsundere);
        values.put(Constants.getNEKO(), neko);
        values.put(Constants.getMATURE(), mature);
        values.put(Constants.getSCORE(), score);
        db.update(Constants.getTableGame(), values, Constants.getKeyId() + " = (" + id + " );", null);
    }

    public void loadGame(SQLiteDatabase db, int id){

    }

                /*
    private static final String CREATE_TABLE_GAME = "CREATE TABLE " + TABLE_GAME + "("
            + KEY_ID + " INTEGER PRIMARY KEY NOT NULL, " + TIME + " TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
            + LEVEL + " INTEGER," + TSUNDERE + " INTEGER," + NEKO + " INTEGER," + MATURE + " INTEGER," + SCORE + " INTEGER);";



            SELECT datetime('now', 'localtime');
            contentValues.put( COLUMN_TIME_STAMP, " time('now') " );
     */

}

    /*
         public void empezarPartida() {
        MiOpenHelper moh = new MiOpenHelper(contexto);
        SQLiteDatabase db = moh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(JuegoDataBase.getScoreFieldName(), 0);
        db.insert(JuegoDataBase.getScoreTablename(), null, values);
    }

    @Override
    public void terminarPartida(int puntos) {
        MiOpenHelper moh = new MiOpenHelper(contexto);
        SQLiteDatabase db = moh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(JuegoDataBase.getScoreFieldName(), puntos);
        db.update(JuegoDataBase.getScoreTablename(), values,  JuegoDataBase.getStartdateFieldName() + " = (SELECT MAX("+ JuegoDataBase.getStartdateFieldName()+") FROM " + JuegoDataBase.getScoreTablename() + ");", null);
    }


    public ArrayList<String> mejorPartida() {
        ArrayList<String> puntuaciones = new ArrayList<String>();
        MiOpenHelper moh = new MiOpenHelper(contexto);
        SQLiteDatabase db = moh.getWritableDatabase();
        Cursor resultadoConsulta = db.rawQuery("select * from " + JuegoDataBase.getScoreTablename()
                + " Order by " + JuegoDataBase.getScoreFieldName() + " desc limit 3;", null);
        if(resultadoConsulta.getCount() > 0){
            resultadoConsulta.moveToFirst();
            do {
                puntuaciones.add(resultadoConsulta.getString(resultadoConsulta.getColumnIndex(JuegoDataBase.getScoreFieldName())));
            } while (resultadoConsulta.moveToNext());
        }
        return puntuaciones;
    }
     */
