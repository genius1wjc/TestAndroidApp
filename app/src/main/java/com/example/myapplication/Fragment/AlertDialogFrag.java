package com.example.myapplication.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class AlertDialogFrag extends DialogFragment {
    private int year, month, day;

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface MyDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    MyDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (MyDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString() + " must implement MyDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final List mSelectedItems = new ArrayList();  // Where we track the selected items
        Bundle args = getArguments();

        if (args == null)
            return null;

        if (args.getString("Type") == "Alert" && args.getInt("Count") == 3) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(args.getString("Title"))
                    .setMessage("Message")
                    .setPositiveButton("Fire", null) // Set to null. We override the onclick
                    .setNeutralButton("Remind later", null)
                    .setNegativeButton("Cancel", null)
                    .setMultiChoiceItems(R.array.toppings, null,
                            new DialogInterface.OnMultiChoiceClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which,
                                                    boolean isChecked) {
                                    if (isChecked) {
                                        // If the user checked the item, add it to the selected items
                                        mSelectedItems.add(which);
                                    } else if (mSelectedItems.contains(which)) {
                                        // Else, if the item is already in the array, remove it
                                        mSelectedItems.remove(Integer.valueOf(which));
                                    }
                                }
                            });

            // Create the AlertDialog object and return it
            return builder.create();
        }
        else
            return null;
    }

    /**
     * Used to not dismiss dialog when positive button is clicked
     */
    @Override
    public void onStart() {
        super.onStart();    //super.onStart() is where dialog.show() is actually called on the underlying dialog, so we have to do it after this point
        final AlertDialog d = (AlertDialog) getDialog();
        if (d != null) {
            Button positiveButton = d.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onDialogPositiveClick(AlertDialogFrag.this);
                }
            });

            Button negativeButton = d.getButton(Dialog.BUTTON_NEGATIVE);
            negativeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    mListener.onDialogNegativeClick(AlertDialogFrag.this);
                }
            });
        }
    }
}
