package com.example.piggiesteam4;

import org.junit.Test;

import static org.junit.Assert.*;

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