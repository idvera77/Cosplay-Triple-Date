package com.mystra77.visualnovel.stages;

import com.mystra77.visualnovel.R;

public class Stage1 extends Stage {

    /**
     * Empty builder
     * Within this we include all the inherited setters, each one serves us to introduce the music,
     * image background and script of the stage
     */
    public Stage1() {
        setStageBackground(R.drawable.stage1background);
        setStageMusic(R.raw.theme_stage1);
        setScriptPlot1(R.raw.chapter1);
    }

}
