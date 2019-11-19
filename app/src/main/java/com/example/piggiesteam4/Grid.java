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
     *
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

        else{
            return false;

    }//boolean

}//grid
