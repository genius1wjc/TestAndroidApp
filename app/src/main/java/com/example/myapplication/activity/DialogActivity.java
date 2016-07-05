package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.example.myapplication.MyDialogFragment;
import com.example.myapplication.R;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        ToggleButton toggle = (ToggleButton)findViewById(R.id.toggleButton);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    // Need to call "getSupportFragmentManager()" rather than "getFragmentManager()"
                    FragmentManager fm = getSupportFragmentManager();
                    MyDialogFragment df = new MyDialogFragment();
                    Bundle args = new Bundle();
                    args.putString("Type", "Alert");
                    args.putString("Title", "Fire?");
                    args.putInt("Count", 3);
                    df.setArguments(args);
                    df.show(fm, "A tag");
                }
            }
        });
    }
}
