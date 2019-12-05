/**
 * AUCSC 220
 * PiggiesTeam4
 *
 * Game.java
 *
 * Each game is made up of 2 players and a grid. So the game class is a
 * container for a game
 *
 * Methods:
 *  - Game(int size, boolean isMulti, int[] p1Color, int[] p2Color) -> Game
 *      Creates a game with the specified grid size, the type of game, and the
 *      given player colors
 *
 *  - getGrid -> Grid
 *      Returns the grid for the game
 *
 *  - getCurrentPlayer() -> Player
 *      Returns the player who's turn is set to true
 *
 *  - getNonCurrentPlayer() -> Player
 *      Returns the player who's turn it currently isn't
 *
 *  - getPlayer1() -> Player
 *      Returns player 1
 *
 *  - getPlayer2() -> Player
 *      Returns player 2
 *
 *  - isMultiplayer() -> boolean
 *      Returns if the game is multiplayer or not
 *
 *  - toggleCurrentPlayer -> void
 *      Ends the current players turn, and sets the the other players turn to true
 *
 *  - isGameOver() -> boolean
 *      Checks if the game is over or not
 *
 *  - resetGame() -> void
 *      Clears the gird and the player's scores
 *
 * Started November 29, 2019 by Keegan
 *
 * Changelog
 *  2019/11/30: Keegan
 *      Merged Keegan's and Alvin's differing instances of Game.java
 */
package com.example.piggiesteam4;

public class Game {

    private Player player1, player2, currentPlayer;
    //private AI AI;
    private Grid grid;
    private boolean multiplayer = false;

    /**
     * Creates a game with the specified grid size, the type of game, and the
     * given player colors
     *
     * @param size - size of the grid (in dots)
     * @param isMulti - specify if the game is multiplayer or not
     * @param p1Color - player 1's colors
     * @param p2Color - player 2's colors
     */
    Game(int size, boolean isMulti, int[] p1Color, int[] p2Color){

        player1 = new Player(p1Color);
        player2 = new Player(p2Color);

        if (isMulti){
            multiplayer = true;
            //set ai stuff
        }//if

        player1.setTurn();
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

    /**
     * By Keegan
     * @return - the grid for the game
     */
    public Grid getGrid(){
        return this.grid;
    }//getGrid

    /**
     * By Keegan
     * @return - the game's current player (player who's turn it currently is)
     */
    public Player getCurrentPlayer(){
        return currentPlayer;
    }//getCurrentPlayer

    /**
     * By Keegan
     * @return - the game's waiting player (player who's turn it currently isn't)
     */
    public Player getNonCurrentPlayer(){

        if (player1 == currentPlayer){
            return player2;
        }//if

        return player1;

    }//getNonCurrentPlayer

    /**
     * By Keegan
     * @return - the game's first player
     */
    public Player getPlayer1(){
        return player1;
    }//getPlayer1

    /**
     * By Keegan
     * @return - the game's second player
     */
    public Player getPlayer2(){
        return player2;
    }//getPlayer2

    /**
     * By Keegan
     * @return - if the game is multiplayer or not
     */
    public boolean isMultiplayer(){
        return this.multiplayer;
    }//isMultiplayer

    /**
     * Swaps who the current player is (ending and starting turns)
     * By Keegan
     */
    void toggleCurrentPlayer(){

        if (currentPlayer == player1){
            currentPlayer = player2;
        }//if

        else{
            currentPlayer = player1;
        }//else

        player1.setTurn();
        player2.setTurn();

    }//toggleCurrentPlayer

    /**
     * Checks if the game is over.
     * @return whether the game is over.
     */
    public boolean isGameOver(){
        int size = (grid.getX() - 1) * (grid.getY() - 1);
        int totalScore = player1.getScore() + player2.getScore();
        if (totalScore == size){
            return true;
        }//if
        return false;
    }//isGameOver

    /**
     * Indicates if the game is over or not.
     * If it is, then then next event needed can happen
     *
     * By Alvin
     */
    public void endGame(String name){
        HighScores.Score highscore = getHighscore(name);
        HighScores.addHighScore(highscore, grid);
        resetGame();
    }//endGame

    /**
     * Same as above, only for ties.
     */
    public void endGame(){
        resetGame();
    }

    /**
     * Resets the game, clearing scores and the grid.
     *
     * By Alvin
     */
    public void resetGame(){
        player1.clearScore();
        player2.clearScore();
        grid.clearGrid();
    }//resetGame

    /**
     * Updates the fence colors displayed.
     * @param newColor - the new color
     * @param oldColor - the color to be replaced
     *
     * By Alvin
     */
    public void updateFenceColors(int newColor, int oldColor){
        this.getGrid().updateColors(newColor, oldColor);
    }//updateFenceColors

    /**
     * Gets the highscore and puts it in Score object.
     *
     * By Alvin
     *
     * @param name the name of the winner;
     * @return the Score object.
     */
    public HighScores.Score getHighscore(String name){
        int p1Score = player1.getScore();
        int p2Score = player2.getScore();

        if (p1Score > p2Score){
            return new HighScores.Score(name, p1Score);
        }//if

        else{
            return new HighScores.Score(name, p2Score);
        }//else

    }//getHighscore

}//Game
