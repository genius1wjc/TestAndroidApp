package com.example.myapplication.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.StackOverflowAPI;
import com.example.myapplication.module.StackOverflowQuestions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActi extends ListActivity implements Callback<StackOverflowQuestions> {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        ArrayAdapter<StackOverflowQuestions.Question> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                new ArrayList<StackOverflowQuestions.Question>());
        setListAdapter(arrayAdapter);
        setProgressBarIndeterminateVisibility(true);
        setProgressBarVisibility(true);

        setContentView(R.layout.activity_retrofit);

        loadQuestions();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_question, menu);
        return true;
    }

    private void loadQuestions() {
        setProgressBarIndeterminateVisibility(true);
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.stackexchange.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        // prepare call in Retrofit 2.0
        StackOverflowAPI stackOverflowAPI = retrofit.create(StackOverflowAPI.class);

        Call<StackOverflowQuestions> call = stackOverflowAPI.loadQuestions("android");
        //asynchronous call
        call.enqueue(this);

        // synchronous call would be with execute, in this case you
        // would have to perform this outside the main thread
        // call.execute()

        // to cancel a running request
        // call.cancel();
        // calls can only be used once but you can easily clone them
        //Call<StackOverflowQuestions> c = call.clone();
        //c.enqueue(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        loadQuestions();
        return true;
    }


    @Override
    public void onResponse(Call<StackOverflowQuestions> call, Response<StackOverflowQuestions> response) {
        setProgressBarIndeterminateVisibility(false);
        ArrayAdapter<StackOverflowQuestions> adapter = (ArrayAdapter<StackOverflowQuestions>) getListAdapter();
        adapter.clear();
        adapter.addAll(response.body());

        //int code = response.code();
        //if (code == 200) {
        //} else {
        //}
    }

    @Override
    public void onFailure(Call<StackOverflowQuestions> call, Throwable t) {
        Toast.makeText(RetrofitActi.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }
}
