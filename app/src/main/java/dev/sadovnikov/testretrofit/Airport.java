package dev.sadovnikov.testretrofit;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

class Airport {

    @SerializedName("code")
    private String code;

    @SerializedName("name")
    private String name;

    @SerializedName("coordinates")
    private Coordinates coordinates;

    public Airport() {
    }

    @NonNull
    @Override
    public String toString() {
        return "Airport{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }


    static private class Coordinates {

        @SerializedName("lon")
        private Float lon;

        @SerializedName("lat")
        private Float lat;

        @NonNull
        @Override
        public String toString() {
            return "Coordinates{" +
                    "lon=" + lon +
                    ", lat=" + lat +
                    '}';
        }
    }
}
