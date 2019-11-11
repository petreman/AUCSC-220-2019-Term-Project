package com.example.piggiesteam4;

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
        }

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
}//HighScore
