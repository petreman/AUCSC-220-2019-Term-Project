/**
 * AUCSC 220
 * PiggiesTeam4
 *
 * AI.java
 *
 * The AI class will scan through the grid and determines where is the best
 * placement to place a fence in order to make a pen. Otherwise, it will t
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

    public void checkForPossibleFencePlacement(Grid grid){
        boolean isPlacementFound = false;
        boolean isGameEnd = false;

        while (!isPlacementFound){

            // check for the rows

            if (checkTopRow() == true){
                isPlacementFound = true;
                checkForPossibleFencePlacement(grid);
            }else if (checkMiddleRows() == true){
                isPlacementFound = true;
                checkForPossibleFencePlacement(grid);
            }else if (checkBottomRow() == true){
                isPlacementFound = true;
                checkForPossibleFencePlacement(grid);
            }

            //================================================================================
            // check for columns

            if (checkTopCol() == true){
                isPlacementFound = true;
                checkForPossibleFencePlacement(grid);
            }else if (checkMiddleCol() == true){
                isPlacementFound = true;
            }else if (checkBottomCol() == true){
                isPlacementFound = true;
                checkForPossibleFencePlacement(grid);
            }
        }// end of while

    }// end of function

    public boolean checkTopRow(){
        while (yValue <= grid.getX() - 1){
            if  (grid.checkPenBelow(xValue,yValue) == true){
                grid.setFenceX(xValue,yValue,color);
                return true;
            }else
                yValue ++;
        }// end of while
        return false;
    } // end of checkTopRow

    public boolean checkMiddleRows(){
        yValue = 0;
        xValue ++;
        while (xValue <= grid.getX() - 1){
            while (yValue <= grid.getX() - 1){
                if (grid.checkPenBelow(xValue,yValue) == true || grid.checkPenAbove(xValue,yValue) == true) {
                    grid.setFenceX(xValue,yValue,color);
                    return true;
                }else
                    yValue ++;
            }// end of inner loop
            yValue = 0;
            xValue ++;
        }// end of outer loop
        return false;
    }// end of checkMiddleRows

    public boolean checkBottomRow(){
        while (yValue <= grid.getX() - 1){
            if (grid.checkPenAbove(xValue,yValue) == true){
                grid.setFenceX(xValue,yValue,color);
                return true;
            }else
                yValue ++;
        }// end of while
        return false;
    }// end of checkBottomRow

    public boolean checkTopCol(){
        xValue = 0;
        yValue = 0;

        while (xValue <= grid.getY()){
            if (grid.checkPenRight(xValue,yValue) == true){
                grid.setFenceX(xValue,yValue,color);
                return true;
            }else
                xValue ++;
        }// end of while
        return false;
    }// end of checkTopCol

    public boolean checkMiddleCol(){
        yValue ++;
        xValue = 0;
        while (yValue <= grid.getY()){
            while (xValue <= grid.getX() - 1){
                if (grid.checkPenRight(xValue,yValue) == true || grid.checkPenLeft(xValue,yValue) == true) {
                    grid.setFenceX(xValue,yValue,color);
                    return true;
                }else
                    xValue ++;
            }// end of inner loop
            yValue ++;
            xValue = 0;
        }// end of outer loop
        return false;
    }// end of checkMiddleCol

    public boolean checkBottomCol(){
        while (xValue <= grid.getY()){
            if (grid.checkPenLeft(xValue,yValue) == true){
                grid.setFenceX(xValue,yValue,color);
                return true;
            }else
                xValue ++;
        }// end of while
        return false;
    }// end of checkBottomCol

}// end of AI class

