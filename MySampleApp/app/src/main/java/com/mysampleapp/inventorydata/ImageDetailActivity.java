package com.mysampleapp.inventorydata;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;




import com.mysampleapp.R;
import com.mysampleapp.provider.Images;
import com.mysampleapp.provider.apicalls.BackgroundApi;
import com.mysampleapp.provider.apicalls.Createtransaction;
import com.mysampleapp.util.inventoryimageutil.ImageFetcher;

public class ImageDetailActivity extends FragmentActivity implements View.OnClickListener {

    private static final String IMAGE_CACHE_DIR = "images";
    public static final String ITEM_ID = "id";

    private ImagePagerAdapter mAdapter;
    private ImageFetcher mImageFetcher;
    private ViewPager mPager;

    Integer transData;
    String type = "/product/get";
    String responseString;
    String indexString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        Bundle b = getIntent().getExtras();
        transData = b.getInt("id");
        indexString = transData.toString();

        backgroundApi.execute(type, indexString);
    }


    BackgroundApi backgroundApi = new BackgroundApi(new Createtransaction.AsyncResponse() {
        @Override
        public void processFinish(String output) {
            Log.d("response", output);
            responseString = output;
            final String responseTest = "User not found";
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            // On complete call either onLoginSuccess or onLoginFailed
                            if (!(responseString.equals(responseTest))) {
                                onLoginSuccess();
                                Log.d("Response from Server", (String) responseString);
                                Toast.makeText(getApplicationContext(),"Delete Confirmed" + responseString, Toast.LENGTH_SHORT).show();
                            } else if (responseString.equals(responseTest)) {
                                Log.d("Response String Null!", (String) responseString);
                                //  onLoginFailed();
                            }
                        }
                    }, 100);

        }

    });

    public void onLoginSuccess() {
        Toast.makeText(getBaseContext(), "TRANSACTION CREATED" +responseString+ "Thanks!", Toast.LENGTH_LONG).show();

//        Intent intent = new Intent(this, getAllTransactions.class);
  //      startActivity(intent);
    }

    /**
     * Called by the ViewPager child fragments to load images via the one ImageFetcher
     */
    public ImageFetcher getImageFetcher() {
        return mImageFetcher;
    }

    /**
     * The main adapter that backs the ViewPager. A subclass of FragmentStatePagerAdapter as there
     * could be a large number of items in the ViewPager and we don't want to retain them all in
     * memory at once but create/destroy them on the fly.
     */
    private class ImagePagerAdapter extends FragmentStatePagerAdapter {
        private final int mSize;

        public ImagePagerAdapter(FragmentManager fm, int size) {
            super(fm);
            mSize = size;
        }

        @Override
        public int getCount() {
            return mSize;
        }

        @Override
        public Fragment getItem(int position) {
            return ImageDetailFragment.newInstance(Images.imageUrls[position]);
        }
    }
    @Override
    public void onClick(View v) {
        final int vis = mPager.getSystemUiVisibility();
        if ((vis & View.SYSTEM_UI_FLAG_LOW_PROFILE) != 0) {
            mPager.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        } else {
            mPager.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
        }
    }
}
