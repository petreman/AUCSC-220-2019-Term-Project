package com.example.piggiesteam4;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScores {

    public static class Score implements Comparable{
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

        @NonNull
        @Override
        public String toString() {
            return name + ": "+ score;
        }
    }//Score

    private static List<Score> smallestGrid = new ArrayList<Score>(); //change names when specific size finalized
    private static List<Score> smallGrid = new ArrayList<Score>();
    private static List<Score> mediumGrid = new ArrayList<Score>();
    private static List<Score> largeGrid = new ArrayList<Score>();
    private static List<Score> largestGrid = new ArrayList<Score>();
    private static final int SMALLEST = 1; //change these ints according to grid size later
    private static final int SMALL = 2;    //possibly change to int[2]. 0 is x and 1 is y instead of using area
    private static final int MEDIUM = 3;
    private static final int LARGE = 4;
    private static final int LARGEST = 5;

//    /**
//     * Constructor.
//     */
//    HighScores(){
//        clear();
//    }//constructor

    /**
     * Resets the scores of a particular grid size
     * @param counter determines which grid size's high scores the user is looking at.
     * @return success.
     */
    public static boolean resetScores(int counter){ //counter in leaderboard activity class. should start at 0 each time and after
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
    public static boolean clear(){
        smallestGrid.clear();
        smallGrid.clear();
        mediumGrid.clear();
        largeGrid.clear();
        largestGrid.clear();
        return true;
    }//clear

    /**
     * Sorts a List of Scores according to their numerical score (int score in class Score).
     * @param gridScores the List of scores to be sorted.
     * @return success.
     */
    public static boolean sort(List<Score> gridScores){
        Collections.sort(gridScores);
        reduceSize();
        return true;
    }//sort

    /**
     * Gets the highest score for a particular grid size.
     * @param grid the grid size whose score are being retrieved.
     * @return the Score.
     */
    public static Score getHighScores(Grid grid){ //this method for getting highest score to show on main screen
        //may change based on how getting grid size works
        //via getX and getY !warning! this does not get the box dimensions, it gets the fence dimensions.
        int size = 0;
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
    public static List<Score> getHighScores(int counter){ //possibly change name of method
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
    public static boolean addHighScore(String name, int score, Grid grid){
        Score highscore = new Score(name, score);
        return addHighScore(highscore, grid);
    }//addHighScore

    public static boolean addHighScore(Score highscore, Grid grid){
        int size = 0;//size via grid getX getY note doesn't get dimensions of box only of points
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
    }

    /**
     * Reduces the size of the lists of scores down to 5.
     */
    public static void reduceSize(){
        boolean tooLarge = false;
        if (smallestGrid.size() >= 6){
            smallestGrid.remove(5);
            tooLarge = smallestGrid.size() >= 6;
        }
        if (smallGrid.size() >= 6){
            smallGrid.remove(5);
            tooLarge = tooLarge || smallGrid.size() >= 6;
        }
        if (mediumGrid.size() >= 6){
            mediumGrid.remove(5);
            tooLarge = tooLarge || mediumGrid.size() >= 6;
        }
        if (largeGrid.size() >= 6){
            largeGrid.remove(5);
            tooLarge = tooLarge || largeGrid.size() >= 6;
        }
        if (largestGrid.size() >= 6){
            largestGrid.remove(5);
            tooLarge = tooLarge || largestGrid.size() >= 6;
        }
        if (tooLarge){
            reduceSize();
        }
    }

    /**
     * Saves the high score lists.
     * This should be called whenever the leaderboard is changed.
     * Whether a new score is added, or one or all high scores for the grid sizes are removed.
     * @param context from getApplicationContext() called inside activity class.
     * @return success
     */
    public static boolean save(Context context){
        SharedPreferences pref = context.getSharedPreferences("leaderboard", Context.MODE_PRIVATE);
        //Possibly move these strings such as the one above to string resource folder?
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson(); //added implementation 'com.google.code.gson:gson:2.8.5' to gradle
        String smallest = gson.toJson(smallestGrid);
        String small = gson.toJson(smallGrid);
        String medium = gson.toJson(mediumGrid);
        String large = gson.toJson(largeGrid);
        String largest = gson.toJson(largestGrid);
        editor.putString("smallest", smallest);
        editor.putString("small", small);
        editor.putString("medium", medium);
        editor.putString("large", large);
        editor.putString("largest", largest);
        editor.commit();
        return true;
        //may need commit after each putString? also move strings to string resource
        //after tests, it doesn't appear to require multiple commits.
    }//save

    /**
     * Retrieves saved score lists. Should be called when the app is opened.
     * A list should always be able to be retrieved as the variables have an empty list by default.
     * @param context from getApplicationContext() called inside activity class.
     * @return whether saved scores have been retrieved.
     */
    public static boolean retrieveScores(Context context){
        try{
            SharedPreferences pref = context.getSharedPreferences("leaderboard", Context.MODE_PRIVATE);
            Gson gson = new Gson();
            String pendingSmallest = pref.getString("smallest", "");
            String pendingSmall = pref.getString("small", "");
            String pendingMedium = pref.getString("medium", "");
            String pendingLarge = pref.getString("large", "");
            String pendingLargest = pref.getString("largest", "");
            smallestGrid = gson.fromJson(pendingSmallest, List.class);
            smallGrid = gson.fromJson(pendingSmall, List.class);
            mediumGrid = gson.fromJson(pendingMedium, List.class);
            largeGrid = gson.fromJson(pendingLarge, List.class);
            largestGrid = gson.fromJson(pendingLargest, List.class);
            return true;
        }//try
        catch (Exception e){
            return false; //shouldn't happen
        }//catch
    }//retrieveScores
}//HighScore
