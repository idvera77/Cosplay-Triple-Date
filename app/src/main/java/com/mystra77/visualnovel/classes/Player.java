package com.mystra77.visualnovel.classes;

import java.io.Serializable;

public class Player implements Serializable {
    private int stage; //Integer type variable, indicates the chapter in the game
    private int angel; //Integer type variable, indicates the point obtained by the character angel
    private int neko; //Integer type variable, indicates the point obtained by the character neko
    private int mature; //Integer type variable, indicates the point obtained by the character mature
    private int score;  //Integer type variable, indicates the score to save to unlock images

    /**
     * Empty builder we use to start a game from 0
     */
    public Player() {
        this.stage = 1;
        this.angel = 0;
        this.neko = 0;
        this.mature = 0;
        this.score = 0;
    }

    /**
     * Builder used when loading a game
     *
     * @param stage  //Integer type variable, indicates the chapter in the game
     * @param angel  //Integer type variable, indicates the point obtained by the character angel
     * @param neko   //Integer type variable, indicates the point obtained by the character neko
     * @param mature //Integer type variable, indicates the point obtained by the character mature
     * @param score  //Integer type variable, indicates the score to save to unlock images
     */
    public Player(int stage, int angel, int neko, int mature, int score) {
        this.stage = stage;
        this.angel = angel;
        this.neko = neko;
        this.mature = mature;
        this.score = score;
    }

    /*
     * GETTERS AND SETTERS that returns or modify the value of the assigned Intenger in each field
     */
    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public int getAngel() {
        return angel;
    }

    public void setAngel(int angel) {
        this.angel = angel;
    }

    public int getNeko() {
        return neko;
    }

    public void setNeko(int neko) {
        this.neko = neko;
    }

    public int getMature() {
        return mature;
    }

    public void setMature(int mature) {
        this.mature = mature;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}

