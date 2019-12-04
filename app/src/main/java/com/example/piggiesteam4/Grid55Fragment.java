/**
 * AUCSC220
 * PiggiesTeam4
 *
 * Grid55Fragment.java
 *
 * Java code for the fragment that holds the 5x5 grid (fragment_grid_55.xml)
 *
 * There are a lot of default functions here that i don't know what they do or if I can
 * safely remove them, so I wont comment on them
 *
 * Methods:
 *  -
 *
 * Started November 26, 2019 by Keegan
 *
 * Changelog:
 *  n/a
 */
package com.example.piggiesteam4;

import android.content.Context;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Grid55Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Grid55Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Grid55Fragment extends GridParent implements View.OnTouchListener, View.OnClickListener{
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//    private MainActivity main;
//    private boolean isMultiplayer;
//    private Game fragmentGame;
//    private OnFragmentInteractionListener mListener;
//
//    private Button p1ScoreButton;
//    private Button p2ScoreButton;

    public Grid55Fragment() {
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
    public static Grid55Fragment newInstance(String param1, String param2) {
        Grid55Fragment fragment = new Grid55Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * On creation of the fragment, find out if it is for a multiplayer game or not
     * so it can point to the correct game inside MainActivity
     *
     * By Keegan
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            isMultiplayer = getArguments().getBoolean("multiplayer");
        }//if

        main = (MainActivity) getActivity(); //get the main activity to share game variables

        if (isMultiplayer){
            fragmentGame = main.multiPlayer;
        }//if

        else{
            fragmentGame = main.singlePlayer;
        }//if

        p1ScoreButton = main.p1Score;
        p2ScoreButton = main.p2Score;

        size = 5;

    }//onCreate

    /**
     * Set up the buttons on creation of the fragment
     *
     * I also set an onTouchListener for every button: what this does, is when a unplaced
     * fence/button is pressed and held, a little preview of the fence appears first,
     * designed to give a little bit of feedback to the player
     *
     * By Keegan
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_grid_55, container, false);
        fragmentView = v;
        loadGame();

        int sizeX = fragmentGame.getGrid().getX();
        int sizeY = fragmentGame.getGrid().getY();

        for(int i = 0; i < sizeX ; i++) {

            for(int j = 0 ; j < sizeY - 1 ; j++) {

                String buttonID = "grid_" + sizeX + sizeY +"_hfence_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", main.getPackageName());

                Button button = ((Button) v.findViewById(resID));
                button.setOnClickListener(this);
                button.setOnTouchListener(this);

            }//for

        }//for

        for(int i = 0; i < sizeX - 1 ; i++) {

            for(int j = 0 ; j < sizeY ; j++) {

                String buttonID = "grid_" + sizeX + sizeY +"_vfence_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", main.getPackageName());

                Button button = ((Button) v.findViewById(resID));
                button.setOnClickListener(this);
                button.setOnTouchListener(this);

            }//for

        }//for

        // Inflate the layout for this fragment
        return v;

    }//onCreateView

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
    }//onAttach

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }//onDetach

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

        switch (v.getId()){

            case R.id.grid_55_hfence_00:
                setHorizontalFence(v, 0, 0);
                break;

            case R.id.grid_55_hfence_01:
                setHorizontalFence(v, 0, 1);
                break;

            case R.id.grid_55_hfence_02:
                setHorizontalFence(v, 0, 2);
                break;

            case R.id.grid_55_hfence_03:
                setHorizontalFence(v, 0, 3);
                break;

            case R.id.grid_55_hfence_10:
                setHorizontalFence(v, 1, 0);
                break;

            case R.id.grid_55_hfence_11:
                setHorizontalFence(v, 1, 1);
                break;

            case R.id.grid_55_hfence_12:
                setHorizontalFence(v, 1, 2);
                break;

            case R.id.grid_55_hfence_13:
                setHorizontalFence(v, 1, 3);
                break;

            case R.id.grid_55_hfence_20:
                setHorizontalFence(v, 2, 0);
                break;

            case R.id.grid_55_hfence_21:
                setHorizontalFence(v, 2, 1);
                break;

            case R.id.grid_55_hfence_22:
                setHorizontalFence(v, 2, 2);
                break;

            case R.id.grid_55_hfence_23:
                setHorizontalFence(v, 2, 3);
                break;

            case R.id.grid_55_hfence_30:
                setHorizontalFence(v, 3, 0);
                break;

            case R.id.grid_55_hfence_31:
                setHorizontalFence(v, 3, 1);
                break;

            case R.id.grid_55_hfence_32:
                setHorizontalFence(v, 3, 2);
                break;

            case R.id.grid_55_hfence_33:
                setHorizontalFence(v, 3, 3);
                break;

            case R.id.grid_55_hfence_40:
                setHorizontalFence(v, 4, 0);
                break;

            case R.id.grid_55_hfence_41:
                setHorizontalFence(v, 4, 1);
                break;

            case R.id.grid_55_hfence_42:
                setHorizontalFence(v, 4, 2);
                break;

            case R.id.grid_55_hfence_43:
                setHorizontalFence(v, 4, 3);
                break;

            case R.id.grid_55_vfence_00:
                setVerticalFence(v, 0, 0);
                break;

            case R.id.grid_55_vfence_01:
                setVerticalFence(v, 0, 1);
                break;

            case R.id.grid_55_vfence_02:
                setVerticalFence(v, 0, 2);
                break;

            case R.id.grid_55_vfence_03:
                setVerticalFence(v, 0, 3);
                break;

            case R.id.grid_55_vfence_10:
                setVerticalFence(v, 1, 0);
                break;

            case R.id.grid_55_vfence_11:
                setVerticalFence(v, 1, 1);
                break;

            case R.id.grid_55_vfence_12:
                setVerticalFence(v, 1, 2);
                break;

            case R.id.grid_55_vfence_13:
                setVerticalFence(v, 1, 3);
                break;

            case R.id.grid_55_vfence_20:
                setVerticalFence(v, 2, 0);
                break;

            case R.id.grid_55_vfence_21:
                setVerticalFence(v, 2, 1);
                break;

            case R.id.grid_55_vfence_22:
                setVerticalFence(v, 2, 2);
                break;

            case R.id.grid_55_vfence_23:
                setVerticalFence(v, 2, 3);
                break;

            case R.id.grid_55_vfence_30:
                setVerticalFence(v, 3, 0);
                break;

            case R.id.grid_55_vfence_31:
                setVerticalFence(v, 3, 1);
                break;

            case R.id.grid_55_vfence_32:
                setVerticalFence(v, 3, 2);
                break;

            case R.id.grid_55_vfence_33:
                setVerticalFence(v, 3, 3);
                break;

            case R.id.grid_55_vfence_04:
                setVerticalFence(v, 0, 4);
                break;

            case R.id.grid_55_vfence_14:
                setVerticalFence(v, 1, 4);
                break;

            case R.id.grid_55_vfence_24:
                setVerticalFence(v, 2, 4);
                break;

            case R.id.grid_55_vfence_34:
                setVerticalFence(v, 3, 4);
                break;

            default:
                break;

        }//switch

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

        Grid currentGrid = fragmentGame.getGrid();
        int currentColor = fragmentGame.getCurrentPlayer().getColor();
        Player currentPlayer = fragmentGame.getCurrentPlayer();
        Button fence = v.findViewById(v.getId());

        switch (row){

            //top row fences
            case 0:

                //if fence is placed on top row
                if (currentGrid.setFenceX(row, col, currentColor)) {

                    //set color
                    fence.getBackground().setColorFilter(currentColor, PorterDuff.Mode.MULTIPLY);
                    fence.setAlpha((float) 1.0);

                    //if fence placement didn't make pen
                    if (!currentGrid.checkPenBelow(row, col, currentPlayer)) {

                        //other players turn
                        toggleTurn(currentPlayer);

                    }//if

                    else{
                        togglePigVisibility(getUpdatedPenView(row, col));
                    }//else

                }//if

                break;

            //bottom row fences
            case 4:

                //if fence is placed on the bottom of grid
                if (currentGrid.setFenceX(row, col, currentColor)) {

                    //set color
                    fence.getBackground().setColorFilter(currentColor, PorterDuff.Mode.MULTIPLY);
                    fence.setAlpha((float) 1.0);

                    //if fence placement didn't make pen
                    if (!currentGrid.checkPenAbove(row, col, currentPlayer)) {

                        //other players turn
                        toggleTurn(currentPlayer);

                    }//if

                    else{
                        togglePigVisibility(getUpdatedPenView(row - 1, col));
                    }//else

                }//if

                break;

            //if fence placed in between top and bottom of grid
            default:

                boolean createdPen = false;

                //if fence is placed
                if (currentGrid.setFenceX(row, col, currentColor)) {

                    //set color
                    fence.getBackground().setColorFilter(currentColor, PorterDuff.Mode.MULTIPLY);
                    fence.setAlpha((float) 1.0);

                    //check for pen to the left
                    if (currentGrid.checkPenAbove(row, col, currentPlayer)){
                        togglePigVisibility(getUpdatedPenView(row - 1, col));
                        createdPen = true;
                    }//if

                    //check for pen to the right
                    if (currentGrid.checkPenBelow(row, col, currentPlayer)){
                        togglePigVisibility(getUpdatedPenView(row, col));
                        createdPen = true;
                    }//if

                    //other player turn if no pen made
                    if (!createdPen){
                        toggleTurn(currentPlayer);
                    }//if

                }//if

                break;

        }//switch

        updateScoreView(currentPlayer);

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

        Grid currentGrid = fragmentGame.getGrid();
        int currentColor = fragmentGame.getCurrentPlayer().getColor();
        Player currentPlayer = fragmentGame.getCurrentPlayer();
        Button fence = v.findViewById(v.getId());

        switch (col){

            //far left fences
            case 0:

                //if fence if placed on far left
                if (currentGrid.setFenceY(row, col, currentColor)) {

                    //set color
                    fence.getBackground().setColorFilter(currentColor, PorterDuff.Mode.MULTIPLY);
                    fence.setAlpha((float) 1.0);

                    //if fence placement didn't make pen
                    if (!currentGrid.checkPenRight(row, col, currentPlayer)) {

                        //other players turn
                        toggleTurn(currentPlayer);

                    }//if

                    else{
                        togglePigVisibility(getUpdatedPenView(row, col));
                    }

                }//if

                break;

            //far right fences
            case 4:

                //if fence placed on far right
                if (currentGrid.setFenceY(row, col, currentColor)) {

                    //set color
                    fence.getBackground().setColorFilter(currentColor, PorterDuff.Mode.MULTIPLY);
                    fence.setAlpha((float) 1.0);

                    //if fence placement didn't make pen
                    if (!currentGrid.checkPenLeft(row, col, currentPlayer)) {

                        //other players turn
                        toggleTurn(currentPlayer);

                    }//if

                    else{
                        togglePigVisibility(getUpdatedPenView(row, col - 1));
                    }//else

                }//if

                break;

            //fences placed between far left and right
            default:

                boolean createdPen = false;

                //if fence is placed
                if (currentGrid.setFenceY(row, col, currentColor)) {

                    //set color
                    fence.getBackground().setColorFilter(currentColor, PorterDuff.Mode.MULTIPLY);
                    fence.setAlpha((float) 1.0);

                    //check for pen to the left
                    if (currentGrid.checkPenLeft(row, col, currentPlayer)){
                        togglePigVisibility(getUpdatedPenView(row, col - 1));
                        createdPen = true;
                    }//if

                    //check for pen to the right
                    if (currentGrid.checkPenRight(row, col, currentPlayer)){
                        togglePigVisibility(getUpdatedPenView(row, col));
                        createdPen = true;
                    }//if

                    //other player turn if no pen made
                    if (!createdPen){
                        toggleTurn(currentPlayer);
                    }//if

                }//if

                break;

        }//switch

        updateScoreView(currentPlayer);

    }//setHorizontalFence

//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }

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

        //button pressed down
        if(event.getAction() == MotionEvent.ACTION_DOWN &&
                v.findViewById(v.getId()).getAlpha() != 1){
            v.findViewById(v.getId()).setAlpha((float)0.15);
        }//if

        //button released
        if(event.getAction() == MotionEvent.ACTION_UP &&
                v.findViewById(v.getId()).getAlpha() != 1){
            v.findViewById(v.getId()).setAlpha((float)(0.0));
        }//if

        return false;

    }//onTouch

    /**
     * Called after a fence is placed. Keeps the visible player scores up to date
     *
     * By Keegan
     *
     * @param currentPlayer - the player who's turn it currently is
     */
    public void updateScoreView(Player currentPlayer){

        //update the scoreboard in MainActivity
        p1ScoreButton.setText(Integer.toString(fragmentGame.getPlayer1().getScore()));
        p2ScoreButton.setText(Integer.toString(fragmentGame.getPlayer2().getScore()));

    }//updateScoreView

    /**
     * Ends the current players turn, and set's the other players turn to true.
     * Also updates the color of the player score button to indicate who the new
     * current player is
     *
     * By Keegan
     *
     * @param currentPlayer - the player who's turn it currently is
     */
    public void toggleTurn(Player currentPlayer) {

        if (currentPlayer == fragmentGame.getPlayer1()) {

            p2ScoreButton.getBackground().setColorFilter(fragmentGame.getPlayer2().getColor(),
                    PorterDuff.Mode.MULTIPLY);

            p1ScoreButton.getBackground().setColorFilter(fragmentGame.getPlayer1().getColorLight(),
                    PorterDuff.Mode.MULTIPLY);


        }//if

        else{

            p1ScoreButton.getBackground().setColorFilter(fragmentGame.getPlayer1().getColor(),
                    PorterDuff.Mode.MULTIPLY);

            p2ScoreButton.getBackground().setColorFilter(fragmentGame.getPlayer2().getColorLight(),
                    PorterDuff.Mode.MULTIPLY);

        }//else

        fragmentGame.toggleCurrentPlayer();

    }//toggleTurn

    /**
     * Called when a pen is completed. Gets the corresponding pen's pig image, so it
     * can be passed to togglePigVisibility to make it visible
     *
     * Yes, it is a really ugly nested switch. It's either this, or set a listener for
     * every single pig image, which would take just as much work if not more (see onCreateView()
     * above to see what setting a listener for multiple views looks like). If i did do it
     * that way, then I'd also have to define a custom listener, which I'm still not sure about
     * doing and would also take more work. So what I'm trying to say is that this nested switch
     * setup was the more convenient thing to do.
     *
     * By Keegan
     *
     * @param row - row index for pig
     * @param col - col index for pig
     * @return the view of the pig that has been penned
     */
     public View getUpdatedPenView(int row, int col){

        switch (row){

            //first row
            case 0:

                switch(col){

                    case 0:
                        return this.getActivity().findViewById(R.id.grid_55_pig_00);

                    case 1:
                        return this.getActivity().findViewById(R.id.grid_55_pig_01);

                    case 2:
                        return this.getActivity().findViewById(R.id.grid_55_pig_02);

                    case 3:
                        return this.getActivity().findViewById(R.id.grid_55_pig_03);

                    default:
                        break;

                }//switch col

            //second row
            case 1:

                switch(col) {

                    case 0:
                        return this.getActivity().findViewById(R.id.grid_55_pig_10);

                    case 1:
                        return this.getActivity().findViewById(R.id.grid_55_pig_11);

                    case 2:
                        return this.getActivity().findViewById(R.id.grid_55_pig_12);

                    case 3:
                        return this.getActivity().findViewById(R.id.grid_55_pig_13);

                    default:
                        break;

                }//switch col

                //third row
            case 2:

                switch(col) {

                    case 0:
                        return this.getActivity().findViewById(R.id.grid_55_pig_20);

                    case 1:
                        return this.getActivity().findViewById(R.id.grid_55_pig_21);

                    case 2:
                        return this.getActivity().findViewById(R.id.grid_55_pig_22);

                    case 3:
                        return this.getActivity().findViewById(R.id.grid_55_pig_23);

                    default:
                        break;

                }//switch col

            //third row
            case 3:

                switch(col) {

                    case 0:
                        return this.getActivity().findViewById(R.id.grid_55_pig_30);

                    case 1:
                        return this.getActivity().findViewById(R.id.grid_55_pig_31);

                    case 2:
                        return this.getActivity().findViewById(R.id.grid_55_pig_32);

                    case 3:
                        return this.getActivity().findViewById(R.id.grid_55_pig_33);

                    default:
                        break;

                }//switch col

            default:
                break;

        }//switch row

        return null;

    }//getUpdatedPen

}//Grid55Fragment
