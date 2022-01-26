package com.abgolor.utilities.currencywidget;

import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.util.logging.Handler;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetTodayCurrenciesPrices {


    private OkHttpClient okHttpClient;

    public GetTodayCurrenciesPrices(){
       okHttpClient = new OkHttpClient();
    }

    public void getBTCUSDPrices(){
        Request request = new Request.Builder()
                .url("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/btc/usd.json")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                HomeScreenWidetReceiver.BTCUSDPrice = null;
            }

            @Override
            public void onResponse(Call call, Response response){
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    HomeScreenWidetReceiver.BTCUSDPrice = String.valueOf((int)jsonObject.getDouble("usd"));
                    HomeScreenWidetReceiver.TodayBTCUSDPrice = jsonObject.getDouble("usd");
                } catch (Exception e){
                    HomeScreenWidetReceiver.BTCUSDPrice = null;
                    e.printStackTrace();
                }
            }
        });
    }

    public void getETHUSDPrices(){
        Request request = new Request.Builder()
                .url("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/eth/usd.json")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                HomeScreenWidetReceiver.ETHUSDPrice = null;
            }

            @Override
            public void onResponse(Call call, Response response){
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    HomeScreenWidetReceiver.ETHUSDPrice = String.valueOf((int)jsonObject.getDouble("usd"));
                    HomeScreenWidetReceiver.TodayETHUSDPrice = jsonObject.getDouble("usd");
                } catch (Exception e){
                    HomeScreenWidetReceiver.ETHUSDPrice = null;
                }
            }
        });
    }

    public void getGBPAEDPrices(){
        Request request = new Request.Builder()
                .url("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/gbp/aed.json")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                HomeScreenWidetReceiver.GBPAEDPrice = null;
            }

            @Override
            public void onResponse(Call call, Response response){
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    HomeScreenWidetReceiver.GBPAEDPrice = String.valueOf(jsonObject.getDouble("aed"));

                    Log.i("MAIN", "THE BODY IS: " + String.valueOf(jsonObject.getDouble("aed")));
                    HomeScreenWidetReceiver.TodayGBPAEDPrice = jsonObject.getDouble("aed");
                } catch (Exception e){
                    Log.i("MAIN", "ERROR IS " + e.getLocalizedMessage());
                    HomeScreenWidetReceiver.GBPAEDPrice = null;
                    e.printStackTrace();
                }
            }
        });
    }

    public void getEURGBPPrices(){

        Request request = new Request.Builder()
                .url("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/eur/gbp.json")
                .build();

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                HomeScreenWidetReceiver.EURGBPPrice = null;
            }

            @Override
            public void onResponse(Call call, Response response){
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    HomeScreenWidetReceiver.EURGBPPrice = String.valueOf(jsonObject.getDouble("gbp"));
                    HomeScreenWidetReceiver.TodayEURGBPPrice= jsonObject.getDouble("gbp");
                } catch (Exception e){
                    HomeScreenWidetReceiver.EURGBPPrice = null;
                }
            }
        });
    }

    public void getEURAEDPrices(){

        Request request = new Request.Builder()
                .url("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/eur/aed.json")
                .build();

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                HomeScreenWidetReceiver.EURAEDPrice = null;
            }

            @Override
            public void onResponse(Call call, Response response){
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    HomeScreenWidetReceiver.EURAEDPrice = String.valueOf(jsonObject.getDouble("aed"));
                    HomeScreenWidetReceiver.TodayEURAEDPrice = jsonObject.getDouble("aed");
                } catch (Exception e){
                    HomeScreenWidetReceiver.EURAEDPrice = null;
                }
            }
        });
    }

    public void getGBPUSDPrices(){
        Request request = new Request.Builder()
                .url("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/gbp/usd.json")
                .build();

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                HomeScreenWidetReceiver.GBPUSDPrice = null;
            }

            @Override
            public void onResponse(Call call, Response response){
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    HomeScreenWidetReceiver.GBPUSDPrice = String.valueOf(jsonObject.getDouble("usd"));
                    HomeScreenWidetReceiver.TodayGBPUSDPrice = jsonObject.getDouble("usd");
                } catch (Exception e){
                    HomeScreenWidetReceiver.GBPUSDPrice = null;
                }
            }
        });
    }
}
