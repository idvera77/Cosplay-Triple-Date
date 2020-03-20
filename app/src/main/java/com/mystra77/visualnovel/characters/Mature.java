package com.mystra77.visualnovel.characters;

import com.mystra77.visualnovel.R;

public class Mature extends GirlCharacters {

    /**
     * Empty builder
     * Within this we include all the inherited setters, each one serves us to introduce the name,
     * sound/emotion and image/position of the mature girl
     */
    public Mature() {
        setName("Mature");
        setImageNormaLeft(R.drawable.left_mature_normal);
        setImageNormalRight(R.drawable.right_mature_normal);
        setImageLaughtLeft(R.drawable.left_mature_happy);
        setImageLaughtRight(R.drawable.right_mature_happy);
        setImageAngryLeft(R.drawable.left_mature_angry);
        setImageAngryRight(R.drawable.right_mature_angry);
        setSceneCensored(R.drawable.right_mature_normal);
        setSceneSexUncensored(R.drawable.right_mature_happy);
        setSoundNormal(R.raw.mature_sound_normal);
        setSoundHappy(R.raw.mature_sound_happy);
        setSoundAngry(R.raw.mature_sound_angry);
    }
}
