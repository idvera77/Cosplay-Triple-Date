package com.mystra77.visualnovel.characters;

public abstract class GirlCharacters {
    private int imageAngry;
    private int imageNormal;
    private int imageLaught;
    private int soundAngry;
    private int soundNormal;
    private int soundLaught;
    private String textStage1;
    private String textStage2;
    private String textStage3;
    private String textStage4;

    public GirlCharacters(){

    }

    public GirlCharacters(int imageAngry, int imageNormal, int imageLaught, int soundAngry, int soundNormal,
                          int soundLaught, String textStage1, String textStage2, String textStage3, String textStage4) {
        this.imageAngry = imageAngry;
        this.imageNormal = imageNormal;
        this.imageLaught = imageLaught;
        this.soundAngry = soundAngry;
        this.soundNormal = soundNormal;
        this.soundLaught = soundLaught;
        this.textStage1 = textStage1;
        this.textStage2 = textStage2;
        this.textStage3 = textStage3;
        this.textStage4 = textStage4;
    }

    public int getImageAngry() {
        return imageAngry;
    }

    public void setImageAngry(int imageAngry) {
        this.imageAngry = imageAngry;
    }

    public int getImageNormal() {
        return imageNormal;
    }

    public void setImageNormal(int imageNormal) {
        this.imageNormal = imageNormal;
    }

    public int getImageLaught() {
        return imageLaught;
    }

    public void setImageLaught(int imageLaught) {
        this.imageLaught = imageLaught;
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

    public int getSoundLaught() {
        return soundLaught;
    }

    public void setSoundLaught(int soundLaught) {
        this.soundLaught = soundLaught;
    }

    public String getTextStage1() {
        return textStage1;
    }

    public void setTextStage1(String textStage1) {
        this.textStage1 = textStage1;
    }

    public String getTextStage2() {
        return textStage2;
    }

    public void setTextStage2(String textStage2) {
        this.textStage2 = textStage2;
    }

    public String getTextStage3() {
        return textStage3;
    }

    public void setTextStage3(String textStage3) {
        this.textStage3 = textStage3;
    }

    public String getTextStage4() {
        return textStage4;
    }

    public void setTextStage4(String textStage4) {
        this.textStage4 = textStage4;
    }
}
