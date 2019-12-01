/**
 * AUCSC 220
 * PiggiesTeam4
 *
 * MainActivity.java
 */

package com.example.piggiesteam4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationView;

import static java.lang.Math.sqrt;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private Game singlePlayer;
    private Game multiPlayer;
    private Game currentGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializations
        ImageButton menuButton = findViewById(R.id.hamMenuButton);
        NavigationView menuView = findViewById(R.id.navigationId);

        //listens for drawer menu items being selected
        menuView.setNavigationItemSelectedListener(this);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawerLayout = findViewById(R.id.drawerId);
                drawerLayout.openDrawer(GravityCompat.START);
            }//onClick
        });

    //askForName();
    askForResetConfirmation();
    }//onCreate

    /**
     * This basically tells the main activity that is has an options menu
     * (the navigation drawer)
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

    /**
     * Shows a popup asking for the name of the winner.
     * To be called on game end.
     * @return the name of the winner, or anonymous.
     */
    public String askForName(){
        NameQueryFragment nameQuery = new NameQueryFragment();
        nameQuery.show(getSupportFragmentManager(), "nameQuery");
        final String[] name = new String[1];

        NameQueryFragment.QueryDialogListener listener = new NameQueryFragment.QueryDialogListener() {
            @Override
            public void onDialogPositiveClick(DialogFragment dialog) {
                NameQueryFragment query = (NameQueryFragment) dialog;
                name[0] = query.getName();
            }//onDialogPositiveClick

            @Override
            public void onDialogNegativeClick(DialogFragment dialog) {
                name[0] = "Anonymous";
            }//OnDialogNegativeClick
        };//QueryDialogListener
        nameQuery.setListener(listener);
        return name[0];
    }//askForName

    public void askForResetConfirmation(){
        ResetConfirmationFragment confirmation = new ResetConfirmationFragment();
        confirmation.show(getSupportFragmentManager(), "resetConfirmation");

        ResetConfirmationFragment.ResetConfirmationListener listener = new ResetConfirmationFragment.ResetConfirmationListener() {
            @Override
            public void onDialogPositiveClick(Dialog dialog) {
                game.resetGame();
            }//onDialogPositiveClick

            @Override
            public void onDialogNegativeClick(Dialog dialog) {
                ;
            }//onDialogNegativeClick
        };//ResetConfirmationListener
        confirmation.setListener(listener);
    }//askForResetConfirmation

    public void changeSize(){
        Intent intent = getIntent();
        int size = intent.getIntExtra("size", 0);
        if (size != 0) {
            size = (int) sqrt((double) size);
            boolean isMultiplayer = currentGame.isMultiplayer;
            currentGame = new Game(isMultiplayer, size);
        }
        else{
            //try to retrieve saved games
            //if that fails from no saved game then create
        }
    }

    public void retrieveGames(){
        Context context = getApplicationContext();

    }

}//MainActivity
