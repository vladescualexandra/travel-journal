package com.example.travel_journal.network;

import com.example.travel_journal.BuildConfig;


import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {

    private static void getWeather() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://community-open-weather-map.p.rapidapi.com/weather?q=London%2Cuk&lat=0&lon=0&callback=test&id=2172797&lang=null&units=%22metric%22%20or%20%22imperial%22&mode=xml%2C%20html")
                .get()
                .addHeader("x-rapidapi-key", "1fd07f8cc8mshe656324f05790f5p19da3ejsncf5353c7616c")
                .addHeader("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com")
                .build();

        try {
            Response response = client.newCall(request).execute();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static OkHttpClient.Builder getClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(interceptor);
        }
        return client;
    }
}
