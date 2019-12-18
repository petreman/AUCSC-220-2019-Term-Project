/**
 * AUCSC 220
 * PiggiesTeam4
 *
 * SoundActivity.Java
 *
 * Page for changing the sound settings.
 */
package com.example.piggiesteam4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SoundActivity extends AppCompatActivity {

    boolean sfxOn;
    boolean musicOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);
        setListeners();
        getSettings();
    }

    public void setListeners() {
        Switch music = (Switch) findViewById(R.id.music);
        music.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                musicOn = isChecked;
                enableConfirm();
            }
        });

        Switch sfx = (Switch) findViewById(R.id.soundfx);
        sfx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                sfxOn = isChecked;
                enableConfirm();
            }
        });
    }

    /**
     * Retrieves saved settings values and sets class variables and view switches.
     */
    public void getSettings(){
        Settings.retrieve(getApplicationContext());
        Switch sfx = (Switch) findViewById(R.id.soundfx);
        Switch music = (Switch) findViewById(R.id.music);
        sfxOn = Settings.getSfxOn();
        musicOn = Settings.getMusicOn();
        sfx.setChecked(sfxOn);
        music.setChecked(musicOn);
    }

    /**
     * Confirms and saves settings.
     * @param v View
     */
    public void confirm(View v){
        Settings.setMusicOn(musicOn);
        Settings.setSfxOn(sfxOn);
        Settings.save(getApplicationContext());
        enableConfirm();
    }//confirm

    /**
     * Enables the confirmation button when settings have been changed.
     */
    public void enableConfirm(){
        Button confirm = (Button) findViewById(R.id.confirmButtonSound);

        if ((sfxOn != Settings.getSfxOn()) || (musicOn != Settings.getMusicOn())){
            confirm.setEnabled(true);
        }
        else{
            confirm.setEnabled(false);
        }//else
    }//if
}//SoundActivity
