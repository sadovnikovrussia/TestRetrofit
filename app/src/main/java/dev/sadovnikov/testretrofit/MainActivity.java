package dev.sadovnikov.testretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class MainActivity extends AppCompatActivity implements Callback<List<Airport>> {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AirportsService airportsService = ApiFactory.getAirportService();
        Call<List<Airport>> call = airportsService.getAirports();
        Log.d(TAG, "onCreate: " +call.toString());
        call.enqueue(this);
    }

    @Override
    public void onResponse(Response<List<Airport>> response) {
        if (response.isSuccess()) {
            List<Airport> airports = response.body();

            Log.d(TAG, "onResponse: " + response.headers() + ", " + response.message() + ", " + response.raw() + ", " + response.body());
            Log.d(TAG, "onResponse: " + airports);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        Log.w(TAG, "onFailure: ", t);
    }
}
