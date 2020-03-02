package com.mystra77.visualnovel.stages;

import android.media.MediaPlayer;

import com.mystra77.visualnovel.R;


public class Stage1 extends Stage {

    public Stage1() {
        setStageBackground(R.mipmap.stage1background);
        setStageMusic(R.raw.theme_stage1);
        setScriptPlot("script1.txt");
    }
}
