package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FutureCallableActi extends AppCompatActivity {

    private static final String TAG = FutureCallableActi.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future_callable);
    }

    public void calculate(View v) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        List<Future<Integer>> results = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            Integer number = random.nextInt(10);
            Log.i(TAG, "Number is " + number);
            FactorialCalculator calculator = new FactorialCalculator(number);
            Future<Integer> result = executor.submit(calculator);
            results.add(result);
        }

        for (Future<Integer> future : results) {
            try {
                // future.cancel(true);
                // get() method waits until the Callable object has finished the execution of the call() method and has returned its result
                Log.i(TAG, "Future result is " + future.get() + "; and task done is " + future.isDone());
            }
            catch (InterruptedException | ExecutionException e) {
                // InterruptedException: if thread is interrupted while the get() method is waiting for the result
                // ExecutionException: if call() method throws an exception
                Log.e(TAG, e.getLocalizedMessage());
            }
        }
    }

    private class FactorialCalculator implements Callable<Integer> {
        private Integer number;

        FactorialCalculator(Integer number) {
            this.number = number;
        }

        @Override
        public Integer call() throws Exception {
            int result = 1;
            if (number != 0 && number != 1) {
                for (int i = 1; i <= number; i++) {
                    result *= i;
                    TimeUnit.MILLISECONDS.sleep(20);
                }
            }
            return result;
        }
    }
}
