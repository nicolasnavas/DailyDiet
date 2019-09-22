package es.saladillo.nicolas.dailydiet.ui.dialogs;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import es.saladillo.nicolas.dailydiet.R;

public class MinimumQuantityDialogFragment extends DialogFragment {

    private Listener listener;

    @SuppressWarnings({"EmptyMethod", "UnusedParameters"})
    public interface Listener {
        void onAcceptClick(DialogFragment dialog);

        void onCloseClick(DialogFragment dialog);
    }

    @SuppressLint("InflateParams")
    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setCancelable(false);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(requireActivity());
        dialogBuilder.setTitle("Set minimum quantity");
        dialogBuilder.setView(LayoutInflater.from(requireActivity()).inflate(R.layout.dialog_minimum_quantity, null));
        dialogBuilder.setPositiveButton("Accept",
                (dialog, which) -> listener.onAcceptClick(MinimumQuantityDialogFragment.this));
        dialogBuilder.setNegativeButton("Close",
                (dialog, which) -> listener.onCloseClick(MinimumQuantityDialogFragment.this));
        return dialogBuilder.create();
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {
            if (getTargetFragment() != null) {
                listener = (Listener) getTargetFragment();
            } else {
                listener = (Listener) activity;
            }
        } catch (ClassCastException e) {
            throw new ClassCastException(
                    "Listener must implement MinimumQuantityDialogFragment.Listener");
        }
    }

}
