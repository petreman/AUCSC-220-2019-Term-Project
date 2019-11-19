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
        for (int i = 0 ; i < x - 1 ; i++){
            for (int j = 0 ; j < y ; j++){
                this.xCoords[i][j].setExistence(false);
            }//for
        }//for

        for (int i = 0 ; i < x ; i++){
            for (int j = 0 ; j < y - 1 ; j++){
                this.yCoords[i][j].setExistence(false);
            }//for
        }//for

    }//clearGrid

    /**
     * Tries to place a horizontal fence at the specified grid location
     * Need to index into xCoords to get correct location
     *
     * @param x - row coord into xCoords
     * @param y - column coord into xCoords
     * @param color - color to set the fence to
     * @return - true if placed successfully,
     *           false if fence already exits at that spot
     * TODO add player as a parameter!
     */
    boolean setFenceX(int x, int y, int color){

        if (this.xCoords[x][y].exists() == false){
            this.xCoords[x][y].setExistence(true);
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
     * @param x - row coord into xCoords
     * @param y - column coord into xCoords
     * @param color - color to set the fence to
     * @return - true if placed successfully,
     *           false if fence already exits at that spot
     * TODO add player as a parameter!
     */
    boolean setFenceY(int x, int y, int color){

        if (this.yCoords[x][y].exists() == false){
            this.yCoords[x][y].setExistence(true);
            //this.yCoords[x][y].setColor(color);
            return true;
        }//if

        else {
            return false;
        }//else

    }//setFenceY

    /**
     * Checks for a completed pen starting by checking the horizontal fences.
     * Intended to called after successfully placing a horizontal fence.
     * For vertical fences, it's safest to use completedPenY.
     *
     * The fence at the coordinates into xCoords are assumed to be for a fence that exists
     *
     * @param xIn - row coord into xCoords
     * @param yIn - column coord into xCoords
     * @return - true if a pen is completed, false otherwise
     */
    boolean checkPenBelow(int xIn, int yIn){

        if (this.xCoords[xIn+1][yIn].exists() && this.yCoords[xIn][yIn].exists() &&
                this.yCoords[xIn][yIn + 1].exists()){
            return true;
        }//if

        else{
            return false;
        }//else

    }//checkPenBelow

    /**
     * Checks for a completed pen starting by checking the horizontal fences.
     * Intended to called after successfully placing a horizontal fence.
     * For vertical fences, it's safest to use completedPenY.
     *
     * The fence at the coordinates into xCoords are assumed to be for a fence that exists
     *
     * @param xIn - row coord into xCoords
     * @param yIn - column coord into xCoords
     * @return - true if a pen is completed, false otherwise
     */
    boolean checkPenAbove(int xIn, int yIn){

        if (this.xCoords[xIn - 1][yIn].exists() && this.yCoords[xIn - 1][yIn].exists() &&
                this.yCoords[xIn - 1][yIn + 1].exists()){
            return true;
        }//if

        else{
            return false;
        }//else

    }//checkPenAbove

}//grid
