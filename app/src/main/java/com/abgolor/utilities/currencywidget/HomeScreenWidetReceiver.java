package com.abgolor.utilities.currencywidget;

import android.app.AppOpsManager;
import android.app.PendingIntent;
import android.app.usage.NetworkStatsManager;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import static android.app.AppOpsManager.MODE_ALLOWED;

public class HomeScreenWidetReceiver extends AppWidgetProvider {

    public static String BTCUSDPrice, ETHUSDPrice, GBPAEDPrice, EURGBPPrice, EURAEDPrice, GBPUSDPrice;

    public static double YesterdayBTCUSDPrice, YesterdayETHUSDPrice, YesterdayGBPAEDPrice,
            YesterdayEURGBPPrice, YesterdayEURAEDPrice, YesterdayGBPUSDPrice;

    public static double TodayBTCUSDPrice, TodayETHUSDPrice, TodayGBPAEDPrice,
            TodayEURGBPPrice, TodayEURAEDPrice, TodayGBPUSDPrice;

    private static int BTCUSDIndex, ETHUSDPriceIndex, GBPAEDPriceIndex,
            EURGBPPriceIndex, EURAEDPriceIndex, GBPUSDPriceIndex;

    private Context context;
    private GetTodayCurrenciesPrices getTodayCurrenciesPrices;
    private GetYesterdayCurrenciesPrices getYesterdayCurrenciesPrices;
    private RemoteViews remoteViews;

    AppWidgetManager appWidgetManager;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        this.context = context;
        getTodayCurrenciesPrices = new GetTodayCurrenciesPrices();
        getYesterdayCurrenciesPrices = new GetYesterdayCurrenciesPrices();
        this.appWidgetManager = appWidgetManager;

        remoteViews = new RemoteViews(context.getPackageName(), R.layout.sample);


        getTodayPrices();
        getYesterdayPrices();

        sample(appWidgetIds);

        for (int appWidgetId : appWidgetIds) {

            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);

