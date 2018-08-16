package com.angel.appexecutordemo;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadExecutors {

    private static final int THREAD_COUNT = 3;

    private final Executor dbThread;

    private final Executor networkThread;

    private final Executor mainThread;

    ThreadExecutors(Executor diskIO, Executor networkIO, Executor mainThread) {
        this.dbThread = diskIO;
        this.networkThread = networkIO;
        this.mainThread = mainThread;
    }

    public ThreadExecutors() {
        this(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(THREAD_COUNT),
                new MainThreadExecutor());
    }

    public Executor diskIO() {
        return dbThread;
    }

    public Executor networkIO() {
        return networkThread;
    }

    public Executor mainThread() {
        return mainThread;
    }

    private static class MainThreadExecutor implements Executor {
        private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}