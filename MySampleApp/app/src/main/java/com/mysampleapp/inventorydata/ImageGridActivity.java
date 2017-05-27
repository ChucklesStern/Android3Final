package com.mysampleapp.inventorydata;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mysampleapp.BuildConfig;
import com.mysampleapp.R;
import com.mysampleapp.util.inventoryimageutil.Utils;

public class ImageGridActivity extends AppCompatActivity {
    private static final String TAG = "ImageGridActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (BuildConfig.DEBUG) {
            Utils.enableStrictMode();
        }
        super.onCreate(savedInstanceState);

        if (getSupportFragmentManager().findFragmentByTag(TAG) == null) {
            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(android.R.id.content, new inventorymain(), TAG);
            ft.commit();
        }
    }
}
