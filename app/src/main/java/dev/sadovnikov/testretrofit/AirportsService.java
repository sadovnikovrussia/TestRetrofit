package dev.sadovnikov.testretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AirportsService {

    @GET("airports.json")
    Call<List<Airport>> getAirports();
}
