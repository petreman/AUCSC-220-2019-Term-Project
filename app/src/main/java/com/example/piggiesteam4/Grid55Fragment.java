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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Grid55Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Grid55Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Grid55Fragment extends Fragment implements View.OnTouchListener, View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MainActivity main;
    private OnFragmentInteractionListener mListener;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        main = (MainActivity) getActivity(); //get the main activity to share game variable






    }

    /**
     * Set up the views on creation of the fragment
     *
     * I'm sorry Rosanna, but because everything is done with buttons,
     * this is the way it has to be: I cant use the "onClick" attribute
     * in the XML as that is apparently only able to grab functions
     * from the view's activity, NOT fragment
     *
     * Every button had to be manually set for the onClickListener
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


        Button hfence00 = (Button) v.findViewById(R.id.grid_55_hfence_00);
        hfence00.setOnClickListener(this);
        hfence00.setOnTouchListener(this);
        Button hfence01 = (Button) v.findViewById(R.id.grid_55_hfence_01);
        hfence01.setOnClickListener(this);
        hfence01.setOnTouchListener(this);
        Button hfence02 = (Button) v.findViewById(R.id.grid_55_hfence_02);
        hfence02.setOnClickListener(this);
        hfence02.setOnTouchListener(this);
        Button hfence03 = (Button) v.findViewById(R.id.grid_55_hfence_03);
        hfence03.setOnClickListener(this);
        hfence03.setOnTouchListener(this);

        Button hfence10 = (Button) v.findViewById(R.id.grid_55_hfence_10);
        hfence10.setOnClickListener(this);
        hfence10.setOnTouchListener(this);
        Button hfence11 = (Button) v.findViewById(R.id.grid_55_hfence_11);
        hfence11.setOnClickListener(this);
        hfence11.setOnTouchListener(this);
        Button hfence12 = (Button) v.findViewById(R.id.grid_55_hfence_12);
        hfence12.setOnClickListener(this);
        hfence12.setOnTouchListener(this);
        Button hfence13 = (Button) v.findViewById(R.id.grid_55_hfence_13);
        hfence13.setOnClickListener(this);
        hfence13.setOnTouchListener(this);

        Button hfence20 = (Button) v.findViewById(R.id.grid_55_hfence_20);
        hfence20.setOnClickListener(this);
        hfence20.setOnTouchListener(this);
        Button hfence21 = (Button) v.findViewById(R.id.grid_55_hfence_21);
        hfence21.setOnClickListener(this);
        hfence21.setOnTouchListener(this);
        Button hfence22 = (Button) v.findViewById(R.id.grid_55_hfence_22);
        hfence22.setOnClickListener(this);
        hfence22.setOnTouchListener(this);
        Button hfence23 = (Button) v.findViewById(R.id.grid_55_hfence_23);
        hfence23.setOnClickListener(this);
        hfence23.setOnTouchListener(this);

        Button hfence30 = (Button) v.findViewById(R.id.grid_55_hfence_30);
        hfence30.setOnClickListener(this);
        hfence30.setOnTouchListener(this);
        Button hfence31 = (Button) v.findViewById(R.id.grid_55_hfence_31);
        hfence31.setOnClickListener(this);
        hfence31.setOnTouchListener(this);
        Button hfence32 = (Button) v.findViewById(R.id.grid_55_hfence_32);
        hfence32.setOnClickListener(this);
        hfence32.setOnTouchListener(this);
        Button hfence33 = (Button) v.findViewById(R.id.grid_55_hfence_33);
        hfence33.setOnClickListener(this);
        hfence33.setOnTouchListener(this);

        Button hfence40 = (Button) v.findViewById(R.id.grid_55_hfence_40);
        hfence40.setOnClickListener(this);
        hfence40.setOnTouchListener(this);
        Button hfence41 = (Button) v.findViewById(R.id.grid_55_hfence_41);
        hfence41.setOnClickListener(this);
        hfence41.setOnTouchListener(this);
        Button hfence42 = (Button) v.findViewById(R.id.grid_55_hfence_42);
        hfence42.setOnClickListener(this);
        hfence42.setOnTouchListener(this);
        Button hfence43 = (Button) v.findViewById(R.id.grid_55_hfence_43);
        hfence43.setOnClickListener(this);
        hfence43.setOnTouchListener(this);

        Button vfence00 = (Button) v.findViewById(R.id.grid_55_vfence_00);
        vfence00.setOnClickListener(this);
        vfence00.setOnTouchListener(this);
        Button vfence01 = (Button) v.findViewById(R.id.grid_55_vfence_01);
        vfence01.setOnClickListener(this);
        vfence01.setOnTouchListener(this);
        Button vfence02 = (Button) v.findViewById(R.id.grid_55_vfence_02);
        vfence02.setOnClickListener(this);
        vfence02.setOnTouchListener(this);
        Button vfence03 = (Button) v.findViewById(R.id.grid_55_vfence_03);
        vfence03.setOnClickListener(this);
        vfence03.setOnTouchListener(this);

        Button vfence10 = (Button) v.findViewById(R.id.grid_55_vfence_10);
        vfence10.setOnClickListener(this);
        vfence10.setOnTouchListener(this);
        Button vfence11 = (Button) v.findViewById(R.id.grid_55_vfence_11);
        vfence11.setOnClickListener(this);
        vfence11.setOnTouchListener(this);
        Button vfence12 = (Button) v.findViewById(R.id.grid_55_vfence_12);
        vfence12.setOnClickListener(this);
        vfence12.setOnTouchListener(this);
        Button vfence13 = (Button) v.findViewById(R.id.grid_55_vfence_13);
        vfence13.setOnClickListener(this);
        vfence13.setOnTouchListener(this);

        Button vfence20 = (Button) v.findViewById(R.id.grid_55_vfence_20);
        vfence20.setOnClickListener(this);
        vfence20.setOnTouchListener(this);
        Button vfence21 = (Button) v.findViewById(R.id.grid_55_vfence_21);
        vfence21.setOnClickListener(this);
        vfence21.setOnTouchListener(this);
        Button vfence22 = (Button) v.findViewById(R.id.grid_55_vfence_22);
        vfence22.setOnClickListener(this);
        vfence22.setOnTouchListener(this);
        Button vfence23 = (Button) v.findViewById(R.id.grid_55_vfence_23);
        vfence23.setOnClickListener(this);
        vfence23.setOnTouchListener(this);

        Button vfence30 = (Button) v.findViewById(R.id.grid_55_vfence_30);
        vfence30.setOnClickListener(this);
        vfence30.setOnTouchListener(this);
        Button vfence31 = (Button) v.findViewById(R.id.grid_55_vfence_31);
        vfence31.setOnClickListener(this);
        vfence31.setOnTouchListener(this);
        Button vfence32 = (Button) v.findViewById(R.id.grid_55_vfence_32);
        vfence32.setOnClickListener(this);
        vfence32.setOnTouchListener(this);
        Button vfence33 = (Button) v.findViewById(R.id.grid_55_vfence_33);
        vfence33.setOnClickListener(this);
        vfence33.setOnTouchListener(this);

        Button vfence04 = (Button) v.findViewById(R.id.grid_55_vfence_04);
        vfence04.setOnClickListener(this);
        vfence04.setOnTouchListener(this);
        Button vfence14 = (Button) v.findViewById(R.id.grid_55_vfence_14);
        vfence14.setOnClickListener(this);
        vfence14.setOnTouchListener(this);
        Button vfence24 = (Button) v.findViewById(R.id.grid_55_vfence_24);
        vfence24.setOnClickListener(this);
        vfence24.setOnTouchListener(this);
        Button vfence34 = (Button) v.findViewById(R.id.grid_55_vfence_34);
        vfence34.setOnClickListener(this);
        vfence34.setOnTouchListener(this);

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
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {

        Grid currentGrid = main.singlePlayer.getGrid();
        int currentColor = main.singlePlayer.getCurrentPlayerColor();
        Button fence;


        switch (v.getId()){

            case R.id.grid_55_hfence_00:

                if (currentGrid.setFenceX(0,0, currentColor)){
                    fence = (Button) v.findViewById(R.id.grid_55_hfence_00);
                    fence.getBackground().setColorFilter(currentColor, PorterDuff.Mode.MULTIPLY);
                    fence.setAlpha((float)1.0);
                }//if

                break;

            default:
                fence = (Button) v.findViewById(v.getId());
                fence.getBackground().setColorFilter(currentColor, PorterDuff.Mode.MULTIPLY);
                fence.setAlpha((float)1.0);
                break;

        }//switch
    }

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

    /**
     * Added for fun
     * If a "fence" hasn't been place yet, hovering over it with your finger
     * will make it slightly visible
     *
     * By Keegan
     *
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {




        //Button Pressed
        if(event.getAction() == MotionEvent.ACTION_DOWN && v.findViewById(v.getId()).getAlpha() != 1){
            v.findViewById(v.getId()).setAlpha((float)0.15);
        }//if

        //button released
        if(event.getAction() == MotionEvent.ACTION_UP && v.findViewById(v.getId()).getAlpha() != 1){
            v.findViewById(v.getId()).setAlpha((float)(0.0));
        }//if



        return false;

    }//onTouch

}


