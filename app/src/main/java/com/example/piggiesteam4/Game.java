package com.example.piggiesteam4;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class Game {
    Player p1;
    Player p2;
    Grid grid;
    boolean isMultiplayer;

    Game(boolean isMultiplayer){
        p1 = new Player();
        if (isMultiplayer){
            p2 = new AI();
        }//if
        else{
            p2 = new Player();
        }//else
        this.isMultiplayer = isMultiplayer;
    }//constructor

    /**
     * Checks if the game is over.
     * @return whether the game is over.
     */
    public boolean isGameOver(){
        int size = (grid.getX() - 1) * (grid.getY() - 1);
        int totalScore = p1.getScore() + p2.getScore();
        if (totalScore == size){
            return true;
        }//if
        return false;
    }//isGameover

    /**
     * Ends the game.
     */
    public void endGame(){
        //get name from popup
        String name = "";
        HighScores.Score highscore = getHighscore(name);
        HighScores.addHighScore(highscore, grid);
        resetGame();
    }//endGame

    /**
     * Resets the game, clearing scores and the grid.
     */
    public void resetGame(){
        p1.clearScore();
        p2.clearScore();
        grid.clearGrid();
    }//resetGame

    /**
     * Updates the fence colors displayed.
     * @param player the player whose color was just changed.
     * @param grid the grid of the game.
     */
    public void updateFenceColors(Player player, Grid grid){
        grid.updateColors(player);
    }//updateFenceColors

    /**
     * Gets the highscore and puts it in Score object.
     * @param name the name of the winner;
     * @return the Score object.
     */
    public HighScores.Score getHighscore(String name){
        int p1Score = p1.getScore();
        int p2Score = p2.getScore();
        if (p1Score>p2Score){
            return new HighScores.Score(name, p1Score);
        }//if
        else{
            return new HighScores.Score(name, p2Score);
        }//else
    }//getHighscore

    /**
     * Saves the current game.
     * @param context from getApplicationContext() called inside activity class.
     */
    public void saveGame(Context context){
        SharedPreferences pref;
        if (isMultiplayer){
            pref = context.getSharedPreferences("single", Context.MODE_PRIVATE);
        }//if
        else{
            pref = context.getSharedPreferences("multi", Context.MODE_PRIVATE);
        }//else
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        String game = gson.toJson(this);
        editor.putString("game", game);
        editor.commit();
    }//saveGame

    /**
     * Retrieves a saved game.
     * @param context from getApplicationContext() called inside activity class.
     * @return Game object, should be used to replace the old instance.
     */
    public Game retrieveGame(Context context){
        SharedPreferences pref;
        if (isMultiplayer) {
            pref = context.getSharedPreferences("single", Context.MODE_PRIVATE);
        }//if
        else{
            pref = context.getSharedPreferences("multi", Context.MODE_PRIVATE);
        }//else
        Gson gson = new Gson();
        String game = pref.getString("game","");
        return gson.fromJson(game, Game.class);
    }//retrieveGame
}//Game
