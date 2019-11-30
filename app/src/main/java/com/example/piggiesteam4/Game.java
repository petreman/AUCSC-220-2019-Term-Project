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
        player1.setTurn();
        currentPlayer = player1;

        switch(size){

            case(55):
                grid = new Grid(5, 5);
                player2 = new Player(Color.BLUE);
                break;

            case(66):
                grid = new Grid(6, 6);
                break;

        }//switch

    }//constructor


    public Grid getGrid(){
        return this.grid;
    }

    public int getCurrentPlayerColor(){
        return currentPlayer.getColor();
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    public Player getPlayer1(){
        return player1;
    }

    public Player getPlayer2(){
        return player2;
    }

    public boolean isMultiplayer(){
        return this.multiplayer;
    }

    void toggleCurrentPlayer(){

        if (currentPlayer == player1){
            currentPlayer = player2;
        }//if

        else{
            currentPlayer = player1;
        }
    }

}//Game
