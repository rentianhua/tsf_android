package com.dumu.housego;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.InfoWindow.OnInfoWindowClickListener;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.entity.BaiduMapInfo;
import com.dumu.housego.util.MyToastShowCenter;

public class BaiduMapActivity extends BaseActivity {
	private BaiduMap mBaiduMAP;
	private MapView mMapView;
	LatLng latLng = null;
	private BitmapDescriptor mCurrentMarker;
	private PoiSearch mPoiSearch;
	private RadioButton rbMapBank, rbMapBus, rbMAPSubWay, rbMapEducation, rbMapHospital, rbMapGame, rbMapShopping,
			rbMapSport, rbMapEating;

	private static String BANK_MARK = "map_pin_icon_bank";
	private static String BUS_MARK = "map_pin_icon_transportation";
	private static String SUBWAY_MARK = "map_pin_icon_subway";
	private static String EDUTION_MARK = "map_pin_icon_education";
	private static String HISPTAL_MARK = "map_pin_icon_hospital";
	private static String GAME_MARK = "map_pin_icon_entertainment";
	private static String SHOPPING_MARK = "map_pin_icon_shopping";
	private static String SPORT_MARK = "map_pin_icon_sport";
	private static String EATING_MARK = "map_pin_icon_restaraunt";

	BitmapDescriptor bitmap = null;

	private String type;

