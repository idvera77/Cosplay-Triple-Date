package com.mystra77.visualnovel.stages;

import com.mystra77.visualnovel.R;

public class Stage3 extends Stage {

    /**
     * Empty builder
     * Within this we include all the inherited setters, each one serves us to introduce the music,
     * image background and script of the stage
     */
    public Stage3() {
        setStageBackground(R.mipmap.stage3backgound);
        setStageMusic(R.raw.theme_stage3);
        setScriptPlot1(R.raw.script2);
        setScriptPlot2(R.raw.script2);
        setScriptPlot3(R.raw.script2);
    }
}
