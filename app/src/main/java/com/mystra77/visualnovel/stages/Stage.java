package com.mystra77.visualnovel.stages;

import com.mystra77.visualnovel.characters.Mature;
import com.mystra77.visualnovel.characters.Neko;
import com.mystra77.visualnovel.characters.Angel;

public abstract class Stage {
    private int stageMusic;
    private int stageBackground;
    private String intro;

    public Stage() {

    }

    public Stage(int stageMusic, int stageBackground, String intro, Angel angel, Neko neko, Mature mature) {
        this.stageMusic = stageMusic;
        this.stageBackground = stageBackground;
        this.intro = intro;
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

}
