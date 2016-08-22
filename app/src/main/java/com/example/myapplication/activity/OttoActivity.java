package com.example.myapplication.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.squareup.otto.Bus;
import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;
import com.squareup.otto.ThreadEnforcer;

public class OttoActivity extends AppCompatActivity {

    public static Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otto);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
        }
        bus = new Bus(ThreadEnforcer.MAIN);
        bus.register(this);
    }

    @Subscribe
    public void getMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_otto, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            TestData t = new TestData();
            t.message = "Hello from the activity";
            bus.post(t);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class TestData {
        public String message;
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_otto, container, false);
            View button = rootView.findViewById(R.id.fragmentbutton);
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    bus.post("Hello from the Fragment");
                }
            });
            bus.register(this);
            return rootView;
        }

        @Subscribe
        public void getMessage(OttoActivity.TestData data) {
            Toast.makeText(getActivity(), data.message, Toast.LENGTH_LONG).show();
        }
    }

    @Produce
    public String produceEvent() {
        return "Starting up";
    }

}
