package com.yuneec.android;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import yuneec.utils.log.LogToFileTree;
import yuneec.utils.log.Timber;

public class MyApplication extends Application {
    private LogToFileTree logToFileTree;

    @Override
    public void onCreate() {
        super.onCreate();
        startLogFileTree();
    }

    private void startLogFileTree() {
        if (logToFileTree == null && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            logToFileTree = new LogToFileTree();
            Timber.plant(logToFileTree);
            logToFileTree.createFileStartLogging(this);
        }
    }

    private void stopLogFileTree() {
        if (logToFileTree != null)
            logToFileTree.stopLoggingThread();
    }

}
