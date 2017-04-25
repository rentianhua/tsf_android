package com.dumu.housego.framgent;

import java.util.ArrayList;
import java.util.List;

import com.dumu.housego.R;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.MyTextView;
import com.dumu.housego.entity.QiuzuANDQiuGou;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.presenter.IQiuZuListDeletePresenter;
import com.dumu.housego.presenter.IQiuZuListPresenter;
import com.dumu.housego.presenter.QiuZuListDeletePresenter;
import com.dumu.housego.presenter.QiuZuListPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.util.TimeTurnDate;
import com.dumu.housego.view.IQiuZuListDeleteView;
import com.dumu.housego.view.IQiuZuListView;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PTQiuZuListFragment extends Fragment implements IQiuZuListView, IQiuZuListDeleteView {
    private ListView lv_qiuzu_list;
    private LinearLayout ll_back_ptqiuzulist;
    private TextView tv_ptqiuzu_submit;
    private List<QiuzuANDQiuGou> qiuzulists;
    private ATQiuZuLsitAdapter adapter;
    private IQiuZuListPresenter listpresenter;
    private IQiuZuListDeletePresenter deletePresenter;
    private UserInfo userinfo;
    private PopupWindow pop;
    private LinearLayout ll_popup_delete;
    private int position;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pt_qiuzhulist, null);
        listpresenter = new QiuZuListPresenter(this);
        deletePresenter = new QiuZuListDeletePresenter(this);
        initView(view);
        PopDelete();
        FontHelper.injectFont(view);
        setListener(view);
        return view;
    }

    private void PopDelete() {

        pop = new PopupWindow(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.item_popupwindows_delete, null);

        ll_popup_delete = (LinearLayout) view.findViewById(R.id.ll_popup_delete);
        pop.setWidth(LayoutParams.MATCH_PARENT);
        pop.setHeight(LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_delete);

        Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_sure);
        Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_cancel);

        parent.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                pop.dismiss();
                ll_popup_delete.clearAnimation();
            }
        });

        bt1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String lock = qiuzulists.get(position).getLock();
                String ID = qiuzulists.get(position).getId();
                String userid = userinfo.getUserid();
                String username = userinfo.getUsername();

                deletePresenter.DeleteQiuZuList(ID, userid, username);
                if (lock.equals("0")) {
                    qiuzulists.remove(position);
                    adapter.notifyDataSetChanged();
                }

                pop.dismiss();
                ll_popup_delete.clearAnimation();
            }
        });

        bt2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup_delete.clearAnimation();
            }
        });

    }

    @Override
    public void onResume() {

        userinfo = HouseGoApp.getContext().getCurrentUserInfo();
        String username = userinfo.getUsername();
        listpresenter.qiuzulist(username, "userqiuzu");

        super.onResume();
    }

    private void initView(View view) {
        lv_qiuzu_list = (ListView) view.findViewById(R.id.lv_qiuzu_list);
        ll_back_ptqiuzulist = (LinearLayout) view.findViewById(R.id.ll_back_ptqiuzulist);
        tv_ptqiuzu_submit = (TextView) view.findViewById(R.id.tv_ptqiuzu_submit);

    }

    private void setListener(final View PopView) {
        tv_ptqiuzu_submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new PTQiuZuSubmitFragment();
                FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
                trans.replace(R.id.rl_container, fragment);
                trans.commitAllowingStateLoss();
            }
        });

        ll_back_ptqiuzulist.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                getActivity().finish();

            }
        });

        lv_qiuzu_list.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                PTQiuZuListFragment.this.position = position;

                Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.activity_translate_in);

                ll_popup_delete.setAnimation(anim);
                pop.showAtLocation(PopView, Gravity.BOTTOM, 0, 0);
                return true;
            }
        });

    }

    @Override
    public void QiuZuListSuccess(List<QiuzuANDQiuGou> qiuzulists) {
        this.qiuzulists = qiuzulists;
        adapter = new ATQiuZuLsitAdapter(qiuzulists, getContext());
        lv_qiuzu_list.setAdapter(adapter);
    }

    @Override
    public void QiuZuListFail(String info) {
        this.qiuzulists = new ArrayList<QiuzuANDQiuGou>();
        adapter = new ATQiuZuLsitAdapter(qiuzulists, getContext());
        lv_qiuzu_list.setAdapter(adapter);
    }

    @Override
    public void qiuzuDelete(String info) {
        MyToastShowCenter.CenterToast(getActivity(), info);
        listpresenter.qiuzulist(userinfo.getUsername(), "userqiuzu");
    }

    public class ATQiuZuLsitAdapter extends BaseAdapter {
        private List<QiuzuANDQiuGou> blocktrades;
        private Context context;
        private LayoutInflater Inflater;
        private Typeface typeface;

        public ATQiuZuLsitAdapter(List<QiuzuANDQiuGou> blocktrades, Context context) {
            super();
            this.blocktrades = blocktrades;
            this.context = context;
            this.Inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return blocktrades == null ? 0 : blocktrades.size();
        }

        @Override
        public QiuzuANDQiuGou getItem(int position) {

            return blocktrades.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = Inflater.inflate(R.layout.item_qiuzu_at, null);
                holder = new ViewHolder();
                holder.tv_qiuzu_area = (MyTextView) convertView.findViewById(R.id.tv_qiuzu_area);
                holder.tv_qiuzu_jine = (MyTextView) convertView.findViewById(R.id.tv_qiuzu_jine);
                holder.tv_qiuzu_phone = (MyTextView) convertView.findViewById(R.id.tv_qiuzu_phone);
                holder.tvtime = (MyTextView) convertView.findViewById(R.id.tv_time);
                holder.delete = (ImageView) convertView.findViewById(R.id.delete);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            final QiuzuANDQiuGou n = getItem(position);

            if (n.getLock().equals("1")) {
                holder.delete.setVisibility(View.GONE);
            } else {
                holder.delete.setVisibility(View.VISIBLE);
            }

            holder.delete.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    deletePresenter.DeleteQiuZuList(n.getId(), userinfo.getUserid(), userinfo.getUsername());
                }
            });

            holder.tv_qiuzu_area.setText("深圳" + n.getCity_name() + n.getArea_name());
            holder.tv_qiuzu_jine.setText(n.getZujinrange() + "");
            holder.tv_qiuzu_phone.setText(n.getChenghu() + " " + n.getUsername());

            long time = Long.valueOf(n.getInputtime());
            String t = TimeTurnDate.getStringDateMoreMORE(time);
            holder.tvtime.setText(t);

            return convertView;
        }

        class ViewHolder {
            ImageView delete;
            TextView tvtime;
            TextView tv_qiuzu_area;
            TextView tv_qiuzu_phone;
            TextView tv_qiuzu_jine;
        }
    }
}
