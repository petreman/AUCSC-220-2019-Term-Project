package com.example.piggiesteam4;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PiggiesTeam4Test {
    /**
     * Alvin testing.
     */
    @Test
    public void playerTest(){
        Player player = new Player(new int[] {0,1});
        assertEquals(player.getColor(), 0);
        assertEquals(player.getColorLight(), 1);
        player.makeCPU();
        assertEquals(player.isCPU(), true);
        player.setWhichplayer(1);
        assertEquals(player.getWhichplayer(), 1);
    }

    /**
     * Keegan
     *
     * A simple test to check if score is added to an empty high score list,
     * it's the first and only item in the list
     */
    @Test
    public void highscoreAddTest() {

        Grid grid = new Grid(5, 5);
        HighScores hs = new HighScores();

        HighScores.Score myScore = new HighScores.Score("Keegan", 10);

        hs.addHighScore(myScore, grid);

        //0 is for the smallest gameboard, which is 5
        List<HighScores.Score> gottonScores = HighScores.getHighScores(0);

        assertEquals(myScore, gottonScores.get(0));

    }//highscoreAddTest

    }// playerTest

    /**
     * Arnold Testing
     */
    @Test
    public void testPossibleScoreNumbers(){
        Player player = new Player(new int[] {0,1});
        player.addScore(1); // for small numbers
        assertEquals(player.getScore(),1);
        player.addScore(600000000);// big number
        player.addScore(5000);
        assertEquals(player.getScore(),600005001);
        player.clearScore(); // testing if it clears the score
        assertEquals(player.getScore(),0);
        player.addScore(-500); // test for negative numbers
        assertEquals(player.getScore(),0);

    }// testPossibleScoreNumbers
}