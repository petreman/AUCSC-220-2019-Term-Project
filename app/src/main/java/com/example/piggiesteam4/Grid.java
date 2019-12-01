/**
 * AUCSC 220
 * PiggiesTeam4
 *
 * Grid.java
 *
 * Used to store the state of the game and update it as appropriate
 *
 * Has an inner class Fence, which is just the data need to represent a fence on
 * the gameboard. Every grid is made up of Fence objects
 *
 * Fence Methods:
 *  - Fence() -> Fence
 *      Creates a fence with default values.
 *
 *  - setColor(String Color) -> void
 *      Sets the color of the fence from a 6 digit hex string (ie "FFFFFF")
 *
 *  - setColor(int Color) -> void
 *      Sets the color of the fence from the provided integer
 *      Use Color.parseColor("FFFFFF") to convert "FFFFFF" to int
 *
 *  - exists() -> boolean
 *      Returns if the fence "exists" of not (ie has been placed)
 *
 *  - setExistence(boolean exists) -> void
 *      Sets the existence of the Fence to whatever boolean is provided
 *
 * Grid Methods:
 *  - Grid(int x, int y) -> Grid
 *      Constructor. Creates structure for gameboard of size x*y dots and appropriate
 *      number of fences. Fences stored in two arrays: one for horizontal, the other
 *      for vertical
 *
 *  - clearGrid() -> void
 *      Sets every fences existance in the grid to "false"
 *
 *  - getX() -> int
 *      Returns the horizontal dimension of dots for the grid
 *
 *  - getY() -> int
 *      Returns the vertical dimension of dots for the grid
 *
 *  - updateColors(player Player)-> void
 *      To be called wehn a player changes their color. Iterates over the grid
 *      to update any colors of fences placed by the parameter player
 *
 *  - setFenceX(int row, int col, player Player) -> boolean
 *      Attempts to set the existence of the fence located at [row][col]
 *      of xCoords to true (if false). Sets the fence's placement ownership to
 *      the provided player. Returns true if successful, false if fence already
 *      exists at location.
 *
 *  - setFenceY(int row, int col, player Player) -> boolean
 *      Attempts to set the existence of the fence located at [row][col]
 *      of yCoords to true (if false). Sets the fence's placement ownership to
 *      the provided playerReturns true if successful, false if fence already
 *      exists at location.
 *
 *  - checkPenBelow(int row, int col) -> boolean
 *      Checks for a potentially completed pen below the provided xCoords horizontal
 *      fence coordinate. Returns true pen is completed, false otherwise
 *
 *  - checkPenAbove(int row, int col) -> boolean
 *      Checks for a potentially completed pen above the provided xCoords horizontal
 *      fence coordinate. Returns true pen is completed, false otherwise
 *
 *  - checkPenLeft(int row, int col) -> boolean
 *      Checks for a potentially completed pen to the left of the provided
 *      yCoords vertical fence coordinate.
 *      Returns true pen is completed, false otherwise
 *
 *  - checkPenRight(int row, int col) -> boolean
 *      Checks for a potentially completed pen to the right of the provided
 *      yCoords vertical fence coordinate.
 *      Returns true pen is completed, false otherwise
 *
 * Started November 17, 2019 by Keegan
 * First push to master on
 *
 * Changelog:
 *  11/28/19 - Added getters for fence existence
 */

package com.example.piggiesteam4;

import android.graphics.Color;

public class Grid {

    /**
     * Each grid is made up of arrays of fences.
     * Each fence has a color, status of placement, and record of what player placed it
     */
    private class Fence {

        //data
        private boolean exists;
        private int color;
        //private player Player;

        /**
         * Constructor for Fences.
         * Sets existence to false, and default color as a safeguard
         */
        Fence(){
            this.exists = false;
            this.color = Color.BLACK;
        }//constructor

        /**
         * Changes the fence's color from 6 digit hex string
         * @param color - 6 digit hex string of new color
         */
        void setColor(String color){
            this.color = Color.parseColor(color);
        }//setColor

        /**
         * Changes the fences color from the provided int
         * @param color - int representing color to change to
         */
        void setColor(int color){
            this.color = color;
        }//setColor

        /**
         * @return - if the fence has been placed or not
         */
        boolean exists(){
            return this.exists;
        }//exists

        /**
         * Sets the fence to be placed or removed
         * @param existence - true to palce, false to remove
         */
        void setExistence(boolean existence){
            this.exists = existence;
        }//setExistence

    }//fence

    //data
    private Fence[][] xCoords; //all horizontal fences
    private Fence[][] yCoords; //all vertical fences
    private int x, y; //dimensions of dots for grid
    private boolean[][] pens;

    /**
     * Grid constructor. Creates data structure for the grid of dots
     * provided by the x and y values
     *
     * @param x - number of horizontal dots
     * @param y - number of vertical dots
     */
    Grid(int x, int y){

        this.xCoords = new Fence[x][y - 1];
        this.yCoords = new Fence[x - 1][y];
        this.pens = new boolean[x - 1][y - 1];
        this.x = x;
        this.y = y;

        //initialize xCoords
        for (int i = 0 ; i < x ; i++){
            for (int j = 0 ; j < y - 1 ; j++){
                this.xCoords[i][j] = new Fence();
            }//for
        }//for

        //initialize yCoords
        for (int i = 0 ; i < x - 1 ; i++){
            for (int j = 0 ; j < y ; j++){
                this.yCoords[i][j] = new Fence();
            }//for
        }//for

        //initialize pens
        for (int i = 0 ; i < x - 1 ; i++){
            for (int j = 0 ; j < y - 1 ; j++){
                this.pens[i][j] = false;
            }//for
        }//for

    }//constructor

