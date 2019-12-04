package com.example.piggiesteam4;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class GridParent extends Fragment {

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public interface gameStateListener {
        void endGame(Game game, GridParent frag);
        void saveGame(GridParent frag);
        boolean retrieveGame(GridParent frag);
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    protected static final String ARG_PARAM1 = "param1";
    protected static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    protected String mParam1;
    protected String mParam2;
    protected MainActivity main;
    protected boolean isMultiplayer;
    protected Game fragmentGame;
    protected OnFragmentInteractionListener mListener;

    protected Button p1ScoreButton;
    protected Button p2ScoreButton;

    protected gameStateListener listener;
    protected int size;
    protected View fragmentView;

    /**
     * Sets the listener.
     * @param listener the listener.
     */
    public void setListener(gameStateListener listener){
        this.listener = listener;
    }//setListener

    /**
     * Saves game on pause.
     */
    @Override
    public void onPause(){
        super.onPause();
        listener.saveGame(this);
    }//onPause

    /**
     * Loads the game.
     */
    public void loadGame(){
        if (listener.retrieveGame(this)){
            showSaved();
        }//if
    }//load

    /**
     * Shows the fences previously selected in a saved game.
     */
    public void showSaved(){
        Grid.Fence[][] xCoords = fragmentGame.getGrid().getxCoords();
        Grid.Fence[][] yCoords = fragmentGame.getGrid().getyCoords();

        for (int row = 0; row < size; row++){
            for (int col = 0; col < size; col++){
                if (col < size - 1){
                    Grid.Fence currentXFence = xCoords[row][col];
                    if (currentXFence.exists()) {
                        Button fence = (Button) fragmentView.findViewById(getResources()
                                .getIdentifier("grid_" + size + size + "_hfence_" + row + col,
                                        "id", this.getActivity().getPackageName()));

                        fence.getBackground()
                                .setColorFilter(currentXFence.getColor(), PorterDuff.Mode.MULTIPLY);

                        fence.setAlpha((float) 1.0);
                    }//if
                }//if

                if (row < size - 1){
                    Grid.Fence currentYFence = yCoords[row][col];
                    if (currentYFence.exists()) {
                        Button fence = (Button) fragmentView.findViewById(getResources()
                                .getIdentifier("grid_" + size + size + "_vfence_" + row + col,
                                        "id", this.getActivity().getPackageName()));

                        fence.getBackground()
                                .setColorFilter(currentYFence.getColor(), PorterDuff.Mode.MULTIPLY);

                        fence.setAlpha((float) 1.0);
                    }//if
                }//if
            }//for
        }//for
    }//showSaved

    /**
     * Resets the fence UI.
     */
    public void resetFences(){
        for (int row = 0; row < size; row++){
            for (int col = 0; col < size; col++){
                if (col < (size - 1)){
                    Button fence = (Button) fragmentView.findViewById(getResources()
                            .getIdentifier("grid_" + size + size + "_hfence_" + row + col,
                                    "id", this.getActivity().getPackageName()));

                    fence.getBackground()
                            .setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);

                    fence.setAlpha((float) 0.0);
                }//if

                if (row < size - 1){
                    Button fence = (Button) fragmentView.findViewById(getResources()
                            .getIdentifier("grid_" + size + size + "_vfence_" + row + col,
                                    "id", this.getActivity().getPackageName()));

                    fence.getBackground()
                            .setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);

                    fence.setAlpha((float) 0.0);
                }//if
            }//for
        }//for
    }//resetFences

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
        setMainCurrentPlayer(fragmentGame.getCurrentPlayer());

    }//toggleTurn

    /**
     * Sets player 1 to be the current player.
     */
    public void setP1Current(){
        if(fragmentGame.getCurrentPlayer() != fragmentGame.getPlayer1()){
            toggleTurn(fragmentGame.getCurrentPlayer());
        }
    }
    protected MainActivity.resetListener resetListener = new MainActivity.resetListener() {
        /**
         * Resets the game.
         */
        @Override
        public void reset() {
            fragmentGame.endGame();
            resetFences();
            System.out.println("AAAAAAA");
        }
    };

    /**
     * Sets the resetListener in the main activity.
     */
    public void setReset(){
        ((MainActivity) getActivity()).setResetListener(resetListener);
    }

    /**
     * Sets the currentGame in the main activity to the currently active game.
     * @param isMultiplayer
     */
    public void setMainCurrentGame(boolean isMultiplayer){
        if (isMultiplayer){
            main.currentGame = main.multiPlayer;
        }
        else{
            main.currentGame = main.singlePlayer;
        }
    }

    public void setMainCurrentPlayer(Player currentPlayer){
        main.currentPlayer = currentPlayer;
    }

    public void resetPens() {

        int sizeX = fragmentGame.getGrid().getX();
        int sizeY = fragmentGame.getGrid().getY();

        for (int i = 0; i < sizeX - 1; i++) {

            for (int j = 0; j < sizeY - 1; j++) {

                String penID = "grid_" + sizeX + sizeY + "_pig_" + i + j;
                int resID = getResources().getIdentifier(penID, "id", main.getPackageName());

                ImageView pig = ((ImageView) fragmentView.findViewById(resID));
                resetPigVisibility(pig);

            }//for

        }//for

    }//resetPens

    /**
     * Called when a pen has been completed. Makes the parameter pig (in the pen) visible,
     * ands give the pig a color filter based on who created the pen
     *
     * By Keegan
     *
     * @param v - the image view to toggle visibility of
     */
    public void togglePigVisibility(View v){

        ImageView pig = (ImageView) v;

        if (pig.getVisibility() == View.VISIBLE){
            resetPigVisibility(pig);
        }//if

        else{
            pig.setColorFilter(fragmentGame.getCurrentPlayer().getColorLight(),
                    PorterDuff.Mode.MULTIPLY);
            pig.setVisibility(View.VISIBLE);
        }//else

    }//togglePigVisibility

    /**
     * Sets the provided image view to be invisible
     * @param pig - the image view (of a pig) to make invisible
     */
    public void resetPigVisibility(ImageView pig){

        pig.setVisibility(View.INVISIBLE);
        pig.clearColorFilter();

    }//resetPigVisibility
}//GridParent
