package dev.sadovnikov.testretrofit;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class AirportsLoader extends BaseLoader {

    private static final String TAG = "AirportsLoader";

    private final AirportsService airportsService;

    public AirportsLoader(@NonNull Context context) {
        super(context);
        Log.d(TAG, "AirportsLoader: ");
        airportsService = ApiFactory.getAirportService();
    }

    @Override
    protected void onForceLoad() {
        Log.d(TAG, "onForceLoad: ");
        Call<List<Airport>> call = airportsService.getAirports();
        call.enqueue(new RetrofitCallback<List<Airport>>() {
            @Override
            public void onResponse(Call<List<Airport>> call, Response<List<Airport>> response) {
                super.onResponse(call, response);
                Log.d(TAG, "onResponse: " + response.isSuccessful());
                if (response.isSuccessful()) {
                    AirportsTable.clear(getContext());
                    AirportsTable.save(getContext(), response.body());
                    Cursor cursor = getContext().getContentResolver().query(AirportsTable.URI,
                            null, null, null, null);
                    deliverResult(cursor);
                } else {
                    deliverResult(null);
                }
            }
        });
    }

}
