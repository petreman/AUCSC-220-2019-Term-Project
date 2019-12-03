package com.example.piggiesteam4;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.view.View;
import android.widget.Button;

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
}//GridParent
