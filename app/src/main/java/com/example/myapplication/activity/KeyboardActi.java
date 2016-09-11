package com.example.myapplication.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;

public class KeyboardActi extends AppCompatActivity {

    EditText pw;
    InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);

        pw = (EditText) findViewById(R.id.pw);
        pw.setImeOptions(EditorInfo.IME_ACTION_DONE); // Hide the "Next" key and add "Done" instead
        if (pw.requestFocus()) {
            imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(pw, InputMethodManager.SHOW_IMPLICIT);
        }
        else
            showToast("Oh, unable to show the keyboard");
    }

    public void hideKeyboard(View v) {
        imm.hideSoftInputFromWindow(pw.getWindowToken(), 0);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_J:
                if (event.isShiftPressed()) {
                    showToast("Shift + J");
                } else {
                    showToast("J");
                }
                return true;
            case KeyEvent.KEYCODE_K:
                if (event.isCtrlPressed()) {
                    showToast("Ctrl + K");
                } else {
                    showToast("K");
                }
                return true;
            default:
                return super.onKeyUp(keyCode, event);
        }
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
