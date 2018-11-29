package dev.sadovnikov.testretrofit;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;

import java.util.List;

import retrofit.Call;
import retrofit.Response;

public class AirportsLoader extends BaseLoader {

    private final AirportsService airportsService;

    public AirportsLoader(@NonNull Context context) {
        super(context);
        airportsService = ApiFactory.getAirportService();
    }

    @Override
    protected void onForceLoad() {
        Call<List<Airport>> call = airportsService.getAirports();
        call.enqueue(new RetrofitCallback<List<Airport>>() {
            @Override
            public void onResponse(Response<List<Airport>> response) {
                if (response.isSuccess()) {
                    AirportsTable.clear(getContext());
                    AirportsTable.save(getContext(), response.body());
                    Cursor cursor = getContext().getContentResolver().query(AirportsTable.URI,
                            null, null, null, null);
                    deliverResult(cursor);
                } else {
                    deliverResult(null);
                }
            }
        });    }
}
