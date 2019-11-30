package com.example.piggiesteam4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
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
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }//NoticeDialogListener

    private QueryDialogListener listener;
    private String name;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.askwinnername);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View popupLayout = inflater.inflate(R.layout.name_query_popup, null);

        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText input = popupLayout.findViewById(R.id.nameQuery);
                String content = input.getText().toString();
                name = content;
                listener.onDialogPositiveClick(NameQueryFragment.this);
            }//onClick
        });//setPositiveButton

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //code to return unknown??
            }//onClick
        });//setNegativeButton
        return builder.create();
    }//onCreateDialog

    public void setListener(QueryDialogListener listener) {
        this.listener = listener;
    }

    public String getName(){
        return name;
    }
}
