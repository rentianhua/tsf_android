package com.dumu.housego;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMapStatusChangeListener;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.adapter.ErShouFangMapHouseAdapter;
import com.dumu.housego.adapter.RentingMapHosueAdapter;
import com.dumu.housego.entity.AreaHouse;
import com.dumu.housego.entity.BaiduMapInfo;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.RentingRecommendData;
import com.dumu.housego.entity.Street;
import com.dumu.housego.entity.XiaoquMapHouse;
import com.dumu.housego.entity.shenZhenLocation;
import com.dumu.housego.presenter.IMapHouseDataPresenter;
import com.dumu.housego.presenter.IXiaoquMapHousePresenter;
import com.dumu.housego.presenter.MapHouseDataPresenter;
import com.dumu.housego.presenter.XiaoquMapHousePresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.view.IMapHouseDataView;
import com.dumu.housego.view.IXiaoquMapHouseView;

public class MapHouseMainActivity extends BaseActivity implements IMapHouseDataView, IXiaoquMapHouseView {
    private RadioButton rbErShouFang, rbRenting;
    private LinearLayout llMapHouseBack;
    private IMapHouseDataPresenter areapresenter;
    private IXiaoquMapHousePresenter xiaoqupresenter;
    // 覆盖物相关
    private BitmapDescriptor mMarker;
    private LinearLayout ll_marker_showlay;
    private TextView tvMarkerTitle, tvMarkerHouseCount, tvMarkerCancle;
    private ListView lvMarker;
    private BaiduMap mBaiduMAP;
    private MapView mMapView;
    private List<AreaHouse> Areahouses = new ArrayList<AreaHouse>();
    private List<AreaHouse> streetes = new ArrayList<AreaHouse>();
    private List<XiaoquMapHouse> xiaoqus = new ArrayList<XiaoquMapHouse>();

    private List<ErShouFangRecommendData> ershous = new ArrayList<ErShouFangRecommendData>();
    private List<RentingRecommendData> rentings = new ArrayList<RentingRecommendData>();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Areahouses = (List<AreaHouse>) msg.obj;
                    showCityHouse(shenZhenLocation.locations, Areahouses);
                    break;

                default:
                    break;
            }
        }
    };
    private String fromtable;
    private String city = "2";
    private String area;
    private String xiaoqu;

    private ErShouFangMapHouseAdapter ershouadapter;
    private RentingMapHosueAdapter rentingadapter;
    private String xiaoquname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_house_main);
        FontHelper.injectFont(findViewById(android.R.id.content));
        setViews();
        setListeners();
        initMarker();
