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
import android.widget.TextView;

import com.example.piggiesteam4.R;
import com.example.piggiesteam4.Settings;

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
        music.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Settings.setMusicOn(isChecked);
                musicOn = isChecked;
                testSet();
                enableConfirm();
            }
        });

        Switch sfx = (Switch) findViewById(R.id.soundfx);
        sfx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Settings.setSfxOn(isChecked);
                sfxOn = isChecked;
                testSet();
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
    }

    //no longer displays accurate info
    public void testSet(){
        TextView sound = (TextView) findViewById(R.id.textsfx);
        TextView music = (TextView) findViewById(R.id.textMusic);
        Boolean sfx = Settings.isSfxOn();
        Boolean mus = Settings.isMusicOn();
        sound.setText(sfx.toString());
        music.setText(mus.toString());
    }

    /**
     * Enables the confirmation button when settings have been changed.
     */
    public void enableConfirm(){
        Button confirm = (Button) findViewById(R.id.confirmButtonSound);

        if ((sfxOn != Settings.getSfxOn()) || (musicOn != Settings.getMusicOn())) {
            confirm.setEnabled(true);
        }
        else{
            confirm.setEnabled(false);
        }
    }
}
