package com.mystra77.visualnovel.stages;

import com.mystra77.visualnovel.R;

public class Stage4 extends Stage {

    /**
     * Empty builder
     * Within this we include all the inherited setters, each one serves us to introduce the music,
     * image background and script of the stage
     */
    public Stage4() {
        setStageBackground(R.drawable.stage4background);
        setStageMusic(R.raw.theme_stage4);
        setScriptPlot1(R.raw.chapter4witch);
        setScriptPlot2(R.raw.chapter4neko);
        setScriptPlot3(R.raw.chapter4angel);
    }

}