//		rbErShouFang.setTextColor(getResources().getColor(R.color.button_ckeck));
        areapresenter = new MapHouseDataPresenter(this);
        xiaoqupresenter = new XiaoquMapHousePresenter(this);

        fromtable = "ershou";
    }

    @Override
    protected void onResume() {
        LatLng latlng = new LatLng(22.546054, 114.025974);
        MapStatus status = new MapStatus.Builder().target(latlng).zoom(12.0f).build();
        MapStatusUpdate msu = MapStatusUpdateFactory.newMapStatus(status);
        mBaiduMAP.animateMapStatus(msu);
        areapresenter.LoadMapHouse(fromtable);
        areapresenter.LoadAreaMapHouse(city, fromtable);
        mMapView.onResume();
        super.onResume();
    }

    private void showCityHouse(List<shenZhenLocation> locations, List<AreaHouse> Areahouses) {
        mBaiduMAP.clear();
        LatLng lat = null;
        Marker marker = null;
        OverlayOptions options;
        InfoWindow infoWindow;

        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.map_house_show, null);
        TextView tv = (TextView) v.findViewById(R.id.tv_map_house);
        for (shenZhenLocation l : locations) {
            for (AreaHouse a : Areahouses) {
                if (a.getId().trim().equals(l.getId().trim())) {
                    tv.
                            setText(
                                    a.getName()
                                            + "\n" +
                                            a.getHouse_count());
                    break;
                }
            }
            lat = new LatLng(l.getLongitude(), l.getLatitude());
            mMarker = BitmapDescriptorFactory.fromView(tv);

            options = new MarkerOptions().position(lat).icon(mMarker).draggable(true);
            // 添加marker
            marker = (Marker) mBaiduMAP.addOverlay(options);

            // 使用marker携带info信息，当点击事件的时候可以通过marker获得info信息
            Bundle bundle = new Bundle();

            BaiduMapInfo inf = new BaiduMapInfo();
            inf.setLongitude(l.getLongitude());
            inf.setLatitude(l.getLatitude());
            bundle.putString("TAG", "CITY");
            bundle.putSerializable("info", inf);

            marker.setExtraInfo(bundle);
        }
    }


    protected void showAreaHouse(Marker marke) {
        mBaiduMAP.clear();

        Bundle bundle = marke.getExtraInfo();
        BaiduMapInfo info = (BaiduMapInfo) bundle.getSerializable("info");
        LatLng winLatLng = new LatLng(info.getLongitude(), info.getLatitude());

//		LatLng latlng = new LatLng(longitude,latitude);
        MapStatus status = new MapStatus.Builder().target(winLatLng).zoom(14.0f).build();
        MapStatusUpdate msu = MapStatusUpdateFactory.newMapStatus(status);
        mBaiduMAP.animateMapStatus(msu);

        LatLng lat = null;
        Marker marker = null;
        OverlayOptions options;

        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.map_house_show, null);
        TextView tv = (TextView) v.findViewById(R.id.tv_map_house);

        for (Street l : Street.streets) {
            for (AreaHouse a : streetes) {
                if (a.getName().trim().equals(l.getName().trim()) && !a.getName().equals("")) {

                    tv.
                            setText(
                                    a.getName()
                                            + "\n" +
                                            a.getHouse_count());
                } else {
                }
            }
            lat = new LatLng(l.getLatitude(), l.getLongitude());
            mMarker = BitmapDescriptorFactory.fromView(tv);

            options = new MarkerOptions().position(lat).icon(mMarker).draggable(true);
            // 添加marker
            marker = (Marker) mBaiduMAP.addOverlay(options);


            // 使用marker携带info信息，当点击事件的时候可以通过marker获得info信息
            Bundle bundle1 = new Bundle();

            BaiduMapInfo inf = new BaiduMapInfo();
            inf.setLongitude(l.getLongitude());
            inf.setLatitude(l.getLatitude());
            inf.setName(l.getId());
            bundle1.putString("TAG", "AREA");
            bundle1.putSerializable("infoe", inf);
            marker.setExtraInfo(bundle1);
        }
    }

    //手动缩放方法
    protected void ChangeshowAreaHouse() {
        mBaiduMAP.clear();
        LatLng lat = null;
        Marker marker = null;
        OverlayOptions options;

        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.map_house_show, null);
        TextView tv = (TextView) v.findViewById(R.id.tv_map_house);

        for (Street l : Street.streets) {
            for (AreaHouse a : streetes) {
                if (a.getName().trim().equals(l.getName().trim()) && !a.getName().equals("")) {

                    tv.
                            setText(
                                    a.getName()
                                            + "\n" +
                                            a.getHouse_count());
                } else {
                }
            }
            lat = new LatLng(l.getLatitude(), l.getLongitude());
            mMarker = BitmapDescriptorFactory.fromView(tv);

            options = new MarkerOptions().position(lat).icon(mMarker).draggable(true);
            // 添加marker
            marker = (Marker) mBaiduMAP.addOverlay(options);


            // 使用marker携带info信息，当点击事件的时候可以通过marker获得info信息
            Bundle bundle1 = new Bundle();

            BaiduMapInfo inf = new BaiduMapInfo();
            inf.setLongitude(l.getLongitude());
            inf.setLatitude(l.getLatitude());
            inf.setName(l.getId());
            bundle1.putString("TAG", "AREA");
            bundle1.putSerializable("infoe", inf);
            marker.setExtraInfo(bundle1);
        }
    }

    protected void showXiaoquHouse(Marker marke) {
        mBaiduMAP.clear();
        Bundle bundle = marke.getExtraInfo();

        BaiduMapInfo info = (BaiduMapInfo) bundle.getSerializable("infoe");
        area = info.getName();
        //执行获取小区请求
        xiaoqupresenter.Xiaoqu(area, fromtable);
        //

        LatLng winLatLng = new LatLng(info.getLatitude(), info.getLongitude());
        //定位到
        MapStatus status = new MapStatus.Builder().target(winLatLng).zoom(16.0f).build();
        MapStatusUpdate msu = MapStatusUpdateFactory.newMapStatus(status);
        mBaiduMAP.animateMapStatus(msu);
    }

    private void initMarker() {
        mMarker = BitmapDescriptorFactory.fromResource(R.drawable.bg_good_rating_ratio);
    }

    private void setViews() {
        mMapView = (MapView) findViewById(R.id.maphouse_bmapView);
        mBaiduMAP = mMapView.getMap();
        mBaiduMAP.setMaxAndMinZoomLevel(17.0f, 11.0f);
        mMapView.showZoomControls(false);
        mMapView.showScaleControl(false);


        rbErShouFang = (RadioButton) findViewById(R.id.btn_ershoufang);
        rbRenting = (RadioButton) findViewById(R.id.btn_renting);
        llMapHouseBack = (LinearLayout) findViewById(R.id.ll_map_house_back);
        ll_marker_showlay = (LinearLayout) findViewById(R.id.ll_marker_showlay);
        tvMarkerCancle = (TextView) findViewById(R.id.tv_marke_canle);
        tvMarkerHouseCount = (TextView) findViewById(R.id.tv_marke_housecount);
        tvMarkerTitle = (TextView) findViewById(R.id.tv_marke_title);
        lvMarker = (ListView) findViewById(R.id.lv_marke_list);
    }

    private void setListeners() {
        rbErShouFang.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mBaiduMAP.clear();
                MapHouseMainActivity.this.fromtable = "ershou";
                onResume();
//				rbErShouFang.setTextColor(getResources().getColor(R.color.button_ckeck));
//				rbRenting.setTextColor(getResources().getColor(R.color.button_unckeck));
            }
        });

        rbRenting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mBaiduMAP.clear();
                MapHouseMainActivity.this.fromtable = "chuzu";
                onResume();
