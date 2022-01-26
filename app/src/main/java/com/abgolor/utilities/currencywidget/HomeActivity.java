package com.abgolor.utilities.currencywidget;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        AppWidgetManager manager = getSystemService(AppWidgetManager.class);
        ComponentName provider = new ComponentName(HomeActivity.this, HomeScreenWidetReceiver.class);
        Bundle extra = new Bundle();
        extra.putString("ggg", "ggg");
            if(manager.isRequestPinAppWidgetSupported()){
                Intent intent = new Intent(HomeActivity.this, HomeScreenWidetReceiver.class);
                PendingIntent successCallBack = PendingIntent.getBroadcast(HomeActivity.this, 0,
                        intent, 0);
                manager.requestPinAppWidget(provider, extra, successCallBack);
              } else {
                Log.d(TAG, "request pin app not supported");
            }
        }
    }
}