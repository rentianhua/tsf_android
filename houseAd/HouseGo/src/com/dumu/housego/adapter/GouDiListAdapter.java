package com.dumu.housego.adapter;

import java.util.List;

import com.dumu.housego.R;
import com.dumu.housego.entity.GouDiInfo;
import com.dumu.housego.entity.MyTextView;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GouDiListAdapter extends BaseAdapter {
	private List<GouDiInfo> goudis;
	private Context context;
	private LayoutInflater Inflater;
	private GouDiInfo goudi;
	ViewHolder1 holder1;
	ViewHolder0 holder0;
	private Typeface typeface;
	public GouDiListAdapter(List<GouDiInfo> goudis, Context context) {
		super();
		this.goudis = goudis;
		this.context = context;
		this.Inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return goudis==null?0:goudis.size();
	}

	@Override
	public GouDiInfo getItem(int position) {

		return goudis.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	
		goudi =(GouDiInfo)goudis.get(position);
		int type = getItemViewType(position);
		if (convertView == null) {
			
			switch (type) {
			
			case 0:
				holder0=new ViewHolder0();
				convertView = Inflater.inflate(R.layout.item_goudi_list2, null);
				holder0.iv_delete=(ImageView) convertView.findViewById(R.id.iv_delete);
				holder0.tv_goudi_dingdanhao=(MyTextView) convertView.findViewById(R.id.tv_goudi_dingdanhao);
				holder0.tv_goudi_loupan=(MyTextView) convertView.findViewById(R.id.tv_goudi_loupan);
				holder0.tv_goudi_paystatus=(MyTextView) convertView.findViewById(R.id.tv_goudi_paystatus);
				holder0.tv_zhuangtai=(MyTextView) convertView.findViewById(R.id.tv_zhuangtai);
				
				convertView.setTag(holder0);
				break;

			case 1:
				holder1=new ViewHolder1();
				convertView = Inflater.inflate(R.layout.item_goudi_list, null);
				holder1.tv_zhifu_liushuihao=(MyTextView) convertView.findViewById(R.id.tv_zhifu_liushuihao);
				holder1.tv_zhifu_loupan=(MyTextView) convertView.findViewById(R.id.tv_zhifu_loupan);
				holder1.tv_zhifu_zhanghao=(MyTextView) convertView.findViewById(R.id.tv_zhifu_zhanghao);
				holder1.tv_zhifu_zhifujine=(MyTextView) convertView.findViewById(R.id.tv_zhifu_zhifujine);
				holder1.tv_zhifu_zhifustatus=(MyTextView) convertView.findViewById(R.id.tv_zhifu_zhifustatus);
				holder1.tv_zhifu_zhifutime=(MyTextView) convertView.findViewById(R.id.tv_zhifu_zhifutime);
				holder1.tvZhifuDingdanhao=(MyTextView) convertView.findViewById(R.id.tv_zhifu_dingdanhao);
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
	            holder0.iv_delete.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
					}
				});
	            
	            holder0.tv_goudi_dingdanhao.setText(goudi.getOrder_no());
	            holder0.tv_goudi_loupan.setText(goudi.getTitle());
	            
	            holder0.tv_goudi_paystatus.setText("未支付");
	            
	            break;
	        case 1:
	            holder1.tv_zhifu_liushuihao.setText(goudi.getTrade_no());
	            holder1.tv_zhifu_loupan.setText(goudi.getTitle());
	            holder1.tv_zhifu_zhanghao.setText(goudi.getBuyer_email());
	            holder1.tv_zhifu_zhifujine.setText(goudi.getJine());
	            holder1.tv_zhifu_zhifustatus.setText("已支付");
	            //转换时间戳
	            long timeStamp=Long.valueOf(goudi.getPaytime());
	            String time=AgentSubmitErShouAdapter.formatData(timeStamp);
	            holder1.tv_zhifu_zhifutime.setText(time);
	            holder1.tvZhifuDingdanhao.setText(goudi.getOrder_no());
	            
	            break;
	        }
	
		return convertView;

	}

	class ViewHolder1 {
		TextView tvZhifuDingdanhao;
		TextView tv_zhifu_loupan;
		TextView tv_zhifu_liushuihao;
		TextView tv_zhifu_zhanghao;
		TextView tv_zhifu_zhifujine;
		TextView tv_zhifu_zhifutime;
		TextView tv_zhifu_zhifustatus;
	}
	
	class ViewHolder0 {
		TextView tv_goudi_dingdanhao;
		TextView tv_goudi_loupan;
		TextView tv_goudi_paystatus;
		TextView tv_zhuangtai;
		ImageView iv_delete;
	}
	
	 @Override
	    public int getViewTypeCount() {
	        return 2;
	    }
	 
	  @Override
	    public int getItemViewType(int position) {
	        goudi = (GouDiInfo) getItem(position);
	        if (goudi.getPay_status().equals("0")) {
	            return 0;
	        } else {
	            return 1;
	        }
	    }
}
