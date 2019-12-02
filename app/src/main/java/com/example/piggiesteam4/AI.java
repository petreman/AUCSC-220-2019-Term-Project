///**
// * AUCSC 220
// * PiggiesTeam4
// *
// * AI.java
// *
// * The AI class will scan through the grid and determines where is the best
// * placement to place a fence in order to make a pen. Otherwise, it will place the place
// * at an empty location on the grid
// *
// *
// *
// *
// * Created by Arnold Gihozo
// * Started on: November 23, 2019
// * Finished on: November 30,2019
// */
//
//package com.example.piggiesteam4;
//
//
//import android.graphics.Color;
//
//public class AI extends Player {
//    private int color;
//    private boolean turn;
//    private int score;
//    private int xValue;
//    private int yValue;
//    private boolean isRandomPositionChosen;
//    private boolean isGridEmpty;
//
//
//
//    AI(int score){
//        this.score = 0;
//        this.turn = false;
//        this.color = Color.BLACK;
//        this.isRandomPositionChosen = false;
//        this.isGridEmpty = false;
//    }// end of constructor
//
//
//
//    /**
//     * This function scans through the entire board in order to check for possible pens it can make.
//     * If a pen can be made, it call place fence function, which places a fence in the area that
//     * the pen can be made
//     *
//     * @param grid-- takes in the Grid as the paremeter, as it scans through the entire grid.
//     */
//
//    public void checkForPossibleFencePlacement(Grid grid){
//        boolean isPlacementFound = false;
//
//        while (!isPlacementFound){
//
//            // check for the rows
//
//            if (isGridEmpty == false) {
//
//                if (checkTopRow(grid) == true) {
//                    isPlacementFound = true;
//                    checkForPossibleFencePlacement(grid);
//                } else if (checkMiddleRows(grid) == true) {
//                    isPlacementFound = true;
//                    checkForPossibleFencePlacement(grid);
//                } else if (checkBottomRow(grid) == true) {
//                    isPlacementFound = true;
//                    checkForPossibleFencePlacement(grid);
//                }
//
//                //================================================================================
//                // check for columns
//
//                if (checkTopCol(grid) == true) {
//                    isPlacementFound = true;
//                    checkForPossibleFencePlacement(grid);
//                } else if (checkMiddleCol(grid) == true) {
//                    isPlacementFound = true;
//                } else if (checkBottomCol(grid) == true) {
//                    isPlacementFound = true;
//                    checkForPossibleFencePlacement(grid);
//                }
//            // if the grid is empty
//            }else{
//                if(choosePositionEmptyGrid(grid) == true){
//                    isGridEmpty = true;
//                    isPlacementFound = true;
//                    break;
//                }else{
//                    checkForPossibleFencePlacement(grid);
//                }
//            }// end of else
//            // end of outer if
//            // chooses a random position if the grid is empty
//
//
//        }// end of while
//
//    }// end of checkForPossibleFencePlacement
//
//    /**
//     * This function will be used by the AI to set a fence if the grid is empty or if
//     * no pen can be made based on the fence already placed on the grid. This function
//     * will recursively call itself until an empty spot is found on the grid.
//     *
//     * @return -- it will return true if it found a random empty spot and place a fence on it
//     */
//    public boolean choosePositionEmptyGrid(Grid grid){
//        while (isRandomPositionChosen != true) {
//            int firstNumber = (int) Math.random() * ((grid.getX() - grid.getY() + 1) + grid.getY());
//            int secondNumber = (int) Math.random() * ((grid.getX() - grid.getY() + 1) + grid.getY());
//            if (grid.setFenceX(firstNumber, secondNumber, color) == true){
//                isRandomPositionChosen = true;
//                return true;
//            }else
//                choosePositionEmptyGrid(grid);
//        }// end of while
//        return false;
//    }// end of choosePositionEmptyGrid
//
//    /**
//     * This function will go through each fence at the top of the grid row and checks if there is a
//     * pen that can be made from it. If there exists 3 other fences (at the bottom) it will put
//     * a fence in order to make a pen
//     *
//     * @return -- returns true if it found a spot to put a fence on in order to make a pen
//     */
//    public boolean checkTopRow(Grid grid){
//        while (yValue <= grid.getX() - 1){
//            if  (grid.checkPenBelow(xValue,yValue) == true){
//                grid.setFenceX(xValue,yValue,color);
//                return true;
//            }else
//                yValue ++;
//        }// end of while
//        return false;
//    } // end of checkTopRow
//
//    /**
//     * This function will go through the rest of the grid  (row)(ie: not the top and not the bottom)
//     * checks if there exists 3 other fences in order to make a pen.
//     *
//     * @return-- returns true if it found a spot to put a fence
//     */
//    public boolean checkMiddleRows(Grid grid){
//        yValue = 0;
//        xValue ++;
//        while (xValue <= grid.getX() - 1){
//            while (yValue <= grid.getX() - 1){
//                if (grid.checkPenBelow(xValue,yValue) == true ||
//                        grid.checkPenAbove(xValue,yValue) == true) {
//                    grid.setFenceX(xValue,yValue,color);
//                    return true;
//                }else
//                    yValue ++;
//            }// end of inner loop
//            yValue = 0;
//            xValue ++;
//        }// end of outer loop
//        return false;
//    }// end of checkMiddleRows
//
//    /**
//     * This function will go through the bottom row of the grid in order to check if there is
//     * a spot to put a fence in order to create a pen
//     *
//     * @return-- returns true if there is a spot to create a penm
//     */
//    public boolean checkBottomRow(Grid grid){
//        while (yValue <= grid.getX() - 1){
//            if (grid.checkPenAbove(xValue,yValue) == true){
//                grid.setFenceX(xValue,yValue,color);
//                return true;
//            }else
//                yValue ++;
//        }// end of while
//        return false;
//    }// end of checkBottomRow
//
//    /**
//     *
//     * @return
//     */
//    public boolean checkTopCol(Grid grid){
//        xValue = 0;
//        yValue = 0;
//
//        while (xValue <= grid.getY()){
//            if (grid.checkPenRight(xValue,yValue) == true){
//                grid.setFenceX(xValue,yValue,color);
//                return true;
//            }else
//                xValue ++;
//        }// end of while
//        return false;
//    }// end of checkTopCol
//
//    /**
//     *
//     * @return
//     */
//    public boolean checkMiddleCol(Grid grid){
//        yValue ++;
//        xValue = 0;
//        while (yValue <= grid.getY()){
//            while (xValue <= grid.getX() - 1){
//                if (grid.checkPenRight(xValue,yValue) == true ||
//                        grid.checkPenLeft(xValue,yValue) == true) {
//                    grid.setFenceX(xValue,yValue,color);
//                    return true;
//                }else
//                    xValue ++;
//            }// end of inner loop
//            yValue ++;
//            xValue = 0;
//        }// end of outer loop
//        return false;
//    }// end of checkMiddleCol
//
//    /**
//     *
//     * @return
//     */
//    public boolean checkBottomCol(Grid grid){
//        while (xValue <= grid.getY()){
//            if (grid.checkPenLeft(xValue,yValue) == true){
//                grid.setFenceX(xValue,yValue,color);
//                return true;
//            }else
//                xValue ++;
//        }// end of while
//        return false;
//    }// end of checkBottomCol
//}// end of AI class
//
