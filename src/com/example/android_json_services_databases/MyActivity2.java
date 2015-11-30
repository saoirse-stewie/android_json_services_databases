package com.example.android_json_services_databases;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by saoirse on 28/11/2015.
 */
public class MyActivity2 extends Activity {

    public static String mBroadcastStringAction = "com.example.android_json_service_databases.string";
    public static String mBroadcastIntegerAction = "com.example.android_json_service_databases.integer";
    public static String mBroadcastArrayListAction = "com.example.android_json_service_databases.arraylist";

    private IntentFilter intentf;
    public HashMap<String,String> inputmap;
    private TextView tv;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        inputmap = new HashMap<String, String>();

        tv = (TextView) findViewById(R.id.txt);

        intentf = new IntentFilter();

        intentf.addAction(mBroadcastStringAction);
        intentf.addAction(mBroadcastIntegerAction);
        intentf.addAction(mBroadcastArrayListAction);

        Intent service = new Intent(this, MyActivity.class);
        startService(service);
    }

    public void onResume()
    {
        super.onResume();
        registerReceiver(mReciever, intentf);
    }
    private BroadcastReceiver mReciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            tv.setText(tv.getText()+ "broadcast service \n");
            if(intent.getAction().equals(mBroadcastStringAction)) {
                tv.setText(tv.getText()+ intent.getStringExtra("data"));
                inputmap.put(tv.getText().toString(), intent.getStringExtra("data"));
            }
            else if (intent.getAction().equals(mBroadcastIntegerAction)) {
                tv.setText(tv.getText().toString() + intent.getIntExtra("data", 0));
                inputmap.put(tv.getText().toString(), String.valueOf(intent.getIntExtra("data",0)));
            }
           else if(intent.getAction().equals(mBroadcastArrayListAction)) {
                tv.setText(tv.getText() + intent.getStringArrayListExtra("data").toString());
                inputmap.put(tv.getText().toString(), intent.getStringArrayListExtra("data").toString());
            }
            JSONObject job = new JSONObject(inputmap);
            //String json = HttpUtils.urlContentPost(url, "loanInputs", job.toString());
            Intent i = new Intent(MyActivity2.this, MyActivity.class);
            stopService(i);
        }};

    protected  void onPause()
    {
        unregisterReceiver(mReciever);
        super.onPause();
    }

    }






