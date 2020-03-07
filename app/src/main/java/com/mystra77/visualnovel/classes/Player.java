package com.mystra77.visualnovel.classes;

import java.io.Serializable;

public class Player implements Serializable {
    private int stage;
    private int angel;
    private int neko;
    private int mature;
    private int score;

    public Player() {
        this.stage = 1;
        this.angel = 0;
        this.neko = 0;
        this.mature = 0;
        this.score = 0;
    }

    public Player(int stage, int angel, int neko, int mature, int score) {
        this.stage = stage;
        this.angel = angel;
        this.neko = neko;
        this.mature = mature;
        this.score = score;
    }

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

    @Override
    public String toString() {
        return "Player{" +
                "stage=" + stage +
                ", angel=" + angel +
                ", neko=" + neko +
                ", mature=" + mature +
                ", score=" + score +
                '}';
    }
}

