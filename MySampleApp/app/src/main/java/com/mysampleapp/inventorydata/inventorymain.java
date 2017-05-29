package com.mysampleapp.inventorydata;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;


import com.mysampleapp.BuildConfig;
import com.mysampleapp.util.inventoryimageutil.ImageCache;
import com.mysampleapp.util.inventoryimageutil.ImageFetcher;
import com.mysampleapp.util.inventoryimageutil.Utils;
import com.mysampleapp.R;
import com.mysampleapp.demo.DemoFragmentBase;
import com.mysampleapp.provider.Images;

public class inventorymain extends DemoFragmentBase implements AdapterView.OnItemClickListener {

  //  private View mFragmentView;
    private static final String TAG = "ImageGridFragment";
    private static final String IMAGE_CACHE_DIR = "thumbs";

    private int mImageThumbSize;
    private int mImageThumbSpacing;
    public ImageAdapter mAdapter;
    public ImageFetcher mImageFetcher;
/*
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        mFragmentView = inflater.inflate(R.layout.activity_inventorymain, container, false);

        return mFragmentView;
    }
*/
public View onCreateView(
        LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    final View v = inflater.inflate(R.layout.activity_inventorymain, container, false);
    final GridView mGridView = (GridView) v.findViewById(R.id.gridView);
    mGridView.setAdapter(mAdapter);
    mGridView.setOnItemClickListener(this);
    mGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView absListView, int scrollState) {
            // Pause fetcher to ensure smoother scrolling when flinging
            if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                // Before Honeycomb pause image loading on scroll to help with performance
                if (!Utils.hasHoneycomb()) {
                    mImageFetcher.setPauseWork(true);
                }
            } else {
                mImageFetcher.setPauseWork(false);
            }
        }

        @Override
        public void onScroll(AbsListView absListView, int firstVisibleItem,
                             int visibleItemCount, int totalItemCount) {
        }
    });
    mGridView.getViewTreeObserver().addOnGlobalLayoutListener(
            new ViewTreeObserver.OnGlobalLayoutListener() {
                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onGlobalLayout() {
                    if (mAdapter.getNumColumns() == 0) {
                        final int numColumns = (int) Math.floor(
                                mGridView.getWidth() / (mImageThumbSize + mImageThumbSpacing));
                        if (numColumns > 0) {
                            final int columnWidth =
                                    (mGridView.getWidth() / numColumns) - mImageThumbSpacing;
                            mAdapter.setNumColumns(numColumns);
                            mAdapter.setItemHeight(columnWidth);
                            if (BuildConfig.DEBUG) {
                                Log.d(TAG, "onCreateView - numColumns set to " + numColumns);
                            }
                            if (Utils.hasJellyBean()) {
                                mGridView.getViewTreeObserver()
                                        .removeOnGlobalLayoutListener(this);
                            } else {
                                mGridView.getViewTreeObserver()
                                        .removeGlobalOnLayoutListener(this);
                            }
                        }
                    }
                }
            });

    return v;
}

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        final Intent i = new Intent(getActivity(), ImageDetailActivity.class);
        i.putExtra(ImageDetailActivity.EXTRA_IMAGE, (int) id);
        if (Utils.hasJellyBean()) {
            // makeThumbnailScaleUpAnimation() looks kind of ugly here as the loading spinner may
            // show plus the thumbnail image in GridView is cropped. so using
            // makeScaleUpAnimation() instead.
            ActivityOptions options =
                    ActivityOptions.makeScaleUpAnimation(v, 0, 0, v.getWidth(), v.getHeight());
            getActivity().startActivity(i, options.toBundle());
        } else {
            startActivity(i);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
    }



        public inventorymain() {}
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //  setContentView(R.layout.activity_inventorymain);
            mImageThumbSize = getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size);
            mImageThumbSpacing = getResources().getDimensionPixelSize(R.dimen.image_thumbnail_spacing);

            mAdapter = new ImageAdapter(getActivity());

            ImageCache.ImageCacheParams cacheParams =
                    new ImageCache.ImageCacheParams(getActivity(), IMAGE_CACHE_DIR);

            cacheParams.setMemCacheSizePercent(0.25f);

            mImageFetcher = new ImageFetcher(getActivity(), mImageThumbSize);
            mImageFetcher.setLoadingImage(R.drawable.empty_photo);
            mImageFetcher.addImageCache(getActivity().getSupportFragmentManager(), cacheParams);
        }

        @Override
        public void onResume() {
            super.onResume();
            mImageFetcher.setExitTasksEarly(false);
            mAdapter.notifyDataSetChanged();
        }

        @Override
        public void onPause() {
            super.onPause();
            mImageFetcher.setPauseWork(false);
            mImageFetcher.setExitTasksEarly(true);
            mImageFetcher.flushCache();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            mImageFetcher.closeCache();
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.clear_cache:
                    mImageFetcher.clearCache();
                    Toast.makeText(getActivity(), R.string.clear_cache_complete_toast,
                            Toast.LENGTH_SHORT).show();
                    return true;
            }
            return super.onOptionsItemSelected(item);
        }

        public class ImageAdapter extends BaseAdapter {

            private final Context mContext;
            private int mItemHeight = 0;
            private int mNumColumns = 0;
            private int mActionBarHeight = 0;
            private GridView.LayoutParams mImageViewLayoutParams;

            public ImageAdapter(Context context) {
                super();
                mContext = context;
                mImageViewLayoutParams = new GridView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                // Calculate ActionBar height
                TypedValue tv = new TypedValue();
                if (context.getTheme().resolveAttribute(
                        android.R.attr.actionBarSize, tv, true)) {
                    mActionBarHeight = TypedValue.complexToDimensionPixelSize(
                            tv.data, context.getResources().getDisplayMetrics());
                }
            }

            @Override
            public int getCount() {
                // If columns have yet to be determined, return no items
                if (getNumColumns() == 0) {
                    return 0;
                }

                // Size + number of columns for top empty row
                return Images.imageThumbUrls.length + mNumColumns;
            }

            @Override
            public Object getItem(int position) {
                return position < mNumColumns ?
                        null : Images.imageThumbUrls[position - mNumColumns];
            }

            @Override
            public long getItemId(int position) {
                return position < mNumColumns ? 0 : position - mNumColumns;
            }

            @Override
            public int getViewTypeCount() {
                // Two types of views, the normal ImageView and the top row of empty views
                return 2;
            }

            @Override
            public int getItemViewType(int position) {
                return (position < mNumColumns) ? 1 : 0;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup container) {
                //BEGIN_INCLUDE(load_gridview_item)
                // First check if this is the top row
                if (position < mNumColumns) {
                    if (convertView == null) {
                        convertView = new View(mContext);
                    }
                    // Set empty view with height of ActionBar
                    convertView.setLayoutParams(new AbsListView.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, mActionBarHeight));
                    return convertView;
                }

                // Now handle the main ImageView thumbnails
                ImageView imageView;
                if (convertView == null) { // if it's not recycled, instantiate and initialize
                    imageView = new RecyclingImageView(mContext);
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    imageView.setLayoutParams(mImageViewLayoutParams);
                } else { // Otherwise re-use the converted view
                    imageView = (ImageView) convertView;
                }

                // Check the height matches our calculated column width
                if (imageView.getLayoutParams().height != mItemHeight) {
                    imageView.setLayoutParams(mImageViewLayoutParams);
                }

                // Finally load the image asynchronously into the ImageView, this also takes care of
                // setting a placeholder image while the background thread runs
                mImageFetcher.loadImage(Images.imageThumbUrls[position - mNumColumns], imageView);
                return imageView;
                //END_INCLUDE(load_gridview_item)
            }

            /**
             * Sets the item height. Useful for when we know the column width so the height can be set
             * to match.
             *
             * @param height
             */
            public void setItemHeight(int height) {
                if (height == mItemHeight) {
                    return;
                }
                mItemHeight = height;
                mImageViewLayoutParams =
                        new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mItemHeight);
                mImageFetcher.setImageSize(height);
                notifyDataSetChanged();
            }

            public void setNumColumns(int numColumns) {
                mNumColumns = numColumns;
            }

            public int getNumColumns() {
                return mNumColumns;
            }
        }
    }
