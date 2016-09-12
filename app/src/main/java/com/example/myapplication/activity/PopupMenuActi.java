package com.example.myapplication.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.myapplication.R;

public class PopupMenuActi extends AppCompatActivity {
    private Button btnFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_menu);
        // Locate filter button and attach click listener
        btnFilter = (Button) findViewById(R.id.btnFilter);
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFilterPopup(v);
            }
        });
    }

    private void showFilterPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        // Inflate the menu from xml
        popup.getMenuInflater().inflate(R.menu.popup_filters, popup.getMenu());
        // Setup menu item selection

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_keyword:
                        Toast.makeText(PopupMenuActi.this, "Keyword!", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.menu_popularity:
                        Toast.makeText(PopupMenuActi.this, "Popularity!", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });

        popup.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                Toast.makeText(PopupMenuActi.this, "Dismiss!", Toast.LENGTH_SHORT).show();
            }
        });

        // Show the menu
        popup.show();
    }

    // Display popup attached to the button as a position anchor
    public void displayPopupWindow(View anchorView) {
        PopupWindow popup = new PopupWindow(PopupMenuActi.this);
        View layout = getLayoutInflater().inflate(R.layout.popup_content, null);
        popup.setContentView(layout);
        // Set content width and height
        popup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        // Closes the popup window when touch outside of it - when looses focus
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        // Show anchored to button
        popup.setBackgroundDrawable(new BitmapDrawable());
        popup.showAsDropDown(anchorView);
    }
}
