package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.utility.Sorter;

public class SortingActivity extends AppCompatActivity {

    EditText edit;
    long startTime;
    long endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorting);

        edit = (EditText) findViewById(R.id.editText2);
    }

    private int[] array;

    // Reference: http://www.java2novice.com/java-sorting-algorithms/merge-sort/
    public void mergeSort(View v) {
        array = getAsArray();

        startTime = System.nanoTime();
        array = Sorter.mergeSort(array);
        endTime = System.nanoTime();

        showResults();
    }

    public void quickSort(View v) {
        array = getAsArray();

        startTime = System.nanoTime();
        array = Sorter.quickSort(array);
        endTime = System.nanoTime();

        showResults();
    }

    /**
     * Converts and returns an int array from what was entered by the user
     * @return
     */
    private int[] getAsArray() {
        String input = edit.getText().toString();
        String[] split = input.split("\\s+");
        int[] ints = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            ints[i] = Integer.valueOf(split[i]);
        }

        return ints;
    }

    private void showResults() {
        StringBuilder sb = new StringBuilder();
        for (int i : array) {
            sb.append(i + " ");
        }

        long elapsedTime = endTime - startTime;
        sb.append("Elapsed time " + elapsedTime);

        TextView tv = (TextView) findViewById(R.id.textView3);
        tv.setText(sb.toString());
        tv.setVisibility(View.VISIBLE);
    }
}
