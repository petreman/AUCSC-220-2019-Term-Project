package com.example.piggiesteam4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScores {

    private static class Score implements Comparable{
        private String name;
        private int score;

        /**
         * Constructor.
         */
        Score(String name, int score){
            this.name = name;
            this.score = score;
        }//constructor

        /**
         * Gets the player's name.
         * @return the player's name
         */
        public String getName() {
            return name;
        }//getName

        /**
         * Sets the player's name.
         * @param name the player's name.
         */
        public void setName(String name) {
            this.name = name;
        }//setName

        /**
         * Gets the player's score.
         * @return the player's score.
         */
        public int getScore() {
            return score;
        }//getScore

        /**
         * Sets the player's score.
         * @param score the player's score.
         */
        public void setScore(int score) {
            this.score = score;
        }//setScore

        /**
         * Compares this Score to another Score.
         * @param otherPlayer
         * @return whether greater, equal, or lesser.
         */
        @Override
        public int compareTo(Object otherPlayer) {
            int otherScore = ((Score)otherPlayer).getScore();
            return otherScore - this.score;
        }//compareTo
    }//Score

    private List<Score> smallestGrid; //change names when specific size finalized
    private List<Score> smallGrid;
    private List<Score> mediumGrid;
    private List<Score> largeGrid;
    private List<Score> largestGrid;
    private final int SMALLEST = 1; //change these ints according to grid size later
    private final int SMALL = 2;    //possibly change to int[2]. 0 is x and 1 is y instead of using area
    private final int MEDIUM = 3;
    private final int LARGE = 4;
    private final int LARGEST = 5;

    /**
     * Constructor.
     */
    HighScores(){
        clear();
    }//constructor

    /**
     * Resets the scores of a particular grid size
     * @param counter determines which grid size's high scores the user is looking at.
     * @return success.
     */
    public boolean resetScores(int counter){ //counter in leaderboard activity class. should start at 0 each time and after
                                             //calling any method with it as a parameter, it should be incremented by 1
        //int size = grid.getSize(); //change later based on how size specified || this should no longer be used
        //switch statement
        switch (counter){
            case 0:
                smallestGrid.clear();
                break;
            case 1:
                smallGrid.clear();
                break;
            case 2:
                mediumGrid.clear();
                break;
            case 3:
                largeGrid.clear();
                break;
            case 4:
                largestGrid.clear();
                break;
        }//switch
        return true;
    }//resetScores

    /**
     * Clears the scores of all grid sizes.
     * Done by creating new ArrayLists, also used in the constructor.
     * @return success.
     */
    public boolean clear(){
        //maybe use List.clear() instead?
        smallestGrid = new ArrayList<Score>();
        smallGrid = new ArrayList<Score>();
        mediumGrid = new ArrayList<Score>();
        largeGrid = new ArrayList<Score>();
        largestGrid = new ArrayList<Score>();
        return true;
    }//clear

    /**
     * Sorts a List of Scores according to their numerical score (int score in class Score).
     * @param gridScores the List of scores to be sorted.
     * @return success.
     */
    public boolean sort(List<Score> gridScores){
        Collections.sort(gridScores);
        return true;
    }//sort

    /**
     * Gets the highest score for a particular grid size.
     * @param grid the grid size whose score are being retrieved.
     * @return the Score.
     */
    public Score getHighScores(Grid grid){ //this method for getting highest score to show on main screen
        //may change based on how getting grid size works
        //via getX and getY !warning! this does not get the box dimensions, it gets the fence dimensions.
        int size = ;
        //switch to get highscores for a grid
        switch (size){
            case SMALLEST:
                return smallestGrid.get(0);
            case SMALL:
                return smallGrid.get(0);
            case MEDIUM:
                return mediumGrid.get(0);
            case LARGE:
                return largeGrid.get(0);
            case LARGEST:
                return largestGrid.get(0);

        }//switch
        return null; //this should never happen
    }//getHighScores

    //this method scrolls through the high scores for showing on the leaderboard screen
    /**
     * Gets the list of scores to be displayed on the leaderboard screen.
     * @param counter which grid size's scores are to be displayed.
     * @return the list of scores to be displayed.
     */
    public List<Score> getHighScores(int counter){ //possibly change name of method
        //int counter for scrolling starts at 0 end at 4 and loops back in the activity class itself
        switch (counter){
            case 0:
                return smallestGrid;
            case 1:
                return smallGrid;
            case 2:
                return mediumGrid;
            case 3:
                return largeGrid;
            case 4:
                return largestGrid;
        }//switch
        return null; //this should never happen
    }//getHighScores

    /**
     * This adds a new high score to the corresponding leaderboard for the grid size.
     * @param name the name of the player.
     * @param score the score of the player.
     * @param grid the grid they played on.
     * @return success.
     */
    public boolean addHighScore(String name, int score, Grid grid){
        Score highscore = new Score(name, score);
        int size = ;//size via grid getX getY note doesn't get dimensions of box only of points
        switch (size){
            case SMALLEST:
                smallestGrid.add(highscore);
                sort(smallestGrid);
                return true;
            case SMALL:
                smallGrid.add(highscore);
                sort(smallGrid);
                return true;
            case MEDIUM:
                mediumGrid.add(highscore);
                sort(mediumGrid);
                return true;
            case LARGE:
                largeGrid.add(highscore);
                sort(largeGrid);
                return true;
            case LARGEST:
                largestGrid.add(highscore);
                sort(largestGrid);
                return true;
        }//switch
        return false; //this should never happen
    }//addHighScore
}//HighScore
