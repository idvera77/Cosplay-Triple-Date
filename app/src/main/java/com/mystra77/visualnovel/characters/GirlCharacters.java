package com.mystra77.visualnovel.characters;

public abstract class GirlCharacters {
    private String name;
    private int imageAngryLeft, imageAngryRight;
    private int imageNormaLeft, imageNormalRight;
    private int imageLaughtLeft, imageLaughtRight;
    private int sceneSexUncensored, sceneCensored;
    private int soundAngry;
    private int soundNormal;
    private int soundHappy;

    public GirlCharacters() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageAngryLeft() {
        return imageAngryLeft;
    }

    public void setImageAngryLeft(int imageAngryLeft) {
        this.imageAngryLeft = imageAngryLeft;
    }

    public int getImageAngryRight() {
        return imageAngryRight;
    }

    public void setImageAngryRight(int imageAngryRight) {
        this.imageAngryRight = imageAngryRight;
    }

    public int getImageNormaLeft() {
        return imageNormaLeft;
    }

    public void setImageNormaLeft(int imageNormaLeft) {
        this.imageNormaLeft = imageNormaLeft;
    }

    public int getImageNormalRight() {
        return imageNormalRight;
    }

    public void setImageNormalRight(int imageNormalRight) {
        this.imageNormalRight = imageNormalRight;
    }

    public int getImageLaughtLeft() {
        return imageLaughtLeft;
    }

    public void setImageLaughtLeft(int imageLaughtLeft) {
        this.imageLaughtLeft = imageLaughtLeft;
    }

    public int getImageLaughtRight() {
        return imageLaughtRight;
    }

    public void setImageLaughtRight(int imageLaughtRight) {
        this.imageLaughtRight = imageLaughtRight;
    }

    public int getSoundAngry() {
        return soundAngry;
    }

    public void setSoundAngry(int soundAngry) {
        this.soundAngry = soundAngry;
    }

    public int getSoundNormal() {
        return soundNormal;
    }

    public void setSoundNormal(int soundNormal) {
        this.soundNormal = soundNormal;
    }

    public int getSoundHappy() {
        return soundHappy;
    }

    public void setSoundHappy(int soundHappy) {
        this.soundHappy = soundHappy;
    }

    public int getSceneSexUncensored() {
        return sceneSexUncensored;
    }

    public void setSceneSexUncensored(int sceneSexUncensored) {
        this.sceneSexUncensored = sceneSexUncensored;
    }

    public int getSceneCensored() {
        return sceneCensored;
    }

    public void setSceneCensored(int sceneCensored) {
        this.sceneCensored = sceneCensored;
    }
}
