package com.example.piggiesteam4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GridSizeActivity extends AppCompatActivity {

    int selectedSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_size);
    }

    /**
     * Sets what size to switch to.
     * @param v View
     */
    public void switchSize(View v){
        Button button = (Button) v;
        Button confirm = (Button) findViewById(R.id.confirmButtonSize);
        String text = button.getText().toString().trim();
        switch (text){
            case "5x5":
                selectedSize = 25;
                break;
            case "6x6":
                selectedSize = 36;
                break;
            case "7x7":
                selectedSize =49;
        }//switch
        confirm.setEnabled(true);
    }//switchSize

    //TODO rename the method
    /**
     * Starts the game with the selected size, will replace the current game.
     * @param v View
     */
    public void pending(View v){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("size", selectedSize);
        startActivity(intent);
    }//pending
}//GridSizeActivity
