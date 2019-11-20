package com.example.piggiesteam4;

import android.graphics.Color;

public class Grid {

    private class Fence {

        private boolean exists;
        private int color;
        //private player Player;

        //Constructor
        Fence(){
            this.exists = false;
            this.color = Color.BLACK;
        }//constructor

        void setColor(String color){
            this.color = Color.parseColor(color);
        }//setColor

        void setColor(int color){
            this.color = color;
        }//setColor

        boolean exists(){
            return this.exists;
        }//exists

        void setExistence(boolean existence){
            this.exists = existence;
        }//setExistence



    }//fence

    private Fence[][] xCoords;
    private Fence[][] yCoords;
    private int x, y;

    Grid(int x, int y){

        this.xCoords = new Fence[x - 1][y];
        this.yCoords = new Fence[x][y - 1];

        this.x = x;
        this.y = y;

        //initialize xCoords
        for (int i = 0 ; i < x - 1 ; i++){
            for (int j = 0 ; j < y ; j++){
                this.xCoords[i][j] = new Fence();
            }//for
        }//for

        //initialize yCoords
        for (int i = 0 ; i < x ; i++){
            for (int j = 0 ; j < y - 1 ; j++){
                this.yCoords[i][j] = new Fence();
            }//for
        }//for

    }//constructor

    void clearGrid(){

        for (int i = 0 ; i < this.x - 1 ; i++){
            for (int j = 0 ; j < y ; j++){
                this.xCoords[i][j].setExistence(false);
            }//for
        }//for

        for (int i = 0 ; i < this.x ; i++){
            for (int j = 0 ; j < y - 1 ; j++){
                this.yCoords[i][j].setExistence(false);
            }//for
        }//for

    }//clearGrid

    int getX(){
        return this.x;
    }//xDimension

    int getY(){
        return this.y;
    }//yDimension

    /**
     * Tries to place a horizontal fence at the specified grid location
     * Need to index into xCoords to get correct location
     *
     * @param row - row coord into xCoords
     * @param col - column coord into xCoords
     * @param color - color to set the fence to
     * @return - true if placed successfully,
     *           false if fence already exits at that spot
     * TODO add player as a parameter!
     */
    boolean setFenceX(int row, int col, int color){

        if (this.xCoords[row][col].exists() == false){
            this.xCoords[row][col].setExistence(true);
            //this.xCoords[x][y].setColor(color);
            return true;
        }//if

        else {
            return false;
        }//else

    }//setFenceX

    /**
     * Tries to place a vertical fence at the specified grid location in game class
     * Need to index into yCoords to get correct location
     *
     * @param row - row coord into xCoords
     * @param col - column coord into xCoords
     * @param color - color to set the fence to
     * @return - true if placed successfully,
     *           false if fence already exits at that spot
     * TODO add player as a parameter!
     */
    boolean setFenceY(int row, int col, int color){

        if (this.yCoords[row][col].exists() == false){
            this.yCoords[row][col].setExistence(true);
            //this.yCoords[x][y].setColor(color);
            return true;
        }//if

        else {
            return false;
        }//else

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
    boolean checkPenBelow(int row, int col){

        if (this.xCoords[row+1][col].exists() && this.yCoords[row][col].exists() &&
                this.yCoords[row][col + 1].exists()){
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
    boolean checkPenAbove(int row, int col){

        if (this.xCoords[row - 1][col].exists() &&
                this.yCoords[row - 1][col].exists() &&
                this.yCoords[row - 1][col + 1].exists()){
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
    boolean checkPenLeft(int row, int col){

        if (this.yCoords[row][col - 1].exists() &&
                this.xCoords[row][col - 1].exists() &&
                this.xCoords[row + 1][col - 1].exists()){
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
     * @param row
     * @param col
     * @return
     */
    boolean checkPenRight(int row, int col){

        if (this.yCoords[row][col + 1].exists() &&
                this.xCoords[row][col].exists() &&
                this.xCoords[row + 1][col].exists()){
            return true;
        }//if

        return false;

    }//checkPenRight

}//Grid
