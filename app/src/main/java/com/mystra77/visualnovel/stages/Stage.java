package com.mystra77.visualnovel.stages;

public abstract class Stage {
    private int stageMusic;
    private int stageBackground;
    private String intro;
    private String tsundere;
    private String neko;
    private String mature;

    public Stage(){

    }

    public Stage(int stageMusic, int stageBackground, String intro, String tsundere, String neko, String mature) {
        this.stageMusic = stageMusic;
        this.stageBackground = stageBackground;
        this.intro = intro;
        this.tsundere = tsundere;
        this.neko = neko;
        this.mature = mature;
    }

    public int getStageMusic() {
        return stageMusic;
    }

    public void setStageMusic(int stageMusic) {
        this.stageMusic = stageMusic;
    }

    public int getStageBackground() {
        return stageBackground;
    }

    public void setStageBackground(int stageBackground) {
        this.stageBackground = stageBackground;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getTsundere() {
        return tsundere;
    }

    public void setTsundere(String tsundere) {
        this.tsundere = tsundere;
    }

    public String getNeko() {
        return neko;
    }

    public void setNeko(String neko) {
        this.neko = neko;
    }

    public String getMature() {
        return mature;
    }

    public void setMature(String mature) {
        this.mature = mature;
    }
}
