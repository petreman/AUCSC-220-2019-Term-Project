package com.example.piggiesteam4;

import android.graphics.Color;

public class Game {

    private Player player1 = new Player(Color.RED);
    private Player player2, currentPlayer;
    //private AI AI;
    private Grid grid;
    private boolean multiplayer;

    Game(int size, boolean type){

        multiplayer = type;
        currentPlayer = player1;

        switch(size){
            case(55):
                grid = new Grid(5, 5);
                break;

            case(66):
                grid = new Grid(6, 6);
                break;

        }//switch

    }//constructor


    Grid getGrid(){
        return this.grid;
    }

    int getCurrentPlayerColor(){
        return currentPlayer.getColor();
    }



}//Game
