package com.example.myapplication.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.fragment.AutoCompleteFragment;
import com.example.myapplication.fragment.DatePickerFragment;

public class FragmentActi extends AppCompatActivity {

    FragmentManager fm;
    Button button1;
    Button button2;
    Button button3;

    private static final String TAG_FRA = "A Tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        fm = getSupportFragmentManager();
        button1 = (Button) findViewById(R.id.button18);
        button2 = (Button) findViewById(R.id.button19);
        button3 = (Button) findViewById(R.id.button20);
    }

    public void add1(View v) {
        // Enables button 3 since we will add a new transaction
        button3.setEnabled(true);

        if (fm.findFragmentByTag(TAG_FRA) == null || !fm.findFragmentByTag(TAG_FRA).isVisible()) {
            AutoCompleteFragment fragment = new AutoCompleteFragment();
            fm.beginTransaction().add(R.id.container, fragment, TAG_FRA).addToBackStack(null).commit();
        }
        else {
            Fragment fragment = fm.findFragmentByTag(TAG_FRA);
            fm.beginTransaction().remove(fragment).addToBackStack(null).commit();
        }

        if (button1.getText().equals("Add 1"))
            button1.setText("Remove 1");
        else
            button1.setText("Add 1");
    }

    public void replaceWith2(View v) {
        if (fm.findFragmentByTag(TAG_FRA) != null && fm.findFragmentByTag(TAG_FRA).isVisible()) {
            DatePickerFragment dateFragment = new DatePickerFragment();
            fm.beginTransaction().replace(R.id.container, dateFragment).addToBackStack(null).commit();

            button3.setEnabled(true);
        }
        else {
            Snackbar.make(findViewById(R.id.window), "Nothing to replace!", Snackbar.LENGTH_LONG)
                    .setActionTextColor(Color.RED).show();
        }
    }

    public void popTop(View v) {
        if (fm.getBackStackEntryCount() == 0)
            button3.setEnabled(false);

        fm.popBackStack();

    }
}
