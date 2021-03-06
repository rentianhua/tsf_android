package com.dumu.housego.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.dumu.housego.R;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.MyTextView;
import com.dumu.housego.util.UrlFactory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ErShouFangRecommendAdapter extends BaseAdapter {
    private List<ErShouFangRecommendData> ershoufangrecommends;
    private Context context;
    private LayoutInflater Inflater;
    private Typeface typeface;

    public ErShouFangRecommendAdapter(List<ErShouFangRecommendData> ershoufangrecommends, Context context) {
        super();
        this.ershoufangrecommends = ershoufangrecommends;
        this.context = context;
        this.Inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return ershoufangrecommends == null ? 0 : ershoufangrecommends.size();
    }

    @Override
    public ErShouFangRecommendData getItem(int position) {

        return ershoufangrecommends.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = Inflater.inflate(R.layout.item_ershoufang_recommend, null);
            holder = new ViewHolder();
            holder.ivImg = (ImageView) convertView.findViewById(R.id.iv_ershoufang_img);
            holder.tvAddress = (MyTextView) convertView.findViewById(R.id.tv_ershoufang_address);
            holder.tvArea = (MyTextView) convertView.findViewById(R.id.tv_ershoufang_price_area);
            holder.tvMeterPrice = (MyTextView) convertView.findViewById(R.id.tv_ershoufang_price_meter);
            holder.tvPrice = (MyTextView) convertView.findViewById(R.id.tv_ershoufang_totalprice);
            holder.tvTitle = (MyTextView) convertView.findViewById(R.id.tv_ershoufang_title);
            holder.tvBiaoqian1 = (MyTextView) convertView.findViewById(R.id.tv_ershou_biaoqian);
            holder.tvBiaoqian2 = (MyTextView) convertView.findViewById(R.id.tv_ershou_biaoqian2);
            holder.tvBiaoqian3 = (MyTextView) convertView.findViewById(R.id.tv_ershou_biaoqian3);
            holder.tvBiaoqian4 = (MyTextView) convertView.findViewById(R.id.tv_ershou_biaoqian4);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        ErShouFangRecommendData n = getItem(position);

        // 显示图片的配置

        String url = n.getThumb();

//		DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
//				.bitmapConfig(Bitmap.Config.RGB_565).build();
//		ImageLoader.getInstance().displayImage(UrlFactory.TSFURL+url, holder.ivImg, options);
        Glide.with(context).load(UrlFactory.TSFURL + url).into(holder.ivImg);

        holder.tvTitle.setText(n.getTitle() + "");

        holder.tvArea.setText(n.getShi() + "室" + n.getTing() + "厅 " + n.getJianzhumianji() + "㎡ " + n.getChaoxiang());
        holder.tvPrice.setText(n.getZongjia() + "");
        holder.tvAddress.setText("" + n.getCityname() + " " + n.getAreaname());

        int zongjia = Integer.valueOf(n.getZongjia().trim());
        int mianji = Integer.valueOf(n.getJianzhumianji());

        int price = 0;
        if (zongjia != 0 && mianji != 0) {
            price = (zongjia) * (10000) / mianji;
        }

        holder.tvMeterPrice.setText(price + "元/㎡");

        if (!n.getBiaoqian().equals("")) {
            String[] b = n.getBiaoqian().split(",");
            int l = b.length;
            switch (l) {
                case 1:
                    holder.tvBiaoqian1.setText(b[0]);
                    holder.tvBiaoqian1.setVisibility(View.VISIBLE);
                    holder.tvBiaoqian2.setVisibility(View.GONE);
                    holder.tvBiaoqian3.setVisibility(View.GONE);
                    holder.tvBiaoqian4.setVisibility(View.GONE);
                    break;
                case 2:
                    holder.tvBiaoqian1.setText(b[0]);
                    holder.tvBiaoqian2.setText(b[1]);
                    holder.tvBiaoqian1.setVisibility(View.VISIBLE);
                    holder.tvBiaoqian2.setVisibility(View.VISIBLE);
                    holder.tvBiaoqian3.setVisibility(View.GONE);
                    holder.tvBiaoqian4.setVisibility(View.GONE);
                    break;
                case 3:
                    holder.tvBiaoqian1.setText(b[0]);
                    holder.tvBiaoqian2.setText(b[1]);
                    holder.tvBiaoqian3.setText(b[2]);
                    holder.tvBiaoqian1.setVisibility(View.VISIBLE);
                    holder.tvBiaoqian2.setVisibility(View.VISIBLE);
                    holder.tvBiaoqian3.setVisibility(View.VISIBLE);
                    holder.tvBiaoqian4.setVisibility(View.GONE);
                    break;
                case 4:
                    holder.tvBiaoqian1.setText(b[0]);
                    holder.tvBiaoqian2.setText(b[1]);
                    holder.tvBiaoqian3.setText(b[2]);
                    holder.tvBiaoqian4.setText(b[3]);
                    holder.tvBiaoqian1.setVisibility(View.VISIBLE);
                    holder.tvBiaoqian3.setVisibility(View.VISIBLE);
                    holder.tvBiaoqian2.setVisibility(View.VISIBLE);
                    holder.tvBiaoqian4.setVisibility(View.VISIBLE);
                    break;
            }
        } else {
            holder.tvBiaoqian1.setVisibility(View.GONE);
            holder.tvBiaoqian3.setVisibility(View.GONE);
            holder.tvBiaoqian2.setVisibility(View.GONE);
            holder.tvBiaoqian4.setVisibility(View.GONE);
        }

        return convertView;
    }

    class ViewHolder {
        TextView tvTitle;
        TextView tvArea;
        TextView tvPrice;
        TextView tvAddress;
        TextView tvMeterPrice;
        ImageView ivImg;

        TextView tvBiaoqian1;
        TextView tvBiaoqian2;
        TextView tvBiaoqian3;
        TextView tvBiaoqian4;
    }
}
