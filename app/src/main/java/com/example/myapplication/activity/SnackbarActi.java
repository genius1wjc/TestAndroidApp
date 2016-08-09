package com.example.myapplication.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;

public class SnackbarActi extends AppCompatActivity {

    CoordinatorLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);

        layout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
    }

    public void showSnackbar(View v) {
        Snackbar snackbar = Snackbar.make(layout, "Yes you did it!", Snackbar.LENGTH_LONG)
                .setAction("Show another", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar.make(layout, "Yes you did it again!", Snackbar.LENGTH_INDEFINITE).show();
                    }
                });

        // Changing action button text color
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }
}
