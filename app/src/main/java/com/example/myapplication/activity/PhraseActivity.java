package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.myapplication.R;
import com.squareup.phrase.Phrase;

public class PhraseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrase);

        // Call put(...) in any order
        CharSequence greeting = Phrase.from(this, R.string.greeting)
                .put("unit", "apples")
                .put("name", "Jiechao")
                .put("yield", "3")
                .format();

        TextView tv1 = (TextView) findViewById(R.id.textView6);
        tv1.setText(greeting);

        CharSequence welcome = Phrase.from("<font color=\"red\">Welcome</font> back {user}.")
                .put("user", "Nancy")
                .format();
        ((TextView) findViewById(R.id.textView7)).setText(welcome);
    }
}
