package com.mystra77.visualnovel.characters;

public abstract class GirlCharacters {
    private int imageAngryLeft, imageAngryRight;
    private int imageNormaLeft, imageNormalRight;
    private int imageLaughtLeft, imageLaughtRight;
    private int soundAngry;
    private int soundNormal;
    private int soundHappy;
    private String textStage1;
    private String textStage2;
    private String textStage3;
    private String textStage4;

    public GirlCharacters() {

    }

    public GirlCharacters(int imageAngryLeft, int imageAngryRight, int imageNormaLeft, int imageNormalRight, int imageLaughtLeft, int imageLaughtRight, int soundAngry, int soundNormal, int soundHappy, String textStage1, String textStage2, String textStage3, String textStage4) {
        this.imageAngryLeft = imageAngryLeft;
        this.imageAngryRight = imageAngryRight;
        this.imageNormaLeft = imageNormaLeft;
        this.imageNormalRight = imageNormalRight;
        this.imageLaughtLeft = imageLaughtLeft;
        this.imageLaughtRight = imageLaughtRight;
        this.soundAngry = soundAngry;
        this.soundNormal = soundNormal;
        this.soundHappy = soundHappy;
        this.textStage1 = textStage1;
        this.textStage2 = textStage2;
        this.textStage3 = textStage3;
        this.textStage4 = textStage4;
    }

    public int getImageAngryLeft() {
        return imageAngryLeft;
    }

    public int getImageAngryRight() {
        return imageAngryRight;
    }

    public int getImageNormaLeft() {
        return imageNormaLeft;
    }

    public int getImageNormalRight() {
        return imageNormalRight;
    }

    public int getImageLaughtLeft() {
        return imageLaughtLeft;
    }

    public int getImageLaughtRight() {
        return imageLaughtRight;
    }

    public int getSoundAngry() {
        return soundAngry;
    }

    public int getSoundNormal() {
        return soundNormal;
    }

    public int getSoundHappy() {
        return soundHappy;
    }

    public String getTextStage1() {
        return textStage1;
    }

    public String getTextStage2() {
        return textStage2;
    }

    public String getTextStage3() {
        return textStage3;
    }

    public String getTextStage4() {
        return textStage4;
    }

    public void setImageAngryLeft(int imageAngryLeft) {
        this.imageAngryLeft = imageAngryLeft;
    }

    public void setImageAngryRight(int imageAngryRight) {
        this.imageAngryRight = imageAngryRight;
    }

    public void setImageNormaLeft(int imageNormaLeft) {
        this.imageNormaLeft = imageNormaLeft;
    }

    public void setImageNormalRight(int imageNormalRight) {
        this.imageNormalRight = imageNormalRight;
    }

    public void setImageLaughtLeft(int imageLaughtLeft) {
        this.imageLaughtLeft = imageLaughtLeft;
    }

    public void setImageLaughtRight(int imageLaughtRight) {
        this.imageLaughtRight = imageLaughtRight;
    }

    public void setSoundAngry(int soundAngry) {
        this.soundAngry = soundAngry;
    }

    public void setSoundNormal(int soundNormal) {
        this.soundNormal = soundNormal;
    }

    public void setSoundHappy(int soundHappy) {
        this.soundHappy = soundHappy;
    }

    public void setTextStage1(String textStage1) {
        this.textStage1 = textStage1;
    }

    public void setTextStage2(String textStage2) {
        this.textStage2 = textStage2;
    }

    public void setTextStage3(String textStage3) {
        this.textStage3 = textStage3;
    }

    public void setTextStage4(String textStage4) {
        this.textStage4 = textStage4;
    }
}
