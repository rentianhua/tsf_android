package util;

import uk.co.senab.photoview.PhotoView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dumu.housego.R;
import com.dumu.housego.activity.BaseActivity;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class BigPicActivity extends BaseActivity {

    private ViewPager mViewPager;
    private TextView tvImgIndex;
    private SamplePagerAdapter mAdapter;
    private String[] mPicUrls;
    private Intent intent;
    private int index;
    private int mcurrentPosition;

    @SuppressWarnings("deprecation")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_pic);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        tvImgIndex = (TextView) findViewById(R.id.tv_img_index);
        intent = getIntent();
        mPicUrls = intent.getStringArrayExtra("picsUrls");
        index = intent.getIntExtra("index", 0);
        mAdapter = new SamplePagerAdapter(mPicUrls);
        mViewPager.setAdapter(mAdapter);

        mViewPager.setCurrentItem(index);

        tvImgIndex.setText((index + 1) + "/" + mPicUrls.length);

        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
            public void onPageSelected(int postion) {
                mcurrentPosition = postion;
                if (mPicUrls != null && mPicUrls.length > 1) {
                    if (tvImgIndex != null) {
                        tvImgIndex.setText((mcurrentPosition + 1) + "/" + mPicUrls.length);
                    }
                }
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    class SamplePagerAdapter extends RecyclingPagerAdapter {
        private String[] images = new String[]{};

        public SamplePagerAdapter(String[] images) {
            this.images = images;
        }

        public String getItem(int position) {
            return images[position];
        }

        @Override
        public int getCount() {
            return images == null ? 0 : images.length;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(container.getContext())
                        .inflate(R.layout.imag_preview_item, null);
                holder = new ViewHolder();
                holder.image = (PhotoView) convertView.findViewById(R.id.photoview);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Log.i("getView", images[position]);

            //ImageLoadProxy.displayImage4Detail(images[position], holder.image, new SimpleImageLoadingListener());
            Glide.with(BigPicActivity.this).load(images[position]).into(holder.image);
            return convertView;
        }
    }

    class ViewHolder {
        PhotoView image;
    }
}
