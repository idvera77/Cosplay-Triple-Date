package com.mystra77.visualnovel.characters;

import com.mystra77.visualnovel.R;


public class Angel extends GirlCharacters {

    /**
     * Empty builder
     * Within this we include all the inherited setters, each one serves us to introduce the name,
     * sound/emotion and image/position of the angel girl
     */
    public Angel() {
        setName("Angel");
        setImageNormaLeft(R.drawable.left_angel_normal);
        setImageNormalRight(R.drawable.right_angel_normal);
        setImageLaughtLeft(R.drawable.left_angel_happy);
        setImageLaughtRight(R.drawable.right_angel_happy);
        setImageAngryLeft(R.drawable.left_angel_angry);
        setImageAngryRight(R.drawable.right_angel_angry);
        setSceneCensored(R.drawable.censored);
        setSceneSexUncensored(R.drawable.uncensored);
        setSoundNormal(R.raw.angel_sound_normal);
        setSoundHappy(R.raw.angel_sound_happy);
        setSoundAngry(R.raw.angel_sound_angry);
    }

}
