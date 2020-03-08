package com.mystra77.visualnovel.characters;

import com.mystra77.visualnovel.R;

public class Neko extends GirlCharacters {

    /**
     * Empty builder
     * Within this we include all the inherited setters, each one serves us to introduce the name,
     * sound/emotion and image/position of the neko girl
     */
    public Neko() {
        setName("Neko:");
        setImageNormaLeft(R.mipmap.left_neko_normal);
        setImageNormalRight(R.mipmap.right_neko_normal);
        setImageLaughtLeft(R.mipmap.left_neko_happy);
        setImageLaughtRight(R.mipmap.right_neko_happy);
        setImageAngryLeft(R.mipmap.left_neko_angry);
        setImageAngryRight(R.mipmap.right_neko_angry);
        setSceneCensored(R.mipmap.right_neko_normal);
        setSceneSexUncensored(R.mipmap.right_neko_happy);
        setSoundNormal(R.raw.neko_sound_normal);
        setSoundHappy(R.raw.neko_sound_happy);
        setSoundAngry(R.raw.neko_sound_angry);
    }
}
