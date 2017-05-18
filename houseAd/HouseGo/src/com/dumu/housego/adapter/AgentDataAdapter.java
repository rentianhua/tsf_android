package com.dumu.housego.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.dumu.housego.LiuYanDetailActivity;
import com.dumu.housego.R;
import com.dumu.housego.activity.LoginActivity;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.AgentData;
import com.dumu.housego.entity.MyTextView;
import com.dumu.housego.entity.UserInfo;

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

public class AgentDataAdapter extends BaseAdapter {
    private List<AgentData> agentdatas;
    private Context context;
    private LayoutInflater Inflater;
    private Typeface typeface;

    public AgentDataAdapter(List<AgentData> agentdatas, Context context) {
        super();
        this.agentdatas = agentdatas;
        this.context = context;
        this.Inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return agentdatas == null ? 0 : agentdatas.size();
    }

    @Override
    public AgentData getItem(int position) {

        return agentdatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = Inflater.inflate(R.layout.item_agent_data_list, null);
            holder = new ViewHolder();
            holder.ivAgentdataPic = (ImageView) convertView.findViewById(R.id.iv_agentdata_pic);
            holder.ivAgentdataPhone = (ImageView) convertView.findViewById(R.id.iv_agentdata_phone);
            holder.tvAgentdataBiaoqian = (MyTextView) convertView.findViewById(R.id.tv_agentdata_biaoqian);
            holder.tvAgentdataBiaoqian2 = (MyTextView) convertView.findViewById(R.id.tv_agentdata_biaoqian2);
            holder.tvAgentdataBiaoqian3 = (MyTextView) convertView.findViewById(R.id.tv_agentdata_biaoqian3);
            holder.tvAgentdataBiaoqian4 = (MyTextView) convertView.findViewById(R.id.tv_agentdata_biaoqian4);
            holder.tvAgentdataBiaoqian5 = (MyTextView) convertView.findViewById(R.id.tv_agentdata_biaoqian5);
            holder.tvAgentdataBiaoqian6 = (MyTextView) convertView.findViewById(R.id.tv_agentdata_biaoqian6);
            holder.tvAgentdataLevel = (MyTextView) convertView.findViewById(R.id.tv_agentdata_level);
            holder.tvAgentdataMainarea = (MyTextView) convertView.findViewById(R.id.tv_agentdata_mainarea);
            holder.tvAgentdataName = (MyTextView) convertView.findViewById(R.id.tv_agentdata_name);
            holder.tvAgentdataPinglun = (MyTextView) convertView.findViewById(R.id.tv_agentdata_pinglun);
            holder.tvAgentdataPoint = (MyTextView) convertView.findViewById(R.id.tv_agentdata_point);
            holder.iv_agentdata_message = (ImageView) convertView.findViewById(R.id.iv_agentdata_message);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        final AgentData n = getItem(position);
        String url = n.getUserpic();

        //DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
        //		.bitmapConfig(Bitmap.Config.RGB_565).build();
        //ImageLoader.getInstance().displayImage(url, holder.ivAgentdataPic, options);
        Glide.with(context).load(url).into(holder.ivAgentdataPic);

        if (!n.getBiaoqian().equals("")) {
            String[] b = n.getBiaoqian().split(",");
            int l = b.length;

            holder.tvAgentdataBiaoqian.setVisibility(View.GONE);
            holder.tvAgentdataBiaoqian2.setVisibility(View.GONE);
            holder.tvAgentdataBiaoqian3.setVisibility(View.GONE);
            holder.tvAgentdataBiaoqian4.setVisibility(View.GONE);
            holder.tvAgentdataBiaoqian5.setVisibility(View.GONE);
            holder.tvAgentdataBiaoqian6.setVisibility(View.GONE);

            switch (l) {
                case 1:
                    holder.tvAgentdataBiaoqian.setText(b[0]);
                    holder.tvAgentdataBiaoqian.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    holder.tvAgentdataBiaoqian.setText(b[0]);
                    holder.tvAgentdataBiaoqian2.setText(b[1]);
                    holder.tvAgentdataBiaoqian.setVisibility(View.VISIBLE);
                    holder.tvAgentdataBiaoqian2.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    holder.tvAgentdataBiaoqian.setText(b[0]);
                    holder.tvAgentdataBiaoqian2.setText(b[1]);
                    holder.tvAgentdataBiaoqian3.setText(b[2]);
                    holder.tvAgentdataBiaoqian.setVisibility(View.VISIBLE);
                    holder.tvAgentdataBiaoqian2.setVisibility(View.VISIBLE);
                    holder.tvAgentdataBiaoqian3.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    holder.tvAgentdataBiaoqian.setText(b[0]);
                    holder.tvAgentdataBiaoqian2.setText(b[1]);
                    holder.tvAgentdataBiaoqian3.setText(b[2]);
                    holder.tvAgentdataBiaoqian4.setText(b[3]);
                    holder.tvAgentdataBiaoqian.setVisibility(View.VISIBLE);
                    holder.tvAgentdataBiaoqian3.setVisibility(View.VISIBLE);
                    holder.tvAgentdataBiaoqian2.setVisibility(View.VISIBLE);
                    holder.tvAgentdataBiaoqian4.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    holder.tvAgentdataBiaoqian.setText(b[0]);
                    holder.tvAgentdataBiaoqian2.setText(b[1]);
                    holder.tvAgentdataBiaoqian3.setText(b[2]);
                    holder.tvAgentdataBiaoqian4.setText(b[3]);
                    holder.tvAgentdataBiaoqian5.setText(b[4]);
                    holder.tvAgentdataBiaoqian.setVisibility(View.VISIBLE);
                    holder.tvAgentdataBiaoqian3.setVisibility(View.VISIBLE);
                    holder.tvAgentdataBiaoqian2.setVisibility(View.VISIBLE);
                    holder.tvAgentdataBiaoqian4.setVisibility(View.VISIBLE);
                    holder.tvAgentdataBiaoqian5.setVisibility(View.VISIBLE);
                    break;
                case 6:
                    holder.tvAgentdataBiaoqian.setText(b[0]);
                    holder.tvAgentdataBiaoqian2.setText(b[1]);
                    holder.tvAgentdataBiaoqian3.setText(b[2]);
                    holder.tvAgentdataBiaoqian4.setText(b[3]);
                    holder.tvAgentdataBiaoqian5.setText(b[3]);
                    holder.tvAgentdataBiaoqian6.setText(b[3]);
                    holder.tvAgentdataBiaoqian.setVisibility(View.VISIBLE);
                    holder.tvAgentdataBiaoqian3.setVisibility(View.VISIBLE);
                    holder.tvAgentdataBiaoqian2.setVisibility(View.VISIBLE);
                    holder.tvAgentdataBiaoqian4.setVisibility(View.VISIBLE);
                    holder.tvAgentdataBiaoqian5.setVisibility(View.VISIBLE);
                    holder.tvAgentdataBiaoqian6.setVisibility(View.VISIBLE);
                    break;
            }
        } else {
            holder.tvAgentdataBiaoqian.setVisibility(View.GONE);
            holder.tvAgentdataBiaoqian2.setVisibility(View.GONE);
            holder.tvAgentdataBiaoqian3.setVisibility(View.GONE);
            holder.tvAgentdataBiaoqian4.setVisibility(View.GONE);
            holder.tvAgentdataBiaoqian5.setVisibility(View.GONE);
            holder.tvAgentdataBiaoqian6.setVisibility(View.GONE);
        }


        String dengji = "";
        if (!n.getDengji().equals(null) && !n.getDengji().equals("")) {
            int dj = Integer.valueOf(n.getDengji());
            switch (dj) {
                case 1:
                    dengji = "普通经纪人";
                    holder.tvAgentdataLevel.setText(dengji);
                    break;
                case 2:
                    dengji = "优秀经纪人";
                    holder.tvAgentdataLevel.setText(dengji);
                    break;
                case 3:
                    dengji = "高级经纪人";
                    holder.tvAgentdataLevel.setText(dengji);
                    break;
                case 4:
                    dengji = "资深经纪人";
                    holder.tvAgentdataLevel.setText(dengji);
                    break;
            }
        } else {
            holder.tvAgentdataLevel.setText("");
        }

        holder.ivAgentdataPhone.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + n.getUsername()));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

        holder.iv_agentdata_message.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfo userinfo = HouseGoApp.getContext().getCurrentUserInfo();
                if (userinfo == null) {
                    context.startActivity(new Intent(context,
                            LoginActivity.class));
                } else {
                    Intent i = new Intent(context, LiuYanDetailActivity.class);
                    i.putExtra("towhos", n.getUserid());
                    i.putExtra("realnames", n.getRealname());
                    context.startActivity(i);
                }
            }
        });


        holder.tvAgentdataMainarea.setText(n.getMainarea());
        holder.tvAgentdataName.setText(n.getRealname());
        holder.tvAgentdataPinglun.setText(n.getComm_count());
//		if(!n.getPoint().equals("")&&!n.getPoint().equals("")){
//			holder.tvAgentdataPoint.setText(n.getPoint()+"");
//		}else{
        holder.tvAgentdataPoint.setText("" + n.fb_rate);
//		}

        return convertView;

    }

    class ViewHolder {
        TextView tvAgentdataPoint;
        TextView tvAgentdataPinglun;
        TextView tvAgentdataBiaoqian;
        TextView tvAgentdataBiaoqian2;
        TextView tvAgentdataBiaoqian3;
        TextView tvAgentdataBiaoqian4;
        TextView tvAgentdataBiaoqian5;
        TextView tvAgentdataBiaoqian6;
        TextView tvAgentdataPhone1;
        TextView tvAgentdataMainarea;
        TextView tvAgentdataLevel;
        TextView tvAgentdataName;
        ImageView ivAgentdataPic;
        ImageView ivAgentdataPhone;
        ImageView iv_agentdata_message;
    }
}