            Intent refreshIntent = new Intent(context, RefreshReceiver.class);
            PendingIntent refreshPendingIntent = PendingIntent.getBroadcast(context, 1, refreshIntent, 0);
            remoteViews.setOnClickPendingIntent(R.id.refresh, refreshPendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                }
            }, 5000);

        }
    }

    private void sample(int[] appWidgetIds) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (BTCUSDPrice != null && ETHUSDPrice != null &&
                        EURGBPPrice != null && EURAEDPrice != null && GBPUSDPrice != null) {
                    getCurrentCurrencyIndex();
                    getInfo(appWidgetIds);
                }
            }
        }, 2000);
    }

    private void getInfo(int[] appWidgetIds) {

        for (int appWidgetId : appWidgetIds) {
            remoteViews.setCharSequence(R.id.bitcoinPrice, "setText", "Bitcoin - $" + BTCUSDPrice);
            remoteViews.setTextViewText(R.id.ethereumPrice, "Ethereum - $" + ETHUSDPrice);
            remoteViews.setTextViewText(R.id.gbpAEDPrice, "GBPAED - " + TodayGBPAEDPrice);
            remoteViews.setTextViewText(R.id.eurGbpPrice, "EURGBP - " + EURGBPPrice);
            remoteViews.setTextViewText(R.id.euraed, "EURAED - " + EURAEDPrice);
            remoteViews.setTextViewText(R.id.gbpUsdPrice, "GBPUSD - " + GBPUSDPrice);


            if (BTCUSDIndex == 1) {
                remoteViews.setTextColor(R.id.bitcoinPrice, ContextCompat.getColor(context, R.color.green));
            } else if (BTCUSDIndex == -1) {
                remoteViews.setTextColor(R.id.bitcoinPrice, ContextCompat.getColor(context, R.color.red));
            }

            if (ETHUSDPriceIndex == 1) {
                remoteViews.setTextColor(R.id.ethereumPrice, ContextCompat.getColor(context, R.color.green));
            } else if (ETHUSDPriceIndex == -1) {
                remoteViews.setTextColor(R.id.ethereumPrice, ContextCompat.getColor(context, R.color.red));
            }

            if (GBPAEDPriceIndex == 1) {
                remoteViews.setTextColor(R.id.gbpAEDPrice, ContextCompat.getColor(context, R.color.green));
            } else if (GBPAEDPriceIndex == -1) {
                remoteViews.setTextColor(R.id.gbpAEDPrice, ContextCompat.getColor(context, R.color.red));
            }

            if (EURGBPPriceIndex == 1) {
                remoteViews.setTextColor(R.id.eurGbpPrice, ContextCompat.getColor(context, R.color.green));
            } else if (EURAEDPriceIndex == -1) {
                remoteViews.setTextColor(R.id.eurGbpPrice, ContextCompat.getColor(context, R.color.red));
            }

            if (EURAEDPriceIndex == 1) {
                remoteViews.setTextColor(R.id.euraed, ContextCompat.getColor(context, R.color.green));
            } else if (EURAEDPriceIndex == -1) {
                remoteViews.setTextColor(R.id.euraed, ContextCompat.getColor(context, R.color.red));
            }

            if (GBPUSDPriceIndex == 1) {
                remoteViews.setTextColor(R.id.gbpUsdPrice, ContextCompat.getColor(context, R.color.green));
            } else if (GBPUSDPriceIndex == -1) {
                remoteViews.setTextColor(R.id.gbpUsdPrice, ContextCompat.getColor(context, R.color.red));
            }

            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
    }

    private int checkGainLossEqual(double todayPrice, double yesterdayPrice) {
        if (todayPrice > yesterdayPrice) {
            return 1; //Profit
        } else if (todayPrice < yesterdayPrice) {
            return -1; //Loss
        }
        return 0; //Equal
    }

    private void getTodayPrices() {
        getTodayCurrenciesPrices.getGBPAEDPrices();
        getTodayCurrenciesPrices.getBTCUSDPrices();
        getTodayCurrenciesPrices.getETHUSDPrices();
        getTodayCurrenciesPrices.getEURAEDPrices();
        getTodayCurrenciesPrices.getEURGBPPrices();
        getTodayCurrenciesPrices.getGBPAEDPrices();
        getTodayCurrenciesPrices.getGBPUSDPrices();
    }

    private void getCurrentCurrencyIndex() {
        BTCUSDIndex = checkGainLossEqual(TodayBTCUSDPrice, YesterdayBTCUSDPrice);
        ETHUSDPriceIndex = checkGainLossEqual(TodayETHUSDPrice, YesterdayETHUSDPrice);
        ETHUSDPriceIndex = checkGainLossEqual(TodayGBPAEDPrice, YesterdayGBPAEDPrice);
        EURAEDPriceIndex = checkGainLossEqual(TodayEURAEDPrice, YesterdayEURAEDPrice);
        EURGBPPriceIndex = checkGainLossEqual(TodayEURGBPPrice, YesterdayEURGBPPrice);
        GBPAEDPriceIndex = checkGainLossEqual(TodayGBPAEDPrice, YesterdayGBPAEDPrice);
        GBPUSDPriceIndex = checkGainLossEqual(TodayGBPUSDPrice, YesterdayGBPUSDPrice);
    }


    private void getYesterdayPrices() {
        getYesterdayCurrenciesPrices.getGBPAEDPrices();
        getYesterdayCurrenciesPrices.getBTCUSDPrices();
        getYesterdayCurrenciesPrices.getETHUSDPrices();
        getYesterdayCurrenciesPrices.getEURAEDPrices();
        getYesterdayCurrenciesPrices.getEURGBPPrices();
        getYesterdayCurrenciesPrices.getGBPAEDPrices();
        getYesterdayCurrenciesPrices.getGBPUSDPrices();
    }


}
