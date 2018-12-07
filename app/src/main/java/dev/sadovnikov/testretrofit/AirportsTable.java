package dev.sadovnikov.testretrofit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class AirportsTable {

    public static final Uri URI = SQLiteHelper.BASE_CONTENT_URI.buildUpon().appendPath(Requests.TABLE_NAME).build();

    public static void save(Context context, @NonNull Airport airport) {
        context.getContentResolver().insert(URI, toContentValues(airport));
    }

    public static void save(Context context, @NonNull List<Airport> airports) {
        ContentValues[] values = new ContentValues[airports.size()];
        for (int i = 0; i < airports.size(); i++) {
            values[i] = toContentValues(airports.get(i));
        }
        context.getContentResolver().bulkInsert(URI, values);
    }

    @NonNull
    public static ContentValues toContentValues(@NonNull Airport airport) {
        ContentValues values = new ContentValues();
        values.put(Columns.CODE, airport.getCode());
        values.put(Columns.NAME, airport.getName());
        return values;
    }

    @NonNull
    public static Airport fromCursor(@NonNull Cursor cursor) {
        String code = cursor.getString(cursor.getColumnIndex(Columns.CODE));
        String name = cursor.getString(cursor.getColumnIndex(Columns.NAME));
        return new Airport(code, name);
    }

    @NonNull
    public static List<Airport> listFromCursor(@NonNull Cursor cursor) {
        List<Airport> airports = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return airports;
        }
        try {
            do {
                airports.add(fromCursor(cursor));
            } while (cursor.moveToNext());
            return airports;
        } finally {
            cursor.close();
        }
    }

    public static void clear(Context context) {
        context.getContentResolver().delete(URI, null, null);
    }

    public interface Columns {
        String CODE = "code";
        String NAME = "name";
    }

    public interface Requests {

        String TABLE_NAME = AirportsTable.class.getSimpleName();

        String CREATION_REQUEST = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                Columns.CODE + " VARCHAR(10) NOT NULL, " +
                Columns.NAME + " VARCHAR(200)" + ");";

        String DROP_REQUEST = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

}
