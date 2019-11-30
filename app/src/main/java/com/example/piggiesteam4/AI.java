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
    private int xValue;
    private int yValue;
    private int[][] xCoords;
    private int[][] yCoords;




    AI(int score){
        this.score = 0;
        this.turn = false;
        this.color = Color.BLACK;
        this.xCoords = new int[xValue][yValue - 1];
        this.xCoords = new int[xValue - 1][yValue];
    }// end of constructor

    Grid grid = new Grid(4, 4);

    /**
     * This function scans through the entire board in order to check for possible pens it can make.
     * If a pen can be made, it call place fence function, which places a fence in the area that
     * the pen can be made
     *
     * @param grid-- takes in the Grid as the paremeter, as it scans through the entire grid.
     */

    public void checkForPossibleFencePlacement(Grid grid){
        boolean isPlacementFound = false;
        boolean isGameEnd = false;

        while (!isPlacementFound && !isGameEnd){
            // checking for 1st row
            while (yValue <= grid.getX() - 1){
                if (grid.checkPenBelow(xValue,yValue) == true){
                    grid.setFenceX(xValue,yValue,color);
                    isPlacementFound = true;
                    break;
                }else
                    yValue ++;
            }// end of while

            // check for the rest of the board (2 row --> on wards)
            yValue = 0;
            xValue ++;
            while (xValue <= grid.getX() - 1){
                while (yValue <= grid.getX() - 1){
                    if (grid.checkPenBelow(xValue,yValue) == true || grid.checkPenAbove(xValue,yValue) == true) {
                        grid.setFenceX(xValue,yValue,color);
                        isPlacementFound = true;
                        break;
                    }else
                        yValue ++;
                }// end of inner loop
                xValue ++;
            }// end of outer loop

            // bottom of the row
            while (yValue <= grid.getX() - 1){
                if (grid.checkPenAbove(xValue,yValue) == true){
                    grid.setFenceX(xValue,yValue,color);
                    isPlacementFound = true;
                    break;
                }else
                    yValue ++;
            }// end of while

            //================================================================================
            // checking for cols
            xValue = 0;
            yValue = 0;

            while (xValue <= grid.getY()){
                if (grid.checkPenRight(xValue,yValue) == true){
                    grid.setFenceX(xValue,yValue,color);
                    isPlacementFound = true;
                    break;
                }else
                    xValue ++;
            }// end of while

            // check for the rest of the board (2nd col --> on wards)
            yValue ++;
            xValue = 0;
            while (yValue <= grid.getY()){
                while (xValue <= grid.getX() - 1){
                    if (grid.checkPenRight(xValue,yValue) == true || grid.checkPenLeft(xValue,yValue) == true) {
                        grid.setFenceX(xValue,yValue,color);
                        isPlacementFound = true;
                        break;
                    }else
                        xValue ++;
                }// end of inner loop
                yValue ++;
            }// end of outer loop

            // far right row
            while (xValue <= grid.getY()){
                if (grid.checkPenLeft(xValue,yValue) == true){
                    grid.setFenceX(xValue,yValue,color);
                    isPlacementFound = true;
                    break;
                }else
                    xValue ++;
            }// end of while





        }// end of while

    }// end of function
//    void checkForPossiblePen (Grid grid){
//        for (int pos = 0; pos < grid.getY(); pos ++){ //we want to check for each single player
//            grid.checkPenBelow(grid.getX(),grid.getY());
//        }//end of for
//
//        this.grid;
//
//
//    }// end of checkForPossiblePen



}// end of AI class