	private LinearLayout ll_baidumap_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_baidu_map);
		double latitude = getIntent().getDoubleExtra("latitude", -1);
		double longitude = getIntent().getDoubleExtra("longitude", -1);
		latLng = new LatLng(longitude, latitude);
		initView();
		setListener();
		showMap();

	}

	private void setListener() {
		rbMapBank.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mBaiduMAP.clear();
				type = "银行";
				PoiNearbySearchOption searchOption = new PoiNearbySearchOption();
				searchOption.location(latLng);
				searchOption.keyword(type);
				searchOption.pageNum(0);
				searchOption.radius(2000);
				mPoiSearch.searchNearby(searchOption);

			}
		});

		rbMapBus.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mBaiduMAP.clear();
				type = "公交";
				PoiNearbySearchOption searchOption = new PoiNearbySearchOption();
				searchOption.location(latLng);
				searchOption.keyword(type);
				searchOption.pageNum(0);
				searchOption.radius(2000);
				mPoiSearch.searchNearby(searchOption);
			}
		});

		rbMapEating.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mBaiduMAP.clear();
				type = "美食";
				PoiNearbySearchOption searchOption = new PoiNearbySearchOption();
				searchOption.location(latLng);
				searchOption.keyword(type);
				searchOption.pageNum(0);
				searchOption.radius(2000);
				mPoiSearch.searchNearby(searchOption);
			}
		});
		rbMapEducation.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mBaiduMAP.clear();
				type = "教育";
				PoiNearbySearchOption searchOption = new PoiNearbySearchOption();
				searchOption.location(latLng);
				searchOption.keyword(type);
				searchOption.pageNum(0);
				searchOption.radius(2000);
				mPoiSearch.searchNearby(searchOption);
			}
		});
		rbMapGame.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mBaiduMAP.clear();
				type = "休闲";
				PoiNearbySearchOption searchOption = new PoiNearbySearchOption();
				searchOption.location(latLng);
				searchOption.keyword(type);
				searchOption.pageNum(0);
				searchOption.radius(2000);
				mPoiSearch.searchNearby(searchOption);
			}
		});
		rbMapHospital.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mBaiduMAP.clear();
				type = "医院";
				PoiNearbySearchOption searchOption = new PoiNearbySearchOption();
				searchOption.location(latLng);
				searchOption.keyword(type);
				searchOption.pageNum(0);
				searchOption.radius(2000);
				mPoiSearch.searchNearby(searchOption);
			}
		});

		rbMapShopping.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mBaiduMAP.clear();
				type = "购物";
				PoiNearbySearchOption searchOption = new PoiNearbySearchOption();
				searchOption.location(latLng);
				searchOption.keyword(type);
				searchOption.pageNum(0);
				searchOption.radius(2000);
				mPoiSearch.searchNearby(searchOption);
			}
		});

		rbMapSport.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mBaiduMAP.clear();
				type = "健身";
				PoiNearbySearchOption searchOption = new PoiNearbySearchOption();
				searchOption.location(latLng);
				searchOption.keyword(type);
				searchOption.pageNum(0);
				searchOption.radius(2000);
				mPoiSearch.searchNearby(searchOption);
			}
		});

		rbMAPSubWay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mBaiduMAP.clear();
				type = "地铁";
				PoiNearbySearchOption searchOption = new PoiNearbySearchOption();
				searchOption.location(latLng);
				searchOption.keyword(type);
				searchOption.pageNum(0);
				searchOption.radius(2000);
				mPoiSearch.searchNearby(searchOption);
			}
		});

		mBaiduMAP.setOnMarkerClickListener(new OnMarkerClickListener() {

			@Override
			public boolean onMarkerClick(Marker marker) {

				showInfo(marker);
				return true;
			}
		});

		mBaiduMAP.setOnMapClickListener(new OnMapClickListener() {

			@Override
			public boolean onMapPoiClick(MapPoi arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void onMapClick(LatLng arg0) {
				mBaiduMAP.hideInfoWindow();

			}
		});

		ll_baidumap_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});

	}

	protected void showInfo(Marker marker) {

		try {
			// 从marker中获取info信息
			Bundle bundle = marker.getExtraInfo();
			BaiduMapInfo info = (BaiduMapInfo) bundle.getSerializable("info");
			LatLng winLatLng = new LatLng(info.getLatitude(), info.getLongitude());
			// 将信息显示在界面上

			InfoWindow infoWindow;
			// TextView tv=(TextView) findViewById(R.layout.mark_baidu_map);
			TextView tv = new TextView(this);
			tv.setBackgroundResource(R.drawable.bg_overitem_1);
			tv.setBackgroundColor(android.graphics.Color.WHITE);
			tv.setTextColor(android.graphics.Color.BLACK);
			tv.setPadding(30, 20, 30, 20);
			tv.setText(info.getName() + "");


			BitmapDescriptor bit = new BitmapDescriptorFactory().fromView(tv);

			OnInfoWindowClickListener infowindowListener = new OnInfoWindowClickListener() {

				@Override
				public void onInfoWindowClick() {
					mBaiduMAP.hideInfoWindow();
				}
			};

			infoWindow = new InfoWindow(bit, winLatLng, -47, infowindowListener);
			mBaiduMAP.showInfoWindow(infoWindow);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void showMap() {
		MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);

		mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.icon_mylocation);
		// ����MarkerOption�������ڵ�ͼ�����Marker
		OverlayOptions option = new MarkerOptions().position(latLng).icon(mCurrentMarker);
		// �ڵ�ͼ�����Marker������ʾ
		mBaiduMAP.addOverlay(option);
		mBaiduMAP.animateMapStatus(msu);

	}

	private void initView() {
		mMapView = (MapView) findViewById(R.id.baidu_map);
		mBaiduMAP = mMapView.getMap();
		mMapView.showZoomControls(false);
		mMapView.showScaleControl(false);

		ll_baidumap_back = (LinearLayout) findViewById(R.id.ll_baidumap_back);
		// 设置兴趣点
		mPoiSearch = PoiSearch.newInstance();
		// 第三步，设置POI检索监听者；
		OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener() {

			public void onGetPoiResult(PoiResult result) {
				showMap();
				// 获取POI检索结果
				if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
					MyToastShowCenter.CenterToast(getBaseContext(), "没有找到结果！");
					return;
				}

				LatLng latLng = null;
				OverlayOptions options = null;
				Marker marker = null;

				List<PoiInfo> infos = result.getAllPoi();
				for (PoiInfo p : infos) {

					latLng = p.location;
					if (type.equals("银行")) {
						bitmap = BitmapDescriptorFactory.fromResource(R.drawable.map_pin_icon_bank);
					} else if (type.equals("公交")) {
						bitmap = BitmapDescriptorFactory.fromResource(R.drawable.map_pin_icon_transportation);
					} else if (type.equals("地铁")) {
						bitmap = BitmapDescriptorFactory.fromResource(R.drawable.map_pin_icon_subway);
					} else if (type.equals("教育")) {
						bitmap = BitmapDescriptorFactory.fromResource(R.drawable.map_pin_icon_education);
					} else if (type.equals("医院")) {
						bitmap = BitmapDescriptorFactory.fromResource(R.drawable.map_pin_icon_hospital);
					} else if (type.equals("休闲")) {
						bitmap = BitmapDescriptorFactory.fromResource(R.drawable.map_pin_icon_entertainment);
					} else if (type.equals("购物")) {
						bitmap = BitmapDescriptorFactory.fromResource(R.drawable.map_pin_icon_shopping);
					} else if (type.equals("健身")) {
						bitmap = BitmapDescriptorFactory.fromResource(R.drawable.map_pin_icon_sport);
					} else if (type.equals("美食")) {
						bitmap = BitmapDescriptorFactory.fromResource(R.drawable.map_pin_icon_restaraunt);
						
					}

					options = new MarkerOptions().position(p.location).icon(bitmap).draggable(true);
					// 添加marker
					marker = (Marker) mBaiduMAP.addOverlay(options);
					
					
					// 使用marker携带info信息，当点击事件的时候可以通过marker获得info信息
					Bundle bundle = new Bundle();
					// info必须实现序列化接口

					BaiduMapInfo inf = new BaiduMapInfo();
					inf.setLongitude(p.location.longitude);
					inf.setLatitude(p.location.latitude);
					inf.setName(p.name);

					bundle.putSerializable("info", inf);
					marker.setExtraInfo(bundle);

				}

			}

			public void onGetPoiDetailResult(PoiDetailResult result) {

			}

			@Override
			public void onGetPoiIndoorResult(PoiIndoorResult arg0) {
				// TODO Auto-generated method stub

			}
		};

		mPoiSearch.setOnGetPoiSearchResultListener(poiListener);

		/**
		 * �ı��ͼ�ı�����
		 */
		MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(17.0f);
		mBaiduMAP.setMapStatus(msu);

		rbMapBank = (RadioButton) findViewById(R.id.rb_baidumap_bank);
		rbMapBus = (RadioButton) findViewById(R.id.rb_baidumap_gongjiao);
		rbMapEating = (RadioButton) findViewById(R.id.rb_baidumap_meishi);
		rbMapEducation = (RadioButton) findViewById(R.id.rb_baidumap_jiaoyu);
		rbMapGame = (RadioButton) findViewById(R.id.rb_baidumap_xiuxian);
		rbMapHospital = (RadioButton) findViewById(R.id.rb_baidumap_yiyuan);
		rbMapShopping = (RadioButton) findViewById(R.id.rb_baidumap_shop);
		rbMapSport = (RadioButton) findViewById(R.id.rb_baidumap_jianshen);
		rbMAPSubWay = (RadioButton) findViewById(R.id.rb_baidumap_ditie);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
		mMapView.onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
		mMapView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		// 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
		mMapView.onPause();
	}
}
