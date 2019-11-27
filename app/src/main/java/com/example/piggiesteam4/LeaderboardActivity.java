package com.example.piggiesteam4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class LeaderboardActivity extends AppCompatActivity {

    int counter;
    boolean scoresReset = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        resetText();
        //testset();
        getLeaderboard();
    }//onCreate

    /**
     * Gets the leaderboard and shows it.
     */
    public void getLeaderboard(){
        List<HighScores.Score> scores = HighScores.getHighScores(counter);
        setPlayerNames(scores);
        setPlayerScores(scores);
    }//getLeaderboard

    /**
     * Shows the player names.
     * @param scores the corresponding list.
     */
    public void setPlayerNames(List<HighScores.Score> scores){
        int size = scores.size();
        TextView player1 = (TextView) findViewById(R.id.leaderboardP1Name);
        TextView player2 = (TextView) findViewById(R.id.leaderboardP2Name);
        TextView player3 = (TextView) findViewById(R.id.leaderboardP3Name);
        TextView player4 = (TextView) findViewById(R.id.leaderboardP4Name);
        TextView player5 = (TextView) findViewById(R.id.leaderboardP5Name);
        switch (size) {
            default:
            case 5:
                String nameP5 = scores.get(4).getName();
                player5.setText("5. " + nameP5);
            case 4:
                String nameP4 = scores.get(3).getName();
                player4.setText("4. " + nameP4);
            case 3:
                String nameP3 = scores.get(2).getName();
                player3.setText("3. " + nameP3);
            case 2:
                String nameP2 = scores.get(1).getName();
                player2.setText("2. " + nameP2);
            case 1:
                String nameP1 = scores.get(0).getName();
                player1.setText("1. " + nameP1);
                break;
            case 0:
                player1.setText(R.string.scoreplaceholder);
                player2.setText(R.string.scoreplaceholder);
                player3.setText(R.string.scoreplaceholder);
                player4.setText(R.string.scoreplaceholder);
                player5.setText(R.string.scoreplaceholder);
        }//switch
    }//setPlayerNames

    /**
     * Shows the player scores.
     * @param scores the corresponding list.
     */
    public void setPlayerScores(List<HighScores.Score> scores){
        int size = scores.size();
        TextView player1 = (TextView) findViewById(R.id.P1HighScore);
        TextView player2 = (TextView) findViewById(R.id.P2HighScore);
        TextView player3 = (TextView) findViewById(R.id.P3HighScore);
        TextView player4 = (TextView) findViewById(R.id.P4HighScore);
        TextView player5 = (TextView) findViewById(R.id.P5HighScore);
        switch (size) {
            default:
            case 5:
                int scoreP5 = scores.get(4).getScore();
                player5.setText(scoreP5 + "");
            case 4:
                int scoreP4 = scores.get(3).getScore();
                player4.setText(scoreP4 + "");
            case 3:
                int scoreP3 = scores.get(2).getScore();
                player3.setText(scoreP3 + "");
            case 2:
                int scoreP2 = scores.get(1).getScore();
                player2.setText(scoreP2 + "");
            case 1:
                int scoreP1 = scores.get(0).getScore();
                player1.setText(scoreP1 + "");
                break;
            case 0:
                player1.setText("0");
                player2.setText("0");
                player3.setText("0");
                player4.setText("0");
                player5.setText("0");
        }//switch
    }//setPlayerScores

    /**
     * Gets the next leaderboard and shows it.
     * @param v View.
     */
    public void nextLeaderboard(View v){
        if (counter == 4){
            counter = 0;
            getLeaderboard();
        }//if
        else {
            counter++;
            getLeaderboard();
        }//else
    }//nextLeaderBoard

    /**
     * Gets the previous leaderboard and shows it.
     * @param v View.
     */
    public void previousLeaderboard(View v){
        if (counter == 0){
            counter = 4;
            getLeaderboard();
        }//if
        else {
            counter--;
            getLeaderboard();
        }//if
    }//previousLeaderboard

    /**
     * Resets all scores.
     * @param v View.
     */
    public void resetAllScores(View v){
        HighScores.clear();
        getLeaderboard();
        resetText();
    }//resetAllScores

    /**
     * Resets the text showing player names and scores.
     */
    public void resetText(){
        ((TextView) findViewById(R.id.leaderboardP1Name)).setText(R.string.scoreplaceholder);
        ((TextView) findViewById(R.id.leaderboardP2Name)).setText(R.string.scoreplaceholder);
        ((TextView) findViewById(R.id.leaderboardP3Name)).setText(R.string.scoreplaceholder);
        ((TextView) findViewById(R.id.leaderboardP4Name)).setText(R.string.scoreplaceholder);
        ((TextView) findViewById(R.id.leaderboardP5Name)).setText(R.string.scoreplaceholder);
        ((TextView) findViewById(R.id.P1HighScore)).setText("0");
        ((TextView) findViewById(R.id.P2HighScore)).setText("0");
        ((TextView) findViewById(R.id.P3HighScore)).setText("0");
        ((TextView) findViewById(R.id.P4HighScore)).setText("0");
        ((TextView) findViewById(R.id.P5HighScore)).setText("0");
    }//resetText

    /**
     * Resets the current scores being viewed. It does not save the reset, and must be confirmed.
     * @param v
     */
    public void resetCurrentScores(View v){
        HighScores.resetScores(counter);
        resetText();
    }

    /**
     * Confirms the resetting of scores.
     * @param v View
     */
    public void confirm(View v){
        Context context = getApplicationContext();
        HighScores.save(context);
        getLeaderboard();
    }//confirm

    /**
     * Method for testing, remove later.
     */
    public void testset(View v){
        List<HighScores.Score> a = HighScores.getHighScores(0);
        List<HighScores.Score> b = HighScores.getHighScores(1);
        List<HighScores.Score> c = HighScores.getHighScores(2);
        List<HighScores.Score> d = HighScores.getHighScores(3);
        List<HighScores.Score> e = HighScores.getHighScores(4);
        a.add(new HighScores.Score("a1", 10));
        a.add(new HighScores.Score("a2", 19));
        b.add(new HighScores.Score("b1", 15));
        b.add(new HighScores.Score("b2", 13));
        c.add(new HighScores.Score("c1", 4));
        c.add(new HighScores.Score("c2", 7));
        d.add(new HighScores.Score("d1", 20));
        d.add(new HighScores.Score("d2", 6));
        e.add(new HighScores.Score("e1", 1));
        e.add(new HighScores.Score("e2", 9));
        HighScores.sort(a);
        HighScores.sort(b);
        HighScores.sort(c);
        HighScores.sort(d);
        HighScores.sort(e);
    }//testset
}
