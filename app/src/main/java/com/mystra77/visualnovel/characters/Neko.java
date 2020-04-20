package com.mystra77.visualnovel.characters;

import com.mystra77.visualnovel.R;

public class Neko extends GirlCharacters {

    /**
     * Empty builder
     * Within this we include all the inherited setters, each one serves us to introduce the name,
     * sound/emotion and image/position of the neko girl
     */
    public Neko() {
        setName("Neko");
        setImageNormaLeft(R.drawable.left_neko_normal);
        setImageNormalRight(R.drawable.right_neko_normal);
        setImageLaughtLeft(R.drawable.left_neko_happy);
        setImageLaughtRight(R.drawable.right_neko_happy);
        setImageAngryLeft(R.drawable.left_neko_angry);
        setImageAngryRight(R.drawable.right_neko_angry);
        setSceneCensored(R.drawable.censored);
        setSceneSexUncensored(R.drawable.uncensored);
        setSoundNormal(R.raw.neko_sound_normal);
        setSoundHappy(R.raw.neko_sound_happy);
        setSoundAngry(R.raw.neko_sound_angry);
    }

}
