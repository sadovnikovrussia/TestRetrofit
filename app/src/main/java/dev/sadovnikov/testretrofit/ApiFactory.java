package dev.sadovnikov.testretrofit;


import android.support.annotation.NonNull;



import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiFactory {

    private static final OkHttpClient CLIENT = new OkHttpClient();

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
