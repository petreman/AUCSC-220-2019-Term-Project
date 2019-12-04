package com.example.piggiesteam4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ResetConfirmationFragment extends DialogFragment {

    public interface ResetConfirmationListener{
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    ResetConfirmationListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.confirmreset);

        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onDialogPositiveClick(ResetConfirmationFragment.this);
            }//onClick
        });//setPositiveButton

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }//onClick
        });//setNegativeButton
        return builder.create();
    }//onCreateDialog

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (ResetConfirmationListener) context;
        }//try
        catch(Exception e) {
            ;
        }//catch
    }//onAttach

    /**
     * Sets the listener.
     * @param listener new ResetConfirmationListener with implemented methods.
     */
    public void setListener(ResetConfirmationListener listener) {
        this.listener = listener;
    }//setListener
}
