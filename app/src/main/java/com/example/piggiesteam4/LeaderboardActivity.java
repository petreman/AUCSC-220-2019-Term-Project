package com.example.piggiesteam4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
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
        resetText();
        setPlayerNames(scores);
        setPlayerScores(scores);
        swapGridSize();
        selectText();
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
                player5.setText(nameP5);
            case 4:
                String nameP4 = scores.get(3).getName();
                player4.setText(nameP4);
            case 3:
                String nameP3 = scores.get(2).getName();
                player3.setText(nameP3);
            case 2:
                String nameP2 = scores.get(1).getName();
                player2.setText(nameP2);
            case 1:
                String nameP1 = scores.get(0).getName();
                player1.setText(nameP1);
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
        if (counter == 2){
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
            counter = 2;
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
     * Swaps the text specifying which scores are being viewed.
     */
    private void swapGridSize() {
        TextView gridSize = (TextView) findViewById(R.id.leaderboardGridSize);
        switch (counter){
            case 0:
                gridSize.setText("5x5");
                break;
            case 1:
                gridSize.setText("6x6");
                break;
            case 2:
                gridSize.setText("7x7");
        }//switch
    }//swapGridSize

    /**
     * Resets the current scores being viewed. It does not save the reset, and must be confirmed.
     * @param v
     */
    public void resetCurrentScores(View v){
        HighScores.resetScores(counter);
        resetText();
    }//resetCurrentScores

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
     * Selects the text after a short delay to enable marquee effect.
     */
    public void selectText(){
        Handler handler = new Handler();

        ((TextView) findViewById(R.id.leaderboardP1Name)).setSelected(false);
        ((TextView) findViewById(R.id.leaderboardP2Name)).setSelected(false);
        ((TextView) findViewById(R.id.leaderboardP3Name)).setSelected(false);
        ((TextView) findViewById(R.id.leaderboardP4Name)).setSelected(false);
        ((TextView) findViewById(R.id.leaderboardP5Name)).setSelected(false);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((TextView) findViewById(R.id.leaderboardP1Name)).setSelected(true);
                ((TextView) findViewById(R.id.leaderboardP2Name)).setSelected(true);
                ((TextView) findViewById(R.id.leaderboardP3Name)).setSelected(true);
                ((TextView) findViewById(R.id.leaderboardP4Name)).setSelected(true);
                ((TextView) findViewById(R.id.leaderboardP5Name)).setSelected(true);
            }//run
        }, 500);//Runnable

    }//selectText

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