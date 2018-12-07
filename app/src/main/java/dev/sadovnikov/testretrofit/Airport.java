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

    public Airport(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
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


    static class Coordinates {

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
