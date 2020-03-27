package com.mystra77.visualnovel.classes;

public class KeyWords {

    private String keyAngel, keyNeko, keyWitch, keyNormalLeftPosition, keyNormalCenterPosition,
            keyNormalRightPosition, keyHappyLeftPosition, keyHappyCenterPosition, keyHappyRightPosition,
            keyAngryLeftPosition, keyAngryCenterPosition, keyAngryRightPosition, keyButtons;
    //String type variable, indicates the keyWord

    /**
     * Builder with all the keywords that help us to read the script and perform actions
     */
    public KeyWords() {
        this.keyAngel = "*angel*";
        this.keyNeko = "*neko*";
        this.keyWitch = "*witch*";
        this.keyNormalLeftPosition = "*normalLeftPosition*";
        this.keyNormalCenterPosition = "*normalCenterPosition*";
        this.keyNormalRightPosition = "*normalRightPosition*";
        this.keyHappyLeftPosition = "*happyLeftPosition*";
        this.keyHappyCenterPosition = "*happyCenterPosition*";
        this.keyHappyRightPosition = "*happyRightPosition*";
        this.keyAngryLeftPosition = "*angryLeftPosition*";
        this.keyAngryCenterPosition = "*angryCenterPosition*";
        this.keyAngryRightPosition = "*angryRightPosition*";
        this.keyButtons = "*buttons*";
    }

    /**
     * GETTERS that returns the value of the assigned String in each field
     */
    public String getKeyAngel() {
        return keyAngel;
    }

    public String getKeyNeko() {
        return keyNeko;
    }

    public String getKeyWitch() {
        return keyWitch;
    }

    public String getKeyNormalLeftPosition() {
        return keyNormalLeftPosition;
    }

    public String getKeyNormalCenterPosition() {
        return keyNormalCenterPosition;
    }

    public String getKeyNormalRightPosition() {
        return keyNormalRightPosition;
    }

    public String getKeyHappyLeftPosition() {
        return keyHappyLeftPosition;
    }

    public String getKeyHappyCenterPosition() {
        return keyHappyCenterPosition;
    }

    public String getKeyHappyRightPosition() {
        return keyHappyRightPosition;
    }

    public String getKeyAngryLeftPosition() {
        return keyAngryLeftPosition;
    }

    public String getKeyAngryCenterPosition() {
        return keyAngryCenterPosition;
    }

    public String getKeyAngryRightPosition() {
        return keyAngryRightPosition;
    }

    public String getKeyButtons() {
        return keyButtons;
    }

}
