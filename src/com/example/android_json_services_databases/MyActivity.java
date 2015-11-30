package com.example.android_json_services_databases;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import java.util.ArrayList;

public class MyActivity extends Service {
    /**
     * Called when the activity is first created.
     */
    ArrayList<String> mobject = new ArrayList<String>();

    @Override

        public void onCreate() {
            super.onCreate();
        //add three objects to an arraylist.
        mobject.add("Object one");
        mobject.add("object two ");
        mobject.add("object three");
    }

    public int onStartCommand(Intent intent, int flags, int startID)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent bf = new Intent();
                bf.setAction(MyActivity2.mBroadcastStringAction);
                bf.putExtra("data", "broadcast data");
                sendBroadcast(bf);

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bf.setAction(MyActivity2.mBroadcastIntegerAction);
                bf.putExtra("data", 10);
                sendBroadcast(bf);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bf.setAction(MyActivity2.mBroadcastArrayListAction);
                bf.putExtra("data", mobject);
                sendBroadcast(bf);

            }
        }).start();
        return START_NOT_STICKY;

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void onTaskRemoved(Intent root)
    {
        super.onTaskRemoved(root);
    }
    public void onDestroy()
    {
        super.onDestroy();
    }



}
