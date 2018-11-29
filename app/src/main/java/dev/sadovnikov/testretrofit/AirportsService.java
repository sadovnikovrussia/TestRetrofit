package dev.sadovnikov.testretrofit;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

public interface AirportsService {

    @GET("airports.json")
    Call<List<Airport>> getAirports();
}
