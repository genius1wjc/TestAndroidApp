package com.example.myapplication.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.myapplication.R;

/**
 *
 */
public class AutoCompleteFragment extends Fragment {
    String[] cities = { "New York City ", "Atlanta", "Beijing", "Nanning", "Chicago", "San Francisco" };

    public AutoCompleteFragment() {
        // Required empty public constructor
    }

    public static AutoCompleteFragment newInstance(String param1, String param2) {
        AutoCompleteFragment fragment = new AutoCompleteFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auto_complete, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);

        AutoCompleteTextView text = (AutoCompleteTextView)getActivity().findViewById(R.id.autoCompleteTextView);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, cities);
        text.setAdapter(adapter);
        text.setThreshold(1);
    }
}
