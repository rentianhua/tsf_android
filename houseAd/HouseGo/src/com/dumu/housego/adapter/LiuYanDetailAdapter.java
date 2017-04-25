package com.dumu.housego.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.dumu.housego.R;
import com.dumu.housego.entity.GouDiInfo;
import com.dumu.housego.entity.LiuYanList;
import com.dumu.housego.entity.MyTextView;
import com.dumu.housego.util.TimeTurnDate;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LiuYanDetailAdapter extends BaseAdapter {
    private List<LiuYanList> goudis;
    private Context context;
    private LayoutInflater Inflater;
    private LiuYanList goudi;
    ViewHolder1 holder1;
    ViewHolder0 holder0;
    String userid;

    public LiuYanDetailAdapter(List<LiuYanList> goudis, Context context, String userid) {
        super();
        this.goudis = goudis;
        this.context = context;
        this.userid = userid;
        this.Inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return goudis == null ? 0 : goudis.size();
    }

    @Override
    public LiuYanList getItem(int position) {

        return goudis.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        goudi = (LiuYanList) goudis.get(position);
        int type = getItemViewType(position);
        if (convertView == null) {

            switch (type) {

                case 0:
                    holder0 = new ViewHolder0();
                    convertView = Inflater.inflate(R.layout.message_item_left, null);
                    holder0.iv_touxiang = (ImageView) convertView.findViewById(R.id.iv_left_touxiang);
                    holder0.tv_name = (TextView) convertView.findViewById(R.id.tv_left_name);
                    holder0.tv_content = (TextView) convertView.findViewById(R.id.tv_left_content);

                    convertView.setTag(holder0);
                    break;

                case 1:

                    holder1 = new ViewHolder1();
                    convertView = Inflater.inflate(R.layout.message_item_right, null);
                    holder1.iv_touxiang = (ImageView) convertView.findViewById(R.id.iv_right_touxiang);
                    holder1.tv_name = (TextView) convertView.findViewById(R.id.tv_right_name);
                    holder1.tv_content = (TextView) convertView.findViewById(R.id.tv_right_content);
                    convertView.setTag(holder1);
                    break;
            }
        } else {

            switch (type) {
                case 0:
                    holder0 = (ViewHolder0) convertView.getTag();
                    break;
                case 1:
                    holder1 = (ViewHolder1) convertView.getTag();
                    break;
            }
        }

        switch (type) {
            case 0:

                if (goudi.getUserpic() != null && !goudi.getUserpic().equals("")) {
                    String url = goudi.getUserpic();
//	    			DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
//	    					.bitmapConfig(Bitmap.Config.RGB_565).build();
//	    			ImageLoader.getInstance().displayImage(url, holder0.iv_touxiang, options);
                    Glide.with(context).load(url).into(holder0.iv_touxiang);
                } else {
                    Glide.clear(holder0.iv_touxiang);
                    holder0.iv_touxiang.setImageResource(R.drawable.appicon60x60);
                }

                holder0.tv_name.setText(TimeTurnDate.getStringDate(goudi.getInputtime()));
                holder0.tv_content.setText(goudi.getContent());

                break;
            case 1:

                if (goudi.getUserpic() != null && !goudi.getUserpic().equals("")) {
                    String url = goudi.getUserpic();
//	    			DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
//	    					.bitmapConfig(Bitmap.Config.RGB_565).build();
//	    			ImageLoader.getInstance().displayImage(url, holder1.iv_touxiang, options);
                    Glide.with(context).load(url).into(holder1.iv_touxiang);
                } else {
                    Glide.clear(holder1.iv_touxiang);
                    holder1.iv_touxiang.setImageResource(R.drawable.appicon60x60);
                }

                holder1.tv_name.setText(TimeTurnDate.getStringDate(goudi.getInputtime()));
                holder1.tv_content.setText(goudi.getContent());


                break;
        }

        return convertView;

    }

    class ViewHolder1 {
        TextView tv_content;
        TextView tv_name;
        ImageView iv_touxiang;
    }

    class ViewHolder0 {

        TextView tv_content;
        TextView tv_name;
        ImageView iv_touxiang;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        goudi = (LiuYanList) getItem(position);
        if (goudi.getFrom_uid().equals(userid)) {
            return 1;
        } else {
            return 0;
        }
    }
}
