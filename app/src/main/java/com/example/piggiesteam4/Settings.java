/**
 * AUCSC 220
 * PiggiesTeam4
 *
 * Settings.java
 *
 * Stores the players settings.
 */
package com.example.piggiesteam4;

import android.content.Context;
import android.content.SharedPreferences;

public class Settings {
    private static boolean musicOn = true;
    private static boolean sfxOn = true;

    /**
     * Sets whether music is on.
     * @param musicOn if music will be enabled.
     */
    public static void setMusicOn(boolean musicOn){
        Settings.musicOn = musicOn;
    }//setMusicOn

    /**
     * Sets whether sfx are on.
     * @param sfxOn if sfx will be enabled.
     */
    public static void setSfxOn(boolean sfxOn){
        Settings.sfxOn = sfxOn;
    }//setSfxOn

    /**
     * Gets whether music is on.
     * @return whether music is on.
     */
    public static boolean isMusicOn(){
        return musicOn;
    }//isMusicOn

    /**
     * Gets whether music is on.
     * @return whether music is on.
     */
    public static boolean getMusicOn(){
        return isMusicOn();
    }//getMusicOn

    /**
     * Gets whether sfx are on.
     * @return whether sfx are on.
     */
    public static boolean isSfxOn(){
        return sfxOn;
    }//isSfxOn

    /**
     * Gets whether sfx are on.
     * @return whether sfx are on.
     */
    public static boolean getSfxOn(){
        return isSfxOn();
    }//getSfxOn

    /**
     * Saves music and sfx settings.
     * Should be called whenever settings have been changed and confirmed.
     * @param context from getApplicationContext() called inside activity class.
     */
    public static void save(Context context){
        SharedPreferences pref = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("music", musicOn);
        editor.putBoolean("sfx", sfxOn);
        editor.commit();
    }//save

    /**
     * Retrieves saved values of music and sfx settings.
     * Should be called whenever the app is opened.
     * @param context from getApplicationContext() called inside activity class.
     */
    public static void retrieve(Context context){
        SharedPreferences pref = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        musicOn = pref.getBoolean("music", true);
        sfxOn = pref.getBoolean("sfx", true);

    }//save

}//Settings
