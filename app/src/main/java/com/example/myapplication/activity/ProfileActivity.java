package com.example.myapplication.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Fragment.AutoCompleteFragment;
import com.example.myapplication.task.DownloadImageTask;
import com.example.myapplication.R;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "Profile";

    static final String INTENT_KEY = "KEY";
    static final int SHOW_DB_REQUEST = 1;  // The request code

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;

    private DownloadImageTask task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.RED);
        }

        addDrawer();

        setupDrawer();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    private void addDrawer() {
        ListView mDrawerList = (ListView)findViewById(R.id.navList);
        ArrayAdapter<String> mAdapter;
        String[] osArray = { "Loader", "Dialog", "Recycler", "Tabbed", "Custom List", "Tasks", "Animation", "Cache", "Database", "Music Play", "My List", "Broadcast", "Grid",
                "Screen Size", "REST", "Gson", "Map", "Content" };
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "Clicked position: " + position + " id: " + id);

                String text = ((TextView)view).getText().toString();

                switch (text) {
                    case "Loader":
                        startActivity(new Intent(ProfileActivity.this, LoaderActivity.class));
                        break;
                    case "Dialog":
                        startActivity(new Intent(ProfileActivity.this, DialogActivity.class));
                        break;
                    case "Recycler":
                        startActivity(new Intent(ProfileActivity.this, RecyclerViewActi.class));
                        break;
                    case "Tabbed":
                        startActivity(new Intent(ProfileActivity.this, TabbedActivity.class));
                        break;
                    case "Custom List":
                        startActivity(new Intent(ProfileActivity.this, CustomListAct.class));
                        break;
                    case "Tasks":
                        startActivity(new Intent(ProfileActivity.this, TasksActivity.class));
                        break;
                    case "Animation":
                        startActivity(new Intent(ProfileActivity.this, PropAnimActivity.class));
                        break;
                    case "Cache":
                        startActivity(new Intent(ProfileActivity.this, CacheActivity.class));
                        break;
                    case "Database":
                        startActivity(new Intent(ProfileActivity.this, DatabaseActivity.class));
                        break;
                    case "Music Play":
                        startActivity(new Intent(ProfileActivity.this, MusicPlayActivity.class));
                        break;
                    case "My List":
                        startActivity(new Intent(ProfileActivity.this, MyListActivity.class));
                        break;
                    case "Broadcast":
                        startActivity(new Intent(ProfileActivity.this, BroadcastAct.class));
                        break;
                    case "Grid":
                        startActivity(new Intent(ProfileActivity.this, GridActivity.class));
                        break;
                    case "Screen Size":
                        startActivity(new Intent(ProfileActivity.this, ScreenSizeActivity.class));
                        break;
                    case "Rest":
                        startActivity(new Intent(ProfileActivity.this, RestActivity.class));
                        break;
                    case "Gson":
                        startActivity(new Intent(ProfileActivity.this, GsonActivity.class));
                        break;
                    case "Map":
                        startActivity(new Intent(ProfileActivity.this, MapActivity.class));
                        break;
                    case "Content":
                        startActivity(new Intent(ProfileActivity.this, ContentActivity.class));
                        break;
                    default:
                        Log.w(TAG, "No such activity");
                }
            }
        });
    }

    private void setupDrawer() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onStart() {
        super.onStart();

        task = new DownloadImageTask(R.id.imageView, this);
        task.execute("http://www.flooringvillage.co.uk/ekmps/shops/flooringvillage/images/request-a-sample--547-p.jpg");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        task.cancel(true);
    }

    /**
     * Shows Database activity on click
     * @param v
     */
    public void showDBView(View v) {
        Intent intent = new Intent(this, DatabaseActivity.class);
        intent.putExtra(INTENT_KEY, "some random string");
        startActivityForResult(intent, SHOW_DB_REQUEST);
    }

    /**
     *
     * @param view
     */
    public void sendEmail(View view) {
        String[] TO = {""};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SHOW_DB_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
            }
        }
    }

    /**
     * Toggles the visibility of the fragment
     * @param v
     */
    public void toggleFragment(View v) {
        FragmentManager fManager = getSupportFragmentManager();
        Fragment f = fManager.findFragmentByTag("frag");
        if(f != null && f instanceof AutoCompleteFragment) {
            fManager.beginTransaction().remove(f).commit();
        }
        else {
            Fragment fragment = new AutoCompleteFragment();
            fManager.beginTransaction().add(R.id.container, fragment, "frag").commit();
        }
    }
}
