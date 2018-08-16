package com.angel.appexecutordemo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Adds task in Thread pool for network operations
         */
        new ThreadExecutors().networkIO().execute(new Runnable() {
            @Override
            public void run() {

            }
        });

        /**
         * Adds task in Main Thread pool for UI operations
         */
        new  ThreadExecutors().mainThread().execute(new Runnable() {
            @Override
            public void run() {

            }
        });


        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            Runnable worker = new WorkerThread("" + i);
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");

    }
}
