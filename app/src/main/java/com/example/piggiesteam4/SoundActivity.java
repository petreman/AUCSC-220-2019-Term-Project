package com.example.piggiesteam4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class SoundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);
        setListeners();
        getSettings();
    }

    public void setListeners(){
        Switch music = (Switch) findViewById(R.id.music);
        music.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Settings.setMusicOn(isChecked);
                testSet();
            }
        });

        Switch sfx = (Switch) findViewById(R.id.soundfx);
        sfx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Settings.setSfxOn(isChecked);
                testSet();
            }
        });
        }

        public void getSettings(){
            Settings.retrieve(getApplicationContext());
            Switch sfx = (Switch) findViewById(R.id.soundfx);
            Switch music = (Switch) findViewById(R.id.music);
            sfx.setChecked(Settings.getSfxOn());
            music.setChecked(Settings.getMusicOn());
        }

        public void confirm(View v){
            Settings.save(getApplicationContext());
        }

        public void testSet(){
            TextView sound = (TextView) findViewById(R.id.textsfx);
            TextView music = (TextView) findViewById(R.id.textMusic);
            Boolean sfx = Settings.isSfxOn();
            Boolean mus = Settings.isMusicOn();
            sound.setText(sfx.toString());
            music.setText(mus.toString());
    }
}
