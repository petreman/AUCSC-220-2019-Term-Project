package com.example.piggiesteam4;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class GridSizeActivity extends AppCompatActivity implements View.OnClickListener {

    private int selectedSize;
    private int currentSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_size);

        currentSize = getIntent().getIntExtra("currentSize", 0);
        disableCurrentSize(currentSize);

        Button size5 = (Button) findViewById(R.id.gridSize5x5);
        Button size6 = (Button) findViewById(R.id.gridSize6x6);
        Button size7 = (Button) findViewById(R.id.gridSize7x7);
        Button confirm = (Button) findViewById(R.id.gridSizeConfirm);

        size5.setOnClickListener(this);
        size6.setOnClickListener(this);
        size7.setOnClickListener(this);
        confirm.setOnClickListener(this);

    }//onCreate

    /**
     * Passes the new game size to the main activity so that it can create a game
     * of the requested new size
     *
     * By Alvin
     */
    public void confirmNewGridSize() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("size", selectedSize);
        setResult(RESULT_OK, intent);
        finish();
    }//confirmNewGridSize

    /**
     * Disables the selection of the current game's current size
     *
     * By Alvin
     *
     * @param size - the size of the current game
     */
    public void disableCurrentSize(int size) {

        switch (size) {

            case 5:
                findViewById(R.id.gridSize5x5).setEnabled(false);
                break;

            case 6:
                findViewById(R.id.gridSize6x6).setEnabled(false);
                break;

            case 7:
                findViewById(R.id.gridSize7x7).setEnabled(false);
                break;

            default:
                throw new AssertionError("Error with size of current grid");

        }//switch

    }//disableCurrentSize

    /**
     * What happens when the buttons on the activity are clicked.
     *
     * Highlights the currently selected new size, and saves the selected size in case
     * "confirm" button is pressed so that the result can be sent to the main activity
     *
     * By Keegan
     *
     * @param v - the button clicked
     */
    @Override
    public void onClick(View v) {

        Button confirm = (Button) findViewById(R.id.gridSizeConfirm);

        int unselectedSize = 5;

        switch (v.getId()) {

            case R.id.gridSize5x5:

                selectedSize = 5;

                if (currentSize == 6) {
                    unselectedSize = 7;
                }//if

                else {
                    unselectedSize = 6;
                }//else

                break;

            case R.id.gridSize6x6:

                selectedSize = 6;

                if (currentSize == 5) {
                    unselectedSize = 7;
                }//if

                else {
                    unselectedSize = 5;
                }//else

                break;

            case R.id.gridSize7x7:

                selectedSize = 7;

                if (currentSize == 5) {
                    unselectedSize = 6;
                }//if

                else {
                    unselectedSize = 5;
                }//else

                break;

            case R.id.gridSizeConfirm:
                confirmNewGridSize();
                break;

            default:
                Log.d("GridSizeActivity", "Unexpected current size, setting size to 5");

        }//switch

        toggleHighlight(v, unselectedSize);
        confirm.setEnabled(true);

    }//onClick

    /**
     * Darkens the choice for new size that the user chose
     *
     * By Keegan
     *
     * @param v - the pressed button
     * @param unselectedSize - the size that wasn't chosen
     */
    void toggleHighlight(View v, int unselectedSize) {

        v.getBackground().setColorFilter(getColor(R.color.gray), PorterDuff.Mode.MULTIPLY);

        switch (unselectedSize) {

            case 5:
                findViewById(R.id.gridSize5x5).getBackground().clearColorFilter();
                break;

            case 6:
                findViewById(R.id.gridSize6x6).getBackground().clearColorFilter();
                break;

            case 7:
                findViewById(R.id.gridSize7x7).getBackground().clearColorFilter();
                break;

        }//switch

    }//to

}//GridSizeActivity
