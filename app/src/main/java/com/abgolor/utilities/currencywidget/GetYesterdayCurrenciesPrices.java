package com.abgolor.utilities.currencywidget;

import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetYesterdayCurrenciesPrices {

    private OkHttpClient okHttpClient;
    String yesterdayDate;

    public GetYesterdayCurrenciesPrices(){
        okHttpClient = new OkHttpClient();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        yesterdayDate = dateFormat.format(calendar.getTime());
    }

    public void getBTCUSDPrices(){
        Request request = new Request.Builder()
                .url("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/" + yesterdayDate + "/currencies/btc/usd.json")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                HomeScreenWidetReceiver.YesterdayBTCUSDPrice = 0;
            }

            @Override
            public void onResponse(Call call, Response response){
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    HomeScreenWidetReceiver.YesterdayBTCUSDPrice = jsonObject.getDouble("usd");
                } catch (Exception e){
                    HomeScreenWidetReceiver.YesterdayBTCUSDPrice = 0;
                }
            }
        });
    }

    public void getETHUSDPrices(){
        Request request = new Request.Builder()
                .url("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/+" + yesterdayDate + "/currencies/eth/usd.json")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                HomeScreenWidetReceiver.YesterdayETHUSDPrice = 0;
            }

            @Override
            public void onResponse(Call call, Response response){
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    HomeScreenWidetReceiver.YesterdayETHUSDPrice = jsonObject.getDouble("usd");
                } catch (Exception e){
                    HomeScreenWidetReceiver.YesterdayETHUSDPrice = 0;
                }
            }
        });
    }

    public void getGBPAEDPrices(){
        Request request = new Request.Builder()
                .url("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/"+ yesterdayDate +"/gbp/aed.json")
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
                    HomeScreenWidetReceiver.GBPAEDPrice = jsonObject.getString("aed");
                } catch (Exception e){
                    HomeScreenWidetReceiver.GBPAEDPrice = null;
                }
            }
        });
    }

    public void getEURGBPPrices(){

        Request request = new Request.Builder()
                .url("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/"+ yesterdayDate +"/currencies/eur/gbp.json")
                .build();

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                HomeScreenWidetReceiver.YesterdayEURGBPPrice = 0;
            }

            @Override
            public void onResponse(Call call, Response response){
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    HomeScreenWidetReceiver.YesterdayEURGBPPrice = jsonObject.getDouble("gbp");
                } catch (Exception e){
                    HomeScreenWidetReceiver.YesterdayEURGBPPrice = 0;
                }
            }
        });
    }

    public void getEURAEDPrices(){

        Request request = new Request.Builder()
                .url("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/"+ yesterdayDate +"/currencies/eur/aed.json")
                .build();

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                HomeScreenWidetReceiver.YesterdayEURAEDPrice = 0;
            }

            @Override
            public void onResponse(Call call, Response response){
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    HomeScreenWidetReceiver.YesterdayEURAEDPrice = jsonObject.getDouble("aed");
                } catch (Exception e){
                    HomeScreenWidetReceiver.YesterdayEURAEDPrice = 0;
                }
            }
        });
    }

    public void getGBPUSDPrices(){
        Request request = new Request.Builder()
                .url("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/"+ yesterdayDate +"/currencies/gbp/usd.json")
                .build();

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                HomeScreenWidetReceiver.YesterdayGBPUSDPrice = 0;
            }

            @Override
            public void onResponse(Call call, Response response){
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    HomeScreenWidetReceiver.YesterdayGBPUSDPrice = jsonObject.getDouble("usd");
                } catch (Exception e){
                    HomeScreenWidetReceiver.YesterdayGBPUSDPrice = 0;
                }
            }
        });
    }

}
