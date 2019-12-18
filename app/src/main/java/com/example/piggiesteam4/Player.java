/**
 * AUCSC 220
 * PiggiesTeam4
 *
 * Player.java
 *
 * The player class contains all the functions for the AI and Mutliplayer game. It contains
 * functions that assigns player's turn and gives the player the opportunity to change their
 * colors
 *
 * Created by Arnold Gihozo
 * Started on: November 14, 2019
 * Finished on: November 23, 2019
 *
 * Changelog:
 *  - 2019/11/30: Keegan
 *      Changed the color field from int to int[2], so a lighter shade
 *      color can be stored as well. Lighter color used for indicated
 *      when player turn is over
 */
package com.example.piggiesteam4;

import android.util.Log;

public class Player {

    private int color;
    private int colorLight;
    private boolean turn;
    private int score;
    private boolean isCPU;

    /**
     * Constructor
     * @param playerColor-- the color of the player
     */
    Player(int[] playerColor){
        this.turn = false;
        this.score = 0;
        this.color = playerColor[0];
        this.colorLight = playerColor[1];

    }// end of player


    /**
     * Updates the current player score from a given value.
     *
     * @param playerScore- current playScore
     */
    void addScore(int playerScore){
        score = score + playerScore;
        Log.d("addScore", "Score added to player " + whichPlayer);
    }// end of addScore

    /**
     * Clears the score of the player in the current game
     */
    public void clearScore (){
        score = 0;
    }// end of clearScore

    /**
     * Returns whether it is the player's turn or not.
     *
     * @return- returns boolean whether it is the player's turn (true or false)
     */
    public boolean getTurn(){
       return turn;
    }// end of myTurn

    /**
     * Sets the player's turn.
     */
    public void setTurn(){
        if (turn == false){
            turn = true;
        }
        else if (turn == true){
            turn = false;
        }
    }// end of setTurn

    /**
     * Gets the player's score
     *
     * @return-- it will return the player's score
     */
    public int getScore(){
        return score;

    }// end of getScore

    /**
     * Sets the new player's color to the color wanted
     *
     * @param colorWanted-- it will take the color wanted and updates the player's color
     */
    public void setColor(int colorWanted){
        color = colorWanted;
    }// end of setColor

    /**
     * Returns the player's color
     *
     * @return -- will return the current color of the player
     */
    public int getColor(){
        return color;
    }// end of getColor

    /**
     * Returns the player's complementatry lighter color
     *
     * By Keegan
     *
     * @return - returns the light color
     */
    public int getColorLight() {
        return colorLight;
    }//getColorLight

    /**
     * Ends the current player's turn(set to false) and set's the other player's turn to True
     *
     * @param otherPlayer-- next player
     */
    public void endTurn(Player otherPlayer){
        this.turn = false;
        otherPlayer.setTurn();
    }// end of endTurn

    /**
     * Marks the provided player as a CPU player
     *
     * By Keegan
     */
    public void makeCPU(){
        this.isCPU = true;
    }//makeCPU

    public boolean isCPU() {
        return isCPU;
    }

    /**
     * The function the CPU uses to place fences. If it makes a pen, true is returned
     *
     * By Keegan
     *
     * @param game - the game the CPU is playing in
     * @return true if a pen was made, false otherwise
     */
    public boolean placeFenceCPU(Game game){

        if (checkForPossiblePen(game.getGrid()) == false){
            choosePositionEmptyGrid(game.getGrid());
            game.toggleCurrentPlayer();
            return false;
        }//if

        return true;

    }//placeFenceCPU


    /**
     * Scans through the entire board in order to check for possible pens it can make.
     * If a pen can be made, it call place fence function, which places a fence in the area that
     * the pen can be made.
     *
     * @param grid-- takes in the Grid as the parameter, as it scans through the entire grid.
     * @return -- function returns true if a pen was made, otherwise false if no pen was made.
     */
    public boolean checkForPossiblePen(Grid grid){

        //check horizontal fences
        for (int row = 0 ; row < grid.getX(); row++){
            for (int col = 0 ; col < grid.getY() - 1; col++){
                Log.d("penCheck", "row is " + row + " ... col is " + col);
                if (row != 0 && grid.checkPenAbove(row, col, this)){
                    return true;
                }//else
                 else if (row != grid.getX() - 1 && grid.checkPenBelow(row, col, this)){
                    return true;
                }//else if
            }//for
        }//for

        //check vertical fences
        for (int row = 0 ; row < grid.getX() - 1; row++){
            for (int col = 0 ; col < grid.getY(); col++){
                Log.d("penCheck", "row is " + row + " ... col is " + col);
                if (col != 0 && grid.checkPenLeft(row, col, this)){
                    return true;
                }//else
                else if (col != grid.getX() - 1 && grid.checkPenRight(row, col, this)){
                    return true;
                }//else if
            }//for
        }//for
        return false;

    }//checkForPossibleFencePlacement

    /**
     * Sets a fence if the grid is empty or if no pen can be made based on the fence already
     * placed on the grid. This function will recursively call itself until an empty spot is
     * found on the grid.
     *
     * @param grid-- takes in a grid as a parameter
     */
    public void choosePositionEmptyGrid(Grid grid){

        int firstNumber;
        int secondNumber;
        int timeoutCounter = 0;

        while (true) {
            int orientation = (int) (Math.random() * 2);
            System.out.println("aaa");

            if (orientation == 0) {
                firstNumber = (int) (Math.random() * (grid.getX()));
                secondNumber = (int) (Math.random() * (grid.getY() - 1));
                if (grid.setFenceX(firstNumber, secondNumber, color) == true) {
                    return;
                }//if
            }
            else{
                firstNumber = (int) (Math.random() * (grid.getX()) - 1);
                secondNumber = (int) (Math.random() * (grid.getY()));
                if (grid.setFenceY(firstNumber, secondNumber, color) == true) {
                    return;
                }//if
            }

        }//end of while loop
    }//end of choosePositionEmptyGrid

    private int whichPlayer;

    public void setWhichplayer(int whichplayer){
        if((this.whichPlayer == 0) || this.whichPlayer == whichplayer){
            this.whichPlayer = whichplayer;
        }
        else{
            throw new AssertionError("whichplayer has already been set, it cannot be altered to a different value");
        }
    }

    public int getWhichplayer() {
        return whichPlayer;
    }
}// end of Player
