package dev.sadovnikov.testretrofit;


import android.support.annotation.NonNull;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;


public class ApiFactory {
    private static final int CONNECT_TIMEOUT = 15;
    private static final int WRITE_TIMEOUT = 60;
    private static final int TIMEOUT = 60;

    private static final OkHttpClient CLIENT = new OkHttpClient();

    static {
        CLIENT.setConnectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        CLIENT.setConnectTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        CLIENT.setConnectTimeout(TIMEOUT, TimeUnit.SECONDS);
    }

    @NonNull
    public static AirportsService getAirportService() {
        return getRetrofit().create(AirportsService.class);
    }

    @NonNull
    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://api.travelpayouts.com/data/ru/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(CLIENT)
                .build();
    }
}
