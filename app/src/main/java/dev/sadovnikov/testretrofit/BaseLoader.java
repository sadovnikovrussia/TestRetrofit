package dev.sadovnikov.testretrofit;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import android.util.Log;

public class BaseLoader extends Loader<Cursor> {
    private static final String TAG = "BaseLoader";
    private Cursor mCursor;

    public BaseLoader(@NonNull Context context) {
        super(context);
        Log.d(TAG, "BaseLoader: ");
    }

    @Override
    public void deliverResult(@Nullable Cursor cursor) {
        Log.d(TAG, "deliverResult: ");
        if (isReset()) {
            if (cursor != null) {
                cursor.close();
            }
            return;
        }
        Cursor oldCursor = mCursor;
        mCursor = cursor;

        if (isStarted()) {
            Log.d(TAG, "deliverResult: " + cursor.toString());
            super.deliverResult(cursor);
        }

        if (oldCursor != null && oldCursor != cursor && !oldCursor.isClosed()) {
            oldCursor.close();
        }
    }

    @Override
    protected void onStartLoading() {
        Log.d(TAG, "onStartLoading: ");
        if (mCursor != null) {
            deliverResult(mCursor);
        } else {
            forceLoad();
        }
    }

    @Override
    protected void onReset() {
        Log.d(TAG, "onReset: ");
        if (mCursor != null && !mCursor.isClosed()) {
            mCursor.close();
        }
        mCursor = null;
    }

    @Override
    public void forceLoad() {
        super.forceLoad();
        Log.d(TAG, "forceLoad: ");
    }


}
