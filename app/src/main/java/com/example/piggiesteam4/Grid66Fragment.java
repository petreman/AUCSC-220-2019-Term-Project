package com.example.piggiesteam4;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Grid66Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Grid66Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Grid66Fragment extends GridParent implements View.OnTouchListener, View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Grid66Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Grid55Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Grid66Fragment newInstance(String param1, String param2) {
        Grid66Fragment fragment = new Grid66Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            isMultiplayer = getArguments().getBoolean("multiplayer");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grid_55, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * When a button is pressed (ie fence placement attempted), a check is performed to
     * see if a fence can be placed at the specified location (if it hasn't been placed already)
     *
     * I wish there was a way to simplify this (I spent quite a while looking), it has to
     * be like this. At least it'll be fast?
     *
     * By Keegan
     *
     * @param v - the button clicked
     */
    @Override
    public void onClick(View v) {
        final char VERTICAL_ORIENTATION = 'v';
        final char HORIZONTAL_ORIENTATION = 'h';
        final int ORIENTATION_INDEX = 8;
        final int ROW_INDEX = 15;
        final int COL_INDEX = 16;

        String idName = main.getResources().getResourceEntryName(v.getId());
        Log.d("onClick", "Current player instance is " + fragmentGame.getCurrentPlayer());
        int row = Integer.parseInt((idName.charAt(ROW_INDEX) + "").trim());
        int col = Integer.parseInt((idName.charAt(COL_INDEX) + "").trim());
        char orientation = idName.charAt(ORIENTATION_INDEX);

        if (orientation == HORIZONTAL_ORIENTATION){
            setHorizontalFence(v, row, col);
        }//if
        else if (orientation == VERTICAL_ORIENTATION){
            setVerticalFence(v, row, col);
        }//elseif
        else{
            throw new AssertionError("Unexpected value, fence does not announce orientation");
        }//else

        listener.endGame(fragmentGame, this);

    }//onClick

    /**
     * Called when a horizontal button is touched. Takes the button's coordinates on grid.
     * If fence doesn't exist at corresponding spot in the grid, it's placed and the button
     * is updated to reflect this
     *
     * By Keegan
     *
     * @param v - the button pressed
     * @param row - button's respective row coordinate into xCoords of the grid
     * @param col - button's respective col coordinate into xCoords of the grid
     */
    void setHorizontalFence(View v, int row, int col){
        super.setHorizontalFence(v, row, col);
    }//setHorizontalFence

    /**
     * Called when a vertical button is touched. Takes the button's coordinates on grid
     * If fence doesn't exist at corresponding spot in the grid, it's placed an the button
     * is updated to reflect this
     *
     * By Keegan
     *
     * @param v - the button pressed
     * @param row - button's respective row coordinate into yCoords of the grid
     * @param col - button's respective col coordinate into yCoords of the grid
     */
    void setVerticalFence(View v, int row, int col){
        super.setVerticalFence(v, row, col);
    }//setHorizontalFence

    /**
     * Added for fun
     * If a "fence" hasn't been place yet, hovering over it with your finger
     * will make it slightly visible. works by checking how visible the selected fence
     * currently is: if alpha = 1, then already placed so don't bother
     *
     * By Keegan
     *
     * @param v - the view being touched
     * @param event - the event (ACTION_DOWN = finger push down, ACTION_UP = finger lift)
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return super.onTouch(v, event);
    }//onTouch

    /**
     * Called after a fence is placed. Keeps the visible player scores up to date
     *
     * By Keegan
     *
     * @param currentPlayer - the player who's turn it currently is
     */
    public void updateScoreView(Player currentPlayer){
        super.updateScoreView(currentPlayer);
    }//updateScoreView

    //toggle turn moved to parent

    /**
     * Called when a pen is completed. Gets the corresponding pen's pig image, so it
     * can be passed to togglePigVisibility to make it visible
     *
     * By Keegan
     *
     * @param row - row index for pig
     * @param col - col index for pig
     * @return the view of the pig that has been penned
     */
    public View getUpdatedPenView(int row, int col){
        return super.getUpdatedPenView(row, col);
    }//getUpdatedPen
}
