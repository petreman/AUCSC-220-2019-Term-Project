/**
 * AUCSC 220
 * PiggiesTeam4
 *
 * Player.java
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * Created by Arnold Gihozo
 * Started on: November 14, 2019
 * Finished on: November 23, 2019
 */




package com.example.piggiesteam4;

import android.graphics.Color;

public class Player {

    private int color;
    private boolean turn;
    private int score;




    Player(int playerColor){
        this.turn = false;
        this.score = 0;

    }// end of player

    /**
     *
     * The addScore method will take the current play score and updates the total value.
     * @param playerScore- current playScore
     */
    public void addScore (int playerScore){
        score = score + playerScore;

    }// end of addScore

    /**
     * This method will clear the score of the player in the current game
     */
    public void clearScore (){
        score = 0;
    }// end of clearScore

    /**
     * This method would return whether it is the player's turn or not.
     * @return- returns whether it is the player's turn (true or false)
     */
    public boolean getTurn(){
       return turn;
    }// end of myTurn

    /**
     * This method will set the player's turn.
     */
    public void setTurn(){
        if (turn == false)
             turn = true;
        turn = false;

    }// end of setTurn

    /**
     * This function will get the player's score
     * @return-- it will return the player's score
     */
    public int getScore(){
        return score;

    }// end of getScore

    /**
     * This function will set the player's color
     * @param colorWanted-- it will take the colorwanted and updates the player's color
     */
    public void setColor(int colorWanted){
        color = colorWanted;
    }// end of setColor

    /**
     * This function will return the player's color
     * @return -- will return the current color of the player
     */
    public int getColor(){
        return color;

    }// end of getColor

    /**
     * This method will end the current player's turn(set to false)
     * and set's the other player's turn to True
     * @param otherPlayer-- next player
     */
    public void endTurn(Player otherPlayer){
        this.turn = false;
        otherPlayer.setTurn();
    }// end of endTurn


}// end of player
