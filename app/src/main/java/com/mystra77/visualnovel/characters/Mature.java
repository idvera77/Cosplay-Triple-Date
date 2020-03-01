package com.mystra77.visualnovel.characters;

import com.mystra77.visualnovel.R;

public class Mature extends GirlCharacters {

    public Mature() {
        setImageNormaLeft(R.mipmap.left_mature_normal);
        setImageNormalRight(R.mipmap.right_mature_normal);
        setImageLaughtLeft(R.mipmap.left_mature_happy);
        setImageLaughtRight(R.mipmap.right_mature_happy);
        setImageAngryLeft(R.mipmap.left_mature_angry);
        setImageAngryRight(R.mipmap.right_mature_angry);

        setSoundNormal(R.raw.mature_sound_normal);
        setSoundHappy(R.raw.mature_sound_happy);
        setSoundAngry(R.raw.mature_sound_angry);

        setTextStage1("^_^ Jose, vente con nosotras que queremos darte calorcito.");
        setTextStage2("MATURE 2");
    }
}
