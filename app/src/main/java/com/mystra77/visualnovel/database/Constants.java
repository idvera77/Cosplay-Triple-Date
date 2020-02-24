package com.mystra77.visualnovel.database;

public class Constants {
    private static final String DATABASE_NAME = "Mystra77VisualNovel";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_GAME = "game";

    private static final String KEY_ID = "id";
    private static final String TIME = "time";
    private static final String LEVEL = "level";
    private static final String TSUNDERE = "tsundere";
    private static final String NEKO = "neko";
    private static final String MATURE = "mature";
    private static final String SCORE = "score";

    private static final String CREATE_TABLE_GAME = "CREATE TABLE " + TABLE_GAME + "("
            + KEY_ID + " INTEGER PRIMARY KEY NOT NULL, " + TIME + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
            + LEVEL + " INTEGER," + TSUNDERE + " INTEGER," + NEKO + " INTEGER," + MATURE + " INTEGER," + SCORE + " INTEGER);";

    private static final String UPDATE_TIME_TRIGGER = "CREATE TRIGGER update_time_trigger " +
                    "AFTER UPDATE ON " + TABLE_GAME + " BEGIN " +
                    "UPDATE " + TABLE_GAME + " SET " + TIME + " = current_timestamp " +
                    "WHERE " + KEY_ID + " = old." + KEY_ID + ";" +
                    "END";


    public static String getDatabaseName() {
        return DATABASE_NAME;
    }

    public static int getDatabaseVersion() {
        return DATABASE_VERSION;
    }

    public static String getTableGame() {
        return TABLE_GAME;
    }

    public static String getKeyId() {
        return KEY_ID;
    }

    public static String getTIME() {
        return TIME;
    }

    public static String getLEVEL() {
        return LEVEL;
    }

    public static String getTSUNDERE() {
        return TSUNDERE;
    }

    public static String getNEKO() {
        return NEKO;
    }

    public static String getMATURE() {
        return MATURE;
    }

    public static String getSCORE() {
        return SCORE;
    }

    public static String getCreateTableGame() {
        return CREATE_TABLE_GAME;
    }

    public static String getUpdateTimeTrigger() { return UPDATE_TIME_TRIGGER; }
}
