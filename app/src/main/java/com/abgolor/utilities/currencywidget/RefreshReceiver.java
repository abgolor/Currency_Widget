package com.abgolor.utilities.currencywidget;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class RefreshReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Refreshing...", Toast.LENGTH_SHORT).show();
       Intent homeScreenWidgetIntent = new Intent(context, HomeScreenWidetReceiver.class);
        homeScreenWidgetIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        int[] ids = AppWidgetManager.getInstance(context)
                .getAppWidgetIds(new ComponentName(context, HomeScreenWidetReceiver.class));
        homeScreenWidgetIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        context.sendBroadcast(homeScreenWidgetIntent);
    }
}
