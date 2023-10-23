package com.chaquo.python.jupyter;

import android.app.*;
import android.content.*;
import android.os.*;
import android.support.annotation.*;
import android.support.v4.app.*;
import android.util.*;
import com.chaquo.python.*;
import com.chaquo.python.demo.*;

public class KernelService extends Service {
    private static final int NOTIFICATION_ID = 1;
    public static final String TAG = "KernelService";

    private boolean running = false;

    @Override public int onStartCommand(final Intent intent, int flags, int startId) {
        if (running) {
            Log.e(TAG, "Ignoring start request: kernel is already running");
        } else {
            Log.i(TAG, "Starting kernel");
            running = true;

            // startForeground should make the OS less likely to shut the process down.
            NotificationCompat.Builder builder = new NotificationCompat.Builder
                (this, App.DEFAULT_CHANNEL);
            builder.setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Kernel running");
            startForeground(NOTIFICATION_ID, builder.build());

            new Thread() {
                @Override public void run() {
                    try {
                        Python.getInstance().getModule("chaquopy.jupyter.kernel")
                            .callAttr("main", intent);
                        Log.i(TAG, "Kernel exited normally");
                    } catch (Exception e) {
                        Log.e(TAG, "Kernel exited abnormally", e);
                    } finally {
                        running = false;
                        stopSelf();
                    }
                }
            }.start();
        }
        return START_NOT_STICKY;
    }

    @Override public void onDestroy() {
        if (running) {
            Log.w(TAG, "Received kill request");
        }
        stopForeground(true);

        // The kernel is not designed to run multiple times in the same process.
        System.exit(0);
    }

    // Not used.
    @Nullable @Override public IBinder onBind(Intent intent) {
        return null;
    }
}
