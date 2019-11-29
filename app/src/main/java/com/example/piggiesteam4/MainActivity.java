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
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, Grid55Fragment.OnFragmentInteractionListener,
        Grid66Fragment.OnFragmentInteractionListener{

    public Game singlePlayer;

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

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }//if

            // Create a new Fragment to be placed in the activity layout
            Grid55Fragment defaultFragment = new Grid55Fragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            defaultFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout

            androidx.fragment.app.FragmentTransaction fragTrans =
                getSupportFragmentManager().beginTransaction();

            fragTrans.add(R.id.fragment_container, defaultFragment).commit();

            singlePlayer = new Game(55, false);


        }//if


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
     * Intentionally left blank
     *
     * Needs to be "implemented" for fragments to display
     * and not cause the app to crash
     *
     * @param uri - no idea
     */
    @Override
    public void onFragmentInteraction(Uri uri) {
        //empty
    }
}//MainActivity
