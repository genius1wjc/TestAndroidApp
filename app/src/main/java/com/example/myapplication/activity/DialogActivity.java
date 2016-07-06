package com.example.myapplication.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.myapplication.fragment.AlertDialogFrag;
import com.example.myapplication.fragment.DatePickerFragment;
import com.example.myapplication.R;

import java.util.Calendar;

public class DialogActivity extends AppCompatActivity implements AlertDialogFrag.MyDialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleButton);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    // Need to call "getSupportFragmentManager()" rather than "getFragmentManager()"
                    FragmentManager fm = getSupportFragmentManager();
                    AlertDialogFrag df = new AlertDialogFrag();
                    Bundle args = new Bundle();
                    args.putString("Type", "Alert");
                    args.putString("Title", "Fire?");
                    args.putInt("Count", 3);
                    df.setArguments(args);
                    df.show(fm, "Alert Dialog");
                }
            }
        });
    }

    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // User touched the dialog's positive button
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // User touched the dialog's negative button
        Toast.makeText(this, "User touched the dialog's negative button", Toast.LENGTH_LONG).show();
    }

    DatePickerDialog.OnDateSetListener onDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Toast.makeText(DialogActivity.this,
                    String.valueOf(year) + "-" + String.valueOf(monthOfYear) + "-" + String.valueOf(dayOfMonth),
                    Toast.LENGTH_LONG).show();
        }
    };

    public void showDatePicker(View v) {
        DatePickerFragment date = new DatePickerFragment();

        // Set Up Current Date Into dialog
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);

        // Set Call back to capture selected date
        date.setCallBack(onDate);
        date.show(getSupportFragmentManager(), "Date Picker");
    }
}
