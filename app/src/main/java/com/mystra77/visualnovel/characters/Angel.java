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
        setImageNormaLeft(R.mipmap.left_angel_normal);
        setImageNormalRight(R.mipmap.right_angel_normal);
        setImageLaughtLeft(R.mipmap.left_angel_happy);
        setImageLaughtRight(R.mipmap.right_angel_happy);
        setImageAngryLeft(R.mipmap.left_angel_angry);
        setImageAngryRight(R.mipmap.right_angel_angry);
        setSceneCensored(R.mipmap.right_angel_normal);
        setSceneSexUncensored(R.mipmap.right_angel_happy);
        setSoundNormal(R.raw.angel_sound_normal);
        setSoundHappy(R.raw.angel_sound_happy);
        setSoundAngry(R.raw.angel_sound_angry);
    }
}
