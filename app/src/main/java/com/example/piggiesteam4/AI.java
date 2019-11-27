/**
 *
 * AI.java
 *
 *
 *
 *
 *
 *
 * Created by Arnold Gihozo
 * Started on: November 23, 2019
 * Finished on:
 */

package com.example.piggiesteam4;


import android.graphics.Color;

public class AI extends Player {
    private int color;
    private boolean turn;
    private int score;

    AI(int score){
        this.score = 0;
        this.turn = false;
        this.color = Color.BLACK;
    }// end of constructor

    Grid grid = new Grid(4, 4);

    /**
     * This function scans through the entire board in order to check for possible pens it can make.
     * If a pen can be made, it call place fence function, which places a fence in the area that
     * the pen can be made
     *
     * @param grid-- takes in the Grid as the paremeter, as it scans through the entire grid.
     */
    void checkForPossiblePen (Grid grid){
        for (int pos = 0; pos < grid.getY(); pos ++){
            grid.checkPenBelow(grid.getX(),grid.getY());
        }//end of for
    }// end of checkForPossiblePen



}// end of AI class

