/**
 * AUCSC 220
 * PiggiesTeam4
 *
 * MainActivity.java
 *
 * The main, single screen where all games take place.
 *
 * Currently by default loads a 5x5 single player game.
 * Visible grids are represented by fragments, and are swapped in and out as necessary
 * into a frame layout
 *
 * Methods:
 *  - onCreate
 *
 * Started forever ago by Keegan
 *
 * Changelog
 *  - n/a
 */

package com.example.piggiesteam4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, Grid55Fragment.OnFragmentInteractionListener,
        Grid66Fragment.OnFragmentInteractionListener{

    //Class Variables
    public Game singlePlayer;
    public Game multiPlayer;
    public Game currentGame;
    public Player currentPlayer;
    public Button p1Score;
    public Button p2Score;
    public int[] p1Color;
    public int[] p2Color;

    /**
     * On creation, creates a defualt single player game (5x5 grid)
     * Eventually we want to be able to load from a saved state
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializations
        ImageButton menuButton = findViewById(R.id.hamMenuButton);
        NavigationView menuView = findViewById(R.id.navigationId);
        p1Score = (Button) findViewById(R.id.p1ScoreButton);
        p2Score = (Button) findViewById(R.id.p2ScoreButton);

        //listens for drawer menu items being selected
        menuView.setNavigationItemSelectedListener(this);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawerLayout = findViewById(R.id.drawerId);
                drawerLayout.openDrawer(GravityCompat.START);
            }//onClick
        });

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }//if

            defaultSinglePlayer();

        }//if

    }//onCreate

    /**
     * This basically tells the main activity that is has an options menu
     * (the navigation drawer) and inflates it so that it is visible
     *
     * By Keegan
     *
     * @param menu - Gonna be honest, I don't know
     * @return - true, as the the menu is created on creation of the activity
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu_layout, menu);
        return true;
    }//onCreateOptionsMenu

    /**
     * On selection of the an item in the drawer menu,
     * the corresponding activity is started.
     *
     * By Keegan
     *
     * @param item - the item in the menu that has been selected
     * @return - true, as the selected item's activity should be started
     */
    @Override
    public boolean onNavigationItemSelected(MenuItem item){

        Intent navIntent;

        switch(item.getItemId()) {

            case R.id.nav_leaderboard:
                navIntent = new Intent(this, LeaderboardActivity.class);
                this.startActivity(navIntent);
                break;

            case R.id.nav_grid_size:
                navIntent = new Intent(this, GridSizeActivity.class);
                this.startActivity(navIntent);
                break;

            case R.id.nav_sound:
                navIntent = new Intent(this, SoundActivity.class);
                this.startActivity(navIntent);
                break;

            case R.id.nav_help:
                navIntent = new Intent(this, HelpActivity.class);
                this.startActivity(navIntent);
                break;

            case R.id.nav_about:
                navIntent = new Intent(this, AboutActivity.class);
                this.startActivity(navIntent);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }//switch

        return true;

    }//onNavigationItemSelected

    //Maybe change variable away from class variable.
    String receivedName;
    /**
     * Shows a popup asking for the name of the winner.
     * To be called on game end.
     * Will end the game and get the winner's score and name. May be displayed on leaderboard.
     */
    public void askForName(final Game game){
        NameQueryFragment nameQuery = new NameQueryFragment();
        nameQuery.show(getSupportFragmentManager(), "nameQuery");

        NameQueryFragment.QueryDialogListener listener = new NameQueryFragment.QueryDialogListener() {
            @Override
            public void onDialogPositiveClick(DialogFragment dialog) {
                NameQueryFragment query = (NameQueryFragment) dialog;
                receivedName = query.getName().trim();
                game.endGame(receivedName);
            }//onDialogPositiveClick

            @Override
            public void onDialogNegativeClick(DialogFragment dialog) {
                receivedName = "Anonymous";
                game.endGame(receivedName);
            }//OnDialogNegativeClick
        };//QueryDialogListener
        nameQuery.setListener(listener);
    }//askForName

    //This method is probably not needed.
    //The variable might be better off as not a class variable.
    /**
     * Gets the name of the winner.
     * @return
     */
    public String getName(){
        return receivedName;
    }

    /**
     * Asks for confirmation to reset the game.
     */
    public void askForResetConfirmation(){
        ResetConfirmationFragment confirmation = new ResetConfirmationFragment();
        confirmation.show(getSupportFragmentManager(), "resetConfirmation");

        ResetConfirmationFragment.ResetConfirmationListener listener = new ResetConfirmationFragment.ResetConfirmationListener() {
            @Override
            public void onDialogPositiveClick(Dialog dialog) {
                currentGame.resetGame();
            }//onDialogPositiveClick

            @Override
            public void onDialogNegativeClick(Dialog dialog) {
                ;
            }//onDialogNegativeClick
        };//ResetConfirmationListener
        confirmation.setListener(listener);
    }//askForResetConfirmation

    /**
     * Sets player 1's color locally
     * Also takes it's color's lighter version for un-highlighting
     *
     * Stored as an array of these two colors, as they're heavily associated with each other
     *
     * By Keegan
     *
     * @param color - the main color of the player
     * @param colorLight - a more softer color of the player's main color
     */
    public void setP1Color(int color, int colorLight){
        p1Color = new int[]{ color, colorLight };
    }//setP1Color

    /**
     * Sets player 2's color locally
     * Also takes it's color's lighter version for un-highlighting
     *
     * Stored as an array of these two colors, as they're heavily associated with each other
     *
     * By Keegan
     *
     * @param color - the main color of the player
     * @param colorLight - a more softer color of the player's main color
     */
    public void setP2Color(int color, int colorLight){
        p2Color = new int[]{ color, colorLight };
    }//setP2Color

    /**
     * Sets up a default single player game
     *
     * Defaults are:
     *  - player 1 is red
     *  - player 2 is an AI and brown
     *  - game grid size is 5x5
     *
     *  Intended to be only called on the app's first launch
     *
     *  By Keegan
     */
    public void defaultSinglePlayer(){

        setP1Color(getColor(R.color.red), getColor(R.color.lightRed));
        setP2Color(getColor(R.color.ai), getColor(R.color.aiLight));

        singlePlayer = new Game(55, false, p1Color, p2Color);
        currentGame = singlePlayer;
        currentPlayer = currentGame.getCurrentPlayer();

        Grid55Fragment singlePlayerDefaultFragment = createGrid55Fragment(false);

        p1Score.setText(Integer.toString(currentGame.getPlayer1().getScore()));
        p2Score.setText(Integer.toString(currentGame.getPlayer2().getScore()));

        setScoreButtonColor();

    }//defaultSinglePlayer

    /**
     * Creates a fragment for a 5x5 grid, then inflates it to fragment_container with call
     * to setGrid55Fragment()
     *
     * By Keegan
     *
     * @param isMultiplayer - if the grid being created is for a multiplayer game
     * @return - the created instance of the fragment grid
     */
    public Grid55Fragment createGrid55Fragment(boolean isMultiplayer){

        // Create a new Fragment to be placed in the activity layout
        Grid55Fragment grid55Fragment = new Grid55Fragment();
        setGrid55FragmentListener(grid55Fragment);

        Bundle args = new Bundle();
        args.putBoolean("multiplayer", isMultiplayer);
        grid55Fragment.setArguments(args);

        setGrid55Fragment(grid55Fragment);

        return grid55Fragment;

    }//createGrid55Fragment

    /**
     * Sets the 5x5 grid on the screen by placing it in fragment_container
     * NOT intended to be used to replace fragments in the container!
     *
     * By Keegan
     *
     * @param fragment - the 5x5 grid fragment to display
     */
    public void setGrid55Fragment(Grid55Fragment fragment){

        // Add the fragment to the 'fragment_container' FrameLayout
        androidx.fragment.app.FragmentTransaction fragTrans =
                getSupportFragmentManager().beginTransaction();

        fragTrans.add(R.id.fragment_container, fragment).commit();


    }//setGrid55Fragment

    /**
     * Sets the initial highlighting of the player scores to indicate
     * who's turn is first
     *
     * By Keegan
     */
    public void setScoreButtonColor(){

        if (currentPlayer == currentGame.getPlayer1()){

            p1Score.getBackground().setColorFilter(currentGame.getCurrentPlayer().getColor(),
                    PorterDuff.Mode.MULTIPLY);

            p2Score.getBackground().setColorFilter(currentGame.getNonCurrentPlayer().getColorLight(),
                    PorterDuff.Mode.MULTIPLY);

        }//if

        else{

            p2Score.getBackground().setColorFilter(currentGame.getCurrentPlayer().getColor(),
                    PorterDuff.Mode.MULTIPLY);

            p1Score.getBackground().setColorFilter(currentGame.getNonCurrentPlayer().getColorLight(),
                    PorterDuff.Mode.MULTIPLY);

        }//else

    }//setScoreButtonColor

    /**
     *
     */
    public void toggleCurrentGame(){

    }

    /**
     * Intentionally left blank
     *
     * Needs to be "implemented" for fragments to display
     * and not cause the app to throw an exception
     *
     * By Keegan
     *
     * @param uri - no idea
     */
    @Override
    public void onFragmentInteraction(Uri uri) {
        //empty
    }

    Grid55Fragment.endGameListener listener = new Grid55Fragment.endGameListener() {
        @Override
        public void endGame(Game game) {
            if (game.isGameOver()){
                askForName(game);
                HighScores.save(getApplicationContext());
            }
        }
    };

    /**
     * Sets the listener in Grid55Fragment
     * @param grid the Grid55Fragment.
     */
    public void setGrid55FragmentListener(Grid55Fragment grid){
        grid.setListener(listener);
    }

}//MainActivity
