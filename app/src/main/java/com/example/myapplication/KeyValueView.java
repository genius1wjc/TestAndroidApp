package com.example.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class KeyValueView extends LinearLayout {
    private String leftLabel = "";
    private String rightLabel = "";
    private TextView leftTextView;
    private TextView rightTextView;

    public KeyValueView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.key_value_layout, this);
    }

    public KeyValueView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context, attrs);
    }

    public KeyValueView(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
        initViews(context, attrs);
    }

    private void initViews(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.keyValueView, 0, 0);

        try {
            // get the text and colors specified using the names in attrs.xml
            leftLabel = a.getString(R.styleable.keyValueView_leftLabel);
            rightLabel = a.getString(R.styleable.keyValueView_rightLabel);

        } finally {
            a.recycle();
        }

        LayoutInflater.from(context).inflate(R.layout.key_value_layout, this);

        //left text view
        leftTextView = (TextView) this.findViewById(R.id.leftTextView);
        leftTextView.setText(leftLabel);

        //right text view
        rightTextView = (TextView) this.findViewById(R.id.rightTextView);
        rightTextView.setText(rightLabel);
    }

    public String getLeftLabel() {
        return leftLabel;
    }

    public void setLeftLabel(String leftLabel) {
        this.leftLabel = leftLabel;
        if(leftTextView!=null){
            leftTextView.setText(leftLabel);
        }
    }

    public String getRightLabel() {
        return rightLabel;
    }

    public void setRightLabel(String rightLabel) {
        this.rightLabel = rightLabel;
        if(rightTextView!=null){
            rightTextView.setText(rightLabel);
        }
    }
}