    /**
     * Sets every fence's existence in the grid to false
     */
    void clearGrid(){

        for (int i = 0 ; i < this.x ; i++){
            for (int j = 0 ; j < y - 1; j++){
                this.xCoords[i][j].setExistence(false);
            }//for
        }//for

        for (int i = 0 ; i < this.x - 1 ; i++){
            for (int j = 0 ; j < y ; j++){
                this.yCoords[i][j].setExistence(false);
            }//for
        }//for

    }//clearGrid

    /**
     * @return - the x dimension of dots for the grid
     */
    int getX(){
        return this.x;
    }//xDimension

    /**
     * @return - the y dimension of dots for the grid
     */
    int getY(){
        return this.y;
    }//yDimension

    /**
     * Returns if a horizontal fence at the specified location has been place.
     * Needed for button listeners on grid fragments for fences placed by the
     * AI: if a fences existence changes without a button press, the buttons
     * visibility and color still need to be changed
     *
     * @param row - row coord into xCoords
     * @param col - column coord into xCoords
     * @return - true if fence is placed at coordinates,
     *           false otherwise
     */
    boolean getExistenceX(int row, int col){
        return this.xCoords[row][col].exists();
    }//getExistenceX

    /**
     * Returns if a vertical fence at the specified location has been place.
     * Needed for button listeners on grid fragments for fences placed by the
     * AI: if a fences existence changes without a button press, the buttons
     * visibility and color still need to be changed
     *
     * @param row - row coord into yCoords
     * @param col - column coord into yCoords
     * @return - true if fence is placed at coordinates,
     *           false otherwise
     */
    boolean getExistenceY(int row, int col){
        return this.yCoords[row][col].exists();
    }//getExistenceY

    boolean getPen(int row, int col){
        return this.pens[row][col];
    }

    /**
     * Tries to place a horizontal fence at the specified grid location
     * Need to index into xCoords to get correct location
     *
     * @param row - row coord into xCoords
     * @param col - column coord into xCoords
     * @param color - color to set the fence to
     * @return - true if placed successfully,
     *           false if fence already exits at that spot
     */
    boolean setFenceX(int row, int col, int color){

        if (this.xCoords[row][col].exists() == false){
            this.xCoords[row][col].setExistence(true);
            this.xCoords[row][col].setColor(color);
            return true;
        }//if

        return false;

    }//setFenceX

    /**
     * Tries to place a vertical fence at the specified grid location in game class
     * Need to index into yCoords to get correct location
     *
     * @param row - row coord into xCoords
     * @param col - column coord into xCoords
     * @param color - color to set the fence to (player.getCurrentPlayerColor())
     * @return - true if placed successfully,
     *           false if fence already exits at that spot
     */
    boolean setFenceY(int row, int col, int color){

        if (this.yCoords[row][col].exists() == false){
            this.yCoords[row][col].setExistence(true);
            this.yCoords[row][col].setColor(color);
            return true;
        }//if

        return false;

    }//setFenceY

    /**
     * Checks for a potentially completed pen below the provided horizontal
     * fence coordinate
     *
     * The fence at the coordinates into xCoords are assumed to be for a fence that exists.
     * However the AI may use this function to help find potential pens
     *
     * @param row - row coord into xCoords
     * @param col - column coord into xCoords
     * @return - true if a pen is completed, false otherwise
     */
    boolean checkPenBelow(int row, int col, Player player){

        if (this.xCoords[row+1][col].exists() &&
                this.yCoords[row][col].exists() &&
                this.yCoords[row][col + 1].exists()){

            player.addScore(1);
            this.pens[row][col] = true;

            return true;

        }//if

        return false;

    }//checkPenBelow

    /**
     * Checks for a potentially completed pen above the provided horizontal
     * fence coordinate
     *
     * The fence at the coordinates into xCoords are assumed to be for a fence that exists.
     * However the AI may use this function to help find potential pens
     *
     * @param row - row coord into xCoords
     * @param col - column coord into xCoords
     * @return - true if a pen is completed, false otherwise
     */
    boolean checkPenAbove(int row, int col, Player player){

        if (this.xCoords[row - 1][col].exists() &&
                this.yCoords[row - 1][col].exists() &&
                this.yCoords[row - 1][col + 1].exists()){

            player.addScore(1);
            this.pens[row - 1][col] = true;
            return true;

        }//if

        return false;

    }//checkPenAbove

    /**
     * Checks for a potentially completed pen to the left of the provided vertical
     * fence coordinate
     *
     * The fence at the coordinates into xCoords are assumed to be for a fence that exists.
     * However the AI may use this function to help find potential pens
     *
     * @param row - row coord into yCoords
     * @param col - column coord into yCoords
     * @return - true if a pen is completed, false otherwise
     */
    boolean checkPenLeft(int row, int col, Player player){

        if (this.yCoords[row][col - 1].exists() &&
                this.xCoords[row][col - 1].exists() &&
                this.xCoords[row + 1][col - 1].exists()){

            player.addScore(1);
            this.pens[row][col - 1] = true;

            return true;

        }//if

        return false;

    }//checkPenLeft

    /**
     * Checks for a potentially completed pen to the right of the provided vertical
     * fence coordinate
     *
     * The fence at the coordinates into xCoords are assumed to be for a fence that exists.
     * However the AI may use this function to help find potential pens
     *
     * @param row - row coord into yCoords
     * @param col - column coord into yCoords
     * @return - true if a pen is completed , false otherwise
     */
    boolean checkPenRight(int row, int col, Player player){

        if (this.yCoords[row][col + 1].exists() &&
                this.xCoords[row][col].exists() &&
                this.xCoords[row + 1][col].exists()){

            player.addScore(1);
            this.pens[row][col] = true;

            return true;

        }//if

        return false;

    }//checkPenRight

}//Grid
