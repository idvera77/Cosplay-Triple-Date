package com.mystra77.visualnovel.stages;

import android.os.Environment;

import com.mystra77.visualnovel.R;
import com.mystra77.visualnovel.characters.Mature;
import com.mystra77.visualnovel.characters.Neko;
import com.mystra77.visualnovel.characters.Angel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;


public abstract class Stage {
    private int stageMusic;
    private int stageBackground;
    private String scriptPlot;

    public Stage() {

    }

    public Stage(int stageMusic, int stageBackground, String scriptPlot, Angel angel, Neko neko, Mature mature) {
        this.stageMusic = stageMusic;
        this.stageBackground = stageBackground;
        this.scriptPlot = scriptPlot;
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

    public String getScriptPlot() {
        return scriptPlot;
    }

    public void setScriptPlot(String scriptPlot) {
        this.scriptPlot = scriptPlot;
    }


}
