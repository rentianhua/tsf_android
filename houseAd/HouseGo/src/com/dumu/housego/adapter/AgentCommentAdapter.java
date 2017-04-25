package com.dumu.housego.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.dumu.housego.R;
import com.dumu.housego.entity.AgentCommentList;
import com.dumu.housego.entity.AgentData;
import com.dumu.housego.entity.MyTextView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AgentCommentAdapter extends BaseAdapter {
    private List<AgentCommentList> comments;
    private Context context;
    private LayoutInflater Inflater;
    private Typeface typeface;

    public AgentCommentAdapter(List<AgentCommentList> comments, Context context) {
        super();
        this.comments = comments;
        this.context = context;
        this.Inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return comments == null ? 0 : comments.size();
    }

    @Override
    public AgentCommentList getItem(int position) {

        return comments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = Inflater.inflate(R.layout.item_agent_pinglun, null);
            holder = new ViewHolder();
            holder.Ivpic = (ImageView) convertView.findViewById(R.id.iv_agent_author);
            holder.tv_agent_author = (MyTextView) convertView.findViewById(R.id.tv_agent_author);
            holder.tv_agent_content = (MyTextView) convertView.findViewById(R.id.tv_agent_content);
            holder.tv_agent_date = (MyTextView) convertView.findViewById(R.id.tv_agent_date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final AgentCommentList e = getItem(position);


        //
        String url = e.getUserpic();
        //DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
        //		.bitmapConfig(Bitmap.Config.RGB_565).build();
        //ImageLoader.getInstance().displayImage(url,holder.Ivpic , options);
        //
        Glide.with(context).load(url).into(holder.Ivpic);

        holder.tv_agent_author.setText(e.getAuthor());
        holder.tv_agent_content.setText(e.getContent());
        holder.tv_agent_date.setText(e.getDate());
        return convertView;

    }

    class ViewHolder {
        ImageView Ivpic;
        TextView tv_agent_author;
        TextView tv_agent_date;
        TextView tv_agent_content;
    }
}
