package com.mystra77.visualnovel.stages;


public abstract class Stage {
    private int stageMusic; //Integer type variable, indicates the music in the stage
    private int stageBackground; //Integer type variable, indicates the image background in the stage
    private int scriptPlot1, scriptPlot2, scriptPlot3; //Integer type variable, indicates the txt File save in the app

    /**
     * Empty builder
     * The Stage object is used to save the music, background and scripts(txt with the plot)
     */
    public Stage() {

    }

    /**
     * GETTERS AND SETTERS that returns or modify the value in each field
     *
     * We will use the getters to receive the necessary images, music or script
     *
     * The setters will be used in the class you inherit and we will insert the necessary elements
     */

    public int getStageMusic() { return stageMusic; }

    public void setStageMusic(int stageMusic) {
        this.stageMusic = stageMusic;
    }

    public int getStageBackground() {
        return stageBackground;
    }

    public void setStageBackground(int stageBackground) {
        this.stageBackground = stageBackground;
    }

    public int getScriptPlot1() { return scriptPlot1; }

    public void setScriptPlot1(int scriptPlot1) {
        this.scriptPlot1 = scriptPlot1;
    }

    public int getScriptPlot2() { return scriptPlot2; }

    public void setScriptPlot2(int scriptPlot2) {
        this.scriptPlot2 = scriptPlot2;
    }

    public int getScriptPlot3() {
        return scriptPlot3;
    }

    public void setScriptPlot3(int scriptPlot3) {
        this.scriptPlot3 = scriptPlot3;
    }

}
