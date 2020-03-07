package com.mystra77.visualnovel.characters;

import com.mystra77.visualnovel.R;

public class Mature extends GirlCharacters {

    public Mature() {
        setName("Mature:");

        setImageNormaLeft(R.mipmap.left_mature_normal);
        setImageNormalRight(R.mipmap.right_mature_normal);
        setImageLaughtLeft(R.mipmap.left_mature_happy);
        setImageLaughtRight(R.mipmap.right_mature_happy);
        setImageAngryLeft(R.mipmap.left_mature_angry);
        setImageAngryRight(R.mipmap.right_mature_angry);

        setSceneCensored(R.mipmap.right_mature_normal);
        setSceneSexUncensored(R.mipmap.right_mature_happy);

        setSoundNormal(R.raw.mature_sound_normal);
        setSoundHappy(R.raw.mature_sound_happy);
        setSoundAngry(R.raw.mature_sound_angry);
    }
}
