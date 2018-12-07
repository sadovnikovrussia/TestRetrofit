package dev.sadovnikov.testretrofit;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class RetrofitCallback<T> implements Callback<T> {
    private static final String TAG = "RetrofitCallback";

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        Log.d(TAG, "onResponse: ");
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.d(TAG, "onFailure: ");

    }
}
