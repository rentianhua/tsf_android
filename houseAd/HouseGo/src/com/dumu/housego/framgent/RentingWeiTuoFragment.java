package com.dumu.housego.framgent;

import java.util.List;

import com.bumptech.glide.Glide;
import com.dumu.housego.R;
import com.dumu.housego.RentingDetailActivity;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.RentingDetail;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.framgent.PTrentingListFragment.SubmitRentingListAdapter;
import com.dumu.housego.framgent.PTrentingListFragment.SubmitRentingListAdapter.ViewHolder;
import com.dumu.housego.model.RentingWeiTuoModel;
import com.dumu.housego.presenter.ApplyShouChuPresenter;
import com.dumu.housego.presenter.IApplyShouChuPresenter;
import com.dumu.housego.presenter.IRentingWeiTuoPresenter;
import com.dumu.housego.presenter.RentingWeiTuoPresenter;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.util.UrlFactory;
import com.dumu.housego.view.IApplyShouChuView;
import com.dumu.housego.view.IRentingWeiTuoView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class RentingWeiTuoFragment extends Fragment implements IApplyShouChuView, IRentingWeiTuoView {
    private ListView lv_renting_weituo;
    private List<RentingDetail> rentings;
    private IApplyShouChuPresenter applyPresenter;
    private IRentingWeiTuoPresenter presenter;
    private UserInfo userinfo;
    private String username;
    private String userid;
    private String table;
    private SubmitRentingListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weituo_renting, null);
        InitView(view);
        applyPresenter = new ApplyShouChuPresenter(this);
        presenter = new RentingWeiTuoPresenter(this);
        setListener();
        return view;
    }

    private void setListener() {
        lv_renting_weituo.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), RentingDetailActivity.class);
                String Id = rentings.get(position).getId();
                String catid = rentings.get(position).getCatid();
                String posid = rentings.get(position).getPosid();
                i.putExtra("id", Id);
                i.putExtra("catid", catid);
                i.putExtra("posid", posid);
                startActivity(i);

            }
        });

    }

    private void InitView(View view) {
        lv_renting_weituo = (ListView) view.findViewById(R.id.lv_renting_weituo);

    }

    @Override
    public void onResume() {
        userinfo = HouseGoApp.getLoginInfo(getContext());
        username = userinfo.getUsername();
        userid = userinfo.getUserid();
        table = "chuzu";
        presenter.weituo(userid, table);
        super.onResume();
    }


    public class SubmitRentingListAdapter extends BaseAdapter {
        private List<RentingDetail> submitershous;
        private Context context;
        private LayoutInflater Inflater;

        public SubmitRentingListAdapter(List<RentingDetail> submitershous, Context context) {
            super();
            this.submitershous = submitershous;
            this.context = context;
            this.Inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return submitershous == null ? 0 : submitershous.size();
        }

        @Override
        public RentingDetail getItem(int position) {

            return submitershous.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = Inflater.inflate(R.layout.item_submit_renting_list3, null);
                holder = new ViewHolder();

                holder.ivPic = (ImageView) convertView.findViewById(R.id.iv_submit_renting_list);

                holder.tvChenHu = (TextView) convertView.findViewById(R.id.tv_submit_renting_list_chuzuType);
                holder.tvDetail = (TextView) convertView.findViewById(R.id.tv_submit_renting_list_detail);
                holder.ivEdit = (ImageView) convertView.findViewById(R.id.iv_submit_renting_list_edit);
                holder.tvLouceng = (TextView) convertView.findViewById(R.id.tv_submit_renting_list_area);
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_submit_renting_list_title);
                holder.tvZongjia = (TextView) convertView.findViewById(R.id.tv_submit_renting_list_zongjia);
                holder.rl_renting = (LinearLayout) convertView.findViewById(R.id.rl_renting);
                holder.tvShenqingshouchu = (TextView) convertView.findViewById(R.id.tv_shenqingshouchu);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            try {

                final RentingDetail n = getItem(position);


                if (n.getZaizu().equals("1")) {
                    if (n.getApply_state().equals("1")) {
                        holder.tvShenqingshouchu.setText("已出租申请中");
                        holder.tvShenqingshouchu.setTextColor(getResources().getColor(R.color.huisetextcolor));
                        holder.tvShenqingshouchu.setClickable(false);
                        holder.tvShenqingshouchu.setEnabled(false);
                    } else {
                        holder.tvShenqingshouchu.setClickable(true);
                        holder.tvShenqingshouchu.setEnabled(true);
                        holder.tvShenqingshouchu.setText("申请已出租");
                        holder.tvShenqingshouchu.setTextColor(getResources().getColor(R.color.conusertextcolor));
                        holder.tvShenqingshouchu.setBackgroundColor(getResources().getColor(R.color.white));
                    }
                } else {
                    holder.tvShenqingshouchu.setText("已出租");
                    holder.tvShenqingshouchu.setTextColor(getResources().getColor(R.color.white));
                    holder.tvShenqingshouchu.setBackgroundColor(getResources().getColor(R.color.bisque));
                    holder.tvShenqingshouchu.setClickable(false);
                    holder.tvShenqingshouchu.setEnabled(false);
                }


                if (n.getStatus().equals("1")) {
                    holder.tvShenqingshouchu.setVisibility(View.GONE);
                } else {
                    holder.tvShenqingshouchu.setVisibility(View.VISIBLE);
                }


                if (n.getThumb() != null && !n.getThumb().equals("")) {

                    if (n.getThumb().startsWith("http")) {
                        Glide.with(getActivity()).load(n.getThumb()).into(holder.ivPic);
                    } else {
                        String url = UrlFactory.TSFURL + n.getThumb();
                        Glide.with(getActivity()).load(url).into(holder.ivPic);
                    }
                } else {
                    //Bitmap bm=BitmapFactory.decodeResource(getResources(), R.drawable.img_no_url);
                    holder.ivPic.setImageResource(R.drawable.img_no_url);
                }

                holder.tvDetail.setText(n.getCityname() + " / " + n.getAreaname() + " / " + n.getXiaoquname());
                holder.tvLouceng.setText(n.getShi() + "室" + n.getTing() + "厅 / " + n.getMianji() + "平米");
                holder.tvTitle.setText(n.getTitle() + "");
                holder.tvZongjia.setText(n.getZujin() + "");


                String type = n.getPub_type();

                if (type.equals("1")) {
                    holder.tvChenHu.setText("直接出租");
                } else if (type.equals("2")) {
                    holder.tvChenHu.setText("委托经纪人");
                } else {
                    holder.tvChenHu.setText("委托平台");
                }


                holder.tvShenqingshouchu.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        applyPresenter.ApplyShouchu(n.getId(), "chuzu", userinfo.getUsername());
//					holder.tvShenqingshouchu.setText("已售出申请中");
                    }
                });


                holder.ivEdit.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //fragment之间传递数值
                        FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();

                        ATrentingSubmitFragment fragment = new ATrentingSubmitFragment();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("keyRenting", n);
                        bundle.putString("weituoedit", "2");
                        fragment.setArguments(bundle);
                        trans.replace(R.id.fl_agent_fragment, fragment);
                        trans.addToBackStack(null);
                        trans.commit();
                        //trans.commitAllowingStateLoss();
                    }
                });


            } catch (Exception e) {
                e.printStackTrace();
            }

            return convertView;

        }

        class ViewHolder {
            TextView tvTitle;
            TextView tvDetail;
            TextView tvLouceng;
            TextView tvShenqingshouchu;
            TextView tvChenHu;
            TextView tvZongjia;
            ImageView ivEdit;
            ImageView ivPic;
            LinearLayout rl_renting;

        }
    }


    @Override
    public void weituoSuccess(List<RentingDetail> rentings) {
        this.rentings = rentings;
        adapter = new SubmitRentingListAdapter(rentings, getActivity());
        lv_renting_weituo.setAdapter(adapter);
    }

    @Override
    public void weituofail(String info) {
//		MyToastShowCenter.CenterToast(getContext(), info);

    }

    @Override
    public void applyShouchu(String info) {
        MyToastShowCenter.CenterToast(getContext(), info);
        presenter.weituo(userid, table);
        adapter.notifyDataSetChanged();

    }

}
