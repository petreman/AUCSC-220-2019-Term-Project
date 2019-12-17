package com.example.piggiesteam4;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class GridSizeActivity extends AppCompatActivity implements View.OnClickListener {

    int selectedSize;
    int currentSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_size);
        currentSize = getIntent().getIntExtra("currentSize", 0);
        disableCurrentSize(currentSize);



        Button size5 = (Button) findViewById(R.id.gridSize5x5);
        Button size6 = (Button) findViewById(R.id.gridSize6x6);
        Button size7 = (Button) findViewById(R.id.gridSize7x7);

        //size5.setOnClickListener(this);
        //size6.setOnClickListener(this);
        //size7.setOnClickListener(this);


    }//onCreate



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
                selectedSize = 55;
                break;
            case "6x6":
                selectedSize = 66;
                break;
            case "7x7":
                selectedSize = 77;
                break;
            default:
                Log.d("GridSizeActivity", "Unexpected current size");
        }//switch
        confirm.setEnabled(true);
    }//switchSize

    /**
     * Starts the game with the selected size, will replace the current game.
     * @param v View
     */
    public void confirmNewGridSize(View v){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("size", selectedSize);
        setResult(RESULT_OK, intent);
        finish();

    }//confirmNewGridSize

    public void disableCurrentSize(int size){
        switch (size){
            case Grid.GRID_5x5:
                findViewById(R.id.gridSize5x5).setEnabled(false);
                break;
            case Grid.GRID_6x6:
                findViewById(R.id.gridSize6x6).setEnabled(false);
                break;
            case Grid.GRID_7x7:
                findViewById(R.id.gridSize7x7).setEnabled(false);
                break;
            default:
                throw new AssertionError("Error with size of current grid");
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.gridSize5x5:
                selectedSize = 5;
                break;

            case R.id.gridSize6x6:
                selectedSize = 6;
                break;

            case R.id.gridSize7x7:
                selectedSize = 7;
                break;

        }//switch

        toggleHighlight(v);

    }//onClick

    void toggleHighlight(View v){

        switch (v.getId()){

            case R.id.gridSize5x5:
                v.getBackground().setColorFilter(getColor(R.color.gray), PorterDuff.Mode.MULTIPLY);
                findViewById(R.id.gridSize6x6).getBackground().
                        setColorFilter(getColor(R.color.grayLight), PorterDuff.Mode.MULTIPLY);
                findViewById(R.id.gridSize7x7).getBackground().
                        setColorFilter(getColor(R.color.grayLight), PorterDuff.Mode.MULTIPLY);
                break;

            case R.id.gridSize6x6:
                v.getBackground().setColorFilter(getColor(R.color.gray), PorterDuff.Mode.MULTIPLY);
                findViewById(R.id.gridSize5x5).getBackground().
                        setColorFilter(getColor(R.color.grayLight), PorterDuff.Mode.MULTIPLY);
                findViewById(R.id.gridSize7x7).getBackground().
                        setColorFilter(getColor(R.color.grayLight), PorterDuff.Mode.MULTIPLY);
                break;

            case R.id.gridSize7x7:
                v.getBackground().setColorFilter(getColor(R.color.gray), PorterDuff.Mode.MULTIPLY);
                findViewById(R.id.gridSize6x6).getBackground().
                        setColorFilter(getColor(R.color.grayLight), PorterDuff.Mode.MULTIPLY);
                findViewById(R.id.gridSize5x5).getBackground().
                        setColorFilter(getColor(R.color.grayLight), PorterDuff.Mode.MULTIPLY);

                break;

        }//switch

    }//to

}//GridSizeActivity