//				rbErShouFang.setTextColor(getResources().getColor(R.color.button_unckeck));
//				rbRenting.setTextColor(getResources().getColor(R.color.button_ckeck));
            }
        });

        llMapHouseBack.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBaiduMAP.setOnMarkerClickListener(new OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marke) {
                Bundle bundle = marke.getExtraInfo();
                String TAG = bundle.getString("TAG");
                if (TAG.equals("CITY")) {
                    showAreaHouse(marke);
                } else if (TAG.equals("AREA")) {
                    showXiaoquHouse(marke);
                } else {
                    ShowAllHouse(marke);
                }
//				if(mBaiduMAP.getMapStatus().zoom<14.0f){
//					showAreaHouse(marke);
//				}else if(mBaiduMAP.getMapStatus().zoom>=14.0f && mBaiduMAP.getMapStatus().zoom<16.0f){
//					showXiaoquHouse(marke);
//				}else{
//					ShowAllHouse(marke);
//				}

                return true;
            }
        });

        mBaiduMAP.setOnMapStatusChangeListener(new OnMapStatusChangeListener() {

            @Override
            public void onMapStatusChangeStart(MapStatus status) {
            }

            @Override
            public void onMapStatusChangeFinish(MapStatus arg0) {
                if (arg0.zoom >= 12.0f && arg0.zoom < 14.0f) {
                    showCityHouse(shenZhenLocation.locations, Areahouses);
                } else if (arg0.zoom >= 14 && arg0.zoom < 16) {
                    ChangeshowAreaHouse();
                }
            }

            @Override
            public void onMapStatusChange(MapStatus arg0) {

            }
        });

        tvMarkerCancle.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                ll_marker_showlay.setVisibility(View.GONE);

            }
        });

        mBaiduMAP.setOnMapClickListener(new OnMapClickListener() {

            @Override
            public boolean onMapPoiClick(MapPoi arg0) {

                return true;
            }

            @Override
            public void onMapClick(LatLng arg0) {
                ll_marker_showlay.setVisibility(View.GONE);

            }
        });

        lvMarker.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (fromtable.equals("ershou")) {
                    Intent i = new Intent(MapHouseMainActivity.this, ErShouFangDetailsActivity.class);
                    String catid = ershous.get(position).getCatid();
                    String ID = ershous.get(position).getId();
                    i.putExtra("catid", catid);
                    i.putExtra("id", ID);
                    startActivity(i);
                } else {
                    String catid = rentings.get(position).getCatid();
                    String Id = rentings.get(position).getId();
                    Intent i = new Intent(getApplicationContext(), RentingDetailActivity.class);
                    i.putExtra("catid", catid);
                    i.putExtra("id", Id);
                    startActivity(i);
                }
            }
        });
    }

    @Override
    public void ShowMapHouse(List<AreaHouse> areahouses) {
        Message msg = new Message();
        msg.what = 1;
        msg.obj = areahouses;
        handler.sendMessage(msg);
    }

    @Override
    public void ShowAreaMapHouse(List<AreaHouse> streetes) {
        this.streetes = streetes;
    }

    @Override
    public void xiaoquSuccess(List<XiaoquMapHouse> xiaoqus) {
        this.xiaoqus = xiaoqus;

        //显示
        LatLng lat = null;
        Marker marker = null;
        OverlayOptions options;

        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.map_house_show2, null);
        TextView tv = (TextView) v.findViewById(R.id.tv_map_house);
        if (!xiaoqus.equals("") && xiaoqus != null) {
            for (XiaoquMapHouse x : xiaoqus) {
                String jwd = x.getJingweidu();
                if (jwd != null && jwd.length() > 0) {
                    String[] arr = jwd.split(",");
                    if (arr.length >= 2) {
                        String j = arr[0].toString();
                        String w = arr[1].toString();

                        double latitude = Double.valueOf(j);
                        double longitude = Double.valueOf(w);
                        LatLng latLng = new LatLng(longitude, latitude);
                        tv.setText(x.getTitle() + "(" + x.getHouse_count() + ")");

                        mMarker = BitmapDescriptorFactory.fromView(tv);

                        options = new MarkerOptions().position(latLng).icon(mMarker).draggable(true);
                        // 添加marker
                        marker = (Marker) mBaiduMAP.addOverlay(options);


                        // 使用marker携带info信息，当点击事件的时候可以通过marker获得info信息
                        Bundle bundle1 = new Bundle();

                        BaiduMapInfo inf = new BaiduMapInfo();
                        inf.setName(x.getId());
                        inf.setDescription(x.getTitle() + "," + x.getHouse_count());
                        bundle1.putString("TAG", "XIAOQU");
                        bundle1.putSerializable("infos", inf);
                        marker.setExtraInfo(bundle1);
                    }
                }
            }
        }
    }


    protected void ShowAllHouse(Marker marke) {
        this.ershous.clear();
        this.rentings.clear();
        Bundle bundle = marke.getExtraInfo();
        BaiduMapInfo info = (BaiduMapInfo) bundle.getSerializable("infos");
        xiaoqu = info.getName();
        String title = info.getDescription().split(",")[0];
        String count = info.getDescription().split(",")[1];

        tvMarkerTitle.setText(title);
        tvMarkerHouseCount.setText("在售" + count + "套房源");
        xiaoquname = title;
        //执行获取小区所以房源请求
        xiaoqupresenter.Allxiaoqu(xiaoqu, fromtable);
        ll_marker_showlay.setVisibility(View.VISIBLE);
    }

    @Override
    public void AllxiaoquhouseErshou(List<ErShouFangRecommendData> ershous) {
        this.ershous = ershous;
//		ll_marker_showlay.setVisibility(View.VISIBLE);
        if (!ershous.equals("") && ershous != null) {
            ershouadapter = new ErShouFangMapHouseAdapter(ershous, getApplicationContext(), xiaoquname);
            lvMarker.setAdapter(ershouadapter);
        }
    }

    @Override
    public void AllxiaoquhouseRenting(List<RentingRecommendData> rentings) {
        this.rentings = rentings;
//		ll_marker_showlay.setVisibility(View.VISIBLE);
        if (!rentings.equals("") && rentings != null) {
            rentingadapter = new RentingMapHosueAdapter(rentings, getApplicationContext(), xiaoquname);
            lvMarker.setAdapter(rentingadapter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
}
