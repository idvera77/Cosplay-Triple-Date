package com.mystra77.visualnovel.stages;

import com.mystra77.visualnovel.R;

public class Stage2 extends Stage {

    /**
     * Empty builder
     * Within this we include all the inherited setters, each one serves us to introduce the music,
     * image background and script of the stage
     */
    public Stage2() {
        setStageBackground(R.drawable.stage2background);
        setStageMusic(R.raw.theme_stage2);
        setScriptPlot1(R.raw.chapter2nekomature);
        setScriptPlot2(R.raw.chapter2angelneko);
        setScriptPlot3(R.raw.chapter2angelmature);
    }

}
