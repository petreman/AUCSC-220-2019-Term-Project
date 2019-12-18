/**
 * AUCSC 220
 * PiggiesTeam4
 *
 * NameQueryFragment.java
 *
 * To be used upon the game ending.
 * The winner may then enter their name to be stored in the leaderboard.
 */
package com.example.piggiesteam4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class NameQueryFragment extends DialogFragment {

    public interface QueryDialogListener{
        void onDialogPositiveClick(DialogFragment dialog);
        void onDialogNegativeClick(DialogFragment dialog);
    }//NoticeDialogListener

    private QueryDialogListener listener;
    private String name;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.ask_winner_name);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View popupLayout = inflater.inflate(R.layout.name_query_popup, null);

        builder.setView(popupLayout);

        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText input = popupLayout.findViewById(R.id.nameQuery);
                String content = input.getText().toString();
                name = content;
                listener.onDialogPositiveClick(NameQueryFragment.this);
            }//onClick
        });//setPositiveButton

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //code to return unknown??
                listener.onDialogNegativeClick(NameQueryFragment.this);
            }//onClick
        });//setNegativeButton
        return builder.create();
    }//onCreateDialog

    /**
     * Sets the listener.
     * @param listener new QueryDialogListener with implemented methods.
     */
    public void setListener(QueryDialogListener listener) {
        this.listener = listener;
    }//setListener

    /**
     * Gets the name of the player entered.
     * @return the name.
     */
    public String getName(){
        return name;
    }//getName
}//NameQueryFragment
