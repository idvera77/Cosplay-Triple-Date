package com.mystra77.visualnovel.characters;

import com.mystra77.visualnovel.R;

public class Witch extends GirlCharacters {

    /**
     * Empty builder
     * Within this we include all the inherited setters, each one serves us to introduce the name,
     * sound/emotion and image/position of the mature girl
     */
    public Witch() {
        setName("Witch");
        setImageNormaLeft(R.drawable.left_witch_normal);
        setImageNormalRight(R.drawable.right_witch_normal);
        setImageLaughtLeft(R.drawable.left_witch_happy);
        setImageLaughtRight(R.drawable.right_witch_happy);
        setImageAngryLeft(R.drawable.left_witch_angry);
        setImageAngryRight(R.drawable.right_witch_angry);
        setSceneCensored(R.drawable.censored);
        setSceneSexUncensored(R.drawable.uncensored);
        setSoundNormal(R.raw.witch_sound_normal);
        setSoundHappy(R.raw.witch_sound_happy);
        setSoundAngry(R.raw.witch_sound_angry);
    }

}

