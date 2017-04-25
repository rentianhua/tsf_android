package com.dumu.housego;

import java.util.ArrayList;
import java.util.List;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
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
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.adapter.SearchBaiDuAdapter;
import com.dumu.housego.util.MyToastShowCenter;

public class GetLocationActivity extends BaseActivity {

	private static final int LOCATION = 0;
	private BaiduMap mBaiduMAP;
	private MapView mMapView;
	private LatLng latlng;
	private String poiString;
	private LatLng position;
	private boolean isFirstLocate = true;
	public LocationClient mLocationClient = null;
	private MyLocationListener mLocationListener;
	
	private BitmapDescriptor mCurrentMarker;
	private ListView lv_searchresult;
	private TextView tv_detail_cacle;
	private EditText et_detail_search;
	private PoiSearch mPoiSearch;
	private 	List<PoiInfo> infos;
	
	private int Posi;
	private BitmapDescriptor mMarker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_location);
		mLocationClient = new LocationClient(getApplicationContext()); // 声明LocationClient类
		mLocationClient.registerLocationListener(new MyLocationListener()); // 注册监听函数
		
		initView();
		setListener();
		
		   List<String> permissionList = new ArrayList<String>();
		   
		   if(Build.VERSION.SDK_INT>=23){
		   
	        if (ContextCompat.checkSelfPermission(GetLocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
	            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
	        }
	        if (ContextCompat.checkSelfPermission(GetLocationActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
	            permissionList.add(Manifest.permission.READ_PHONE_STATE);
	        }
	        if (ContextCompat.checkSelfPermission(GetLocationActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
	            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
	        }
	        if (!permissionList.isEmpty()) {
	            String [] permissions = permissionList.toArray(new String[permissionList.size()]);
	            ActivityCompat.requestPermissions(GetLocationActivity.this, permissions, 1);
	        } else {
	            requestLocation();
	        }
	        
	        
		   }else if(Build.VERSION.SDK_INT<23){
			 initDown();
		   }
	}
	
	
	

    private void initDown() {
    	initLocation();
    	mLocationClient.start();
	}




	private void navigateTo(BDLocation location) {
        if (isFirstLocate) {
            Toast.makeText(this, "nav to " + location.getAddrStr(), Toast.LENGTH_SHORT).show();
            
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
            
            MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(ll);
            MapStatusUpdate m = MapStatusUpdateFactory.zoomTo(16f);
            mBaiduMAP.setMapStatus(m);
			mBaiduMAP.animateMapStatus(msu);
            
            isFirstLocate = false;
        }
        MyLocationData.Builder locationBuilder = new MyLocationData.
                Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData = locationBuilder.build();
        mBaiduMAP.setMyLocationData(locationData);
    }

    private void requestLocation() {
        initLocation();
        mLocationClient.start();
    }
	
    
    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开GPS  
        option.setCoorType("bd09ll");
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置定位模式  
        option.setScanSpan(5000); // 设置发起定位请求的间隔时间为5000ms  
        option.setIsNeedAddress(true); // 返回的定位结果包含地址信息  
        option.setNeedDeviceDirect(true); // 返回的定位结果包含手机机头的方向 
        
        mLocationClient.setLocOption(option);
    }
	
	
    @Override
    protected void onResume() {
    	
        super.onResume();
        mMapView.onResume();
    }
    
    @Override
    protected void onStart() {
    	
    	super.onStart();
    	mBaiduMAP.setMyLocationEnabled(true);
    	mLocationClient.start();
    }
    

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        mMapView.onDestroy();
        mBaiduMAP.setMyLocationEnabled(false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                } else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
//            StringBuilder currentPosition = new StringBuilder();
//            currentPosition.append("纬度：").append(location.getLatitude()).append("\n");
//            currentPosition.append("经线：").append(location.getLongitude()).append("\n");
//            currentPosition.append("国家：").append(location.getCountry()).append("\n");
//            currentPosition.append("省：").append(location.getProvince()).append("\n");
//            currentPosition.append("市：").append(location.getCity()).append("\n");
//            currentPosition.append("区：").append(location.getDistrict()).append("\n");
//            currentPosition.append("街道：").append(location.getStreet()).append("\n");
//            currentPosition.append("定位方式：");
//            if (location.getLocType() == BDLocation.TypeGpsLocation) {
//                currentPosition.append("GPS");
//            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
//                currentPosition.append("网络");
//            }
//            positionText.setText(currentPosition);
        	
       	 if(Build.VERSION.SDK_INT<23){
       		// map view 销毁后不在处理新接收的位置
 			if (location == null || mMapView == null) {
 				return;
 			}
 		
        	
        	
        	if(isFirstLocate){
        		
        		   //Receive Location
                StringBuffer sb = new StringBuffer(256);
                sb.append("time : ");
                sb.append(location.getTime());
                sb.append("\nerror code : ");
                sb.append(location.getLocType());
                sb.append("\nlatitude : ");
                sb.append(location.getLatitude());
                sb.append("\nlontitude : ");
                sb.append(location.getLongitude());
                sb.append("\nradius : ");
                sb.append(location.getRadius());
                if (location.getLocType() == BDLocation.TypeGpsLocation){// GPS定位结果
                    sb.append("\nspeed : ");
                    sb.append(location.getSpeed());// 单位：公里每小时
                    sb.append("\nsatellite : ");
                    sb.append(location.getSatelliteNumber());
                    sb.append("\nheight : ");
                    sb.append(location.getAltitude());// 单位：米
                    sb.append("\ndirection : ");
                    sb.append(location.getDirection());// 单位度
                    sb.append("\naddr : ");
                    sb.append(location.getAddrStr());
                    sb.append("\ndescribe : ");
                    sb.append("gps定位成功");
     
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation){// 网络定位结果
                    sb.append("\naddr : ");
                    sb.append(location.getAddrStr());
                    //运营商信息
                    sb.append("\noperationers : ");
                    sb.append(location.getOperators());
                    sb.append("\ndescribe : ");
                    sb.append("网络定位成功");
                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                    sb.append("\ndescribe : ");
                    sb.append("离线定位成功，离线定位结果也是有效的");
                } else if (location.getLocType() == BDLocation.TypeServerError) {
                    sb.append("\ndescribe : ");
                    sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                    sb.append("\ndescribe : ");
                    sb.append("网络不同导致定位失败，请检查网络是否通畅");
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                    sb.append("\ndescribe : ");
                    sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
                }
    sb.append("\nlocationdescribe : ");
                    sb.append(location.getLocationDescribe());// 位置语义化信息
                    List<Poi> list = location.getPoiList();// POI数据
                    if (list != null) {
                        sb.append("\npoilist size = : ");
                        sb.append(list.size());
                        for (Poi p : list) {
                            sb.append("\npoi= : ");
                            sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                        }
                    }
                    
        		
        		  Toast.makeText(GetLocationActivity.this, "nav to " + location.getAddrStr(), Toast.LENGTH_SHORT).show();
//        		LatLng latng1=new LatLng(location.getLongitude(),location.getLatitude());
        		LatLng latng1=new LatLng(location.getLatitude(),location.getLongitude());
        		MapStatusUpdate  msu=MapStatusUpdateFactory.newLatLng(latng1);
                   MapStatusUpdate m = MapStatusUpdateFactory.zoomTo(16f);
                   mBaiduMAP.setMapStatus(m);
       			mBaiduMAP.animateMapStatus(msu);
       			
        		isFirstLocate=false;
        	}
        	
        	
         	// 设置定位数据
        	MyLocationData data=new MyLocationData.Builder()//
        			.accuracy(location.getRadius())//
        			.latitude(location.getLatitude())//
        			.longitude(location.getLongitude()).build();
        	mBaiduMAP.setMyLocationData(data);
        	
        	
        	
    	 }
       	 
        	 if(Build.VERSION.SDK_INT>=23){
        
            if (location.getLocType() == BDLocation.TypeGpsLocation
                    || location.getLocType() == BDLocation.TypeNetWorkLocation) {
                navigateTo(location);
            }
        }
        }

    }
	
	
	



	private void initView() {
		mPoiSearch = PoiSearch.newInstance();
		lv_searchresult=(ListView) findViewById(R.id.lv_searchresult);
		tv_detail_cacle=(TextView) findViewById(R.id.tv_detail_cacle);
		et_detail_search=(EditText) findViewById(R.id.et_detail_search);
		
		mMapView = (MapView) findViewById(R.id.baidu_map);
		
		mBaiduMAP = mMapView.getMap();
		mBaiduMAP.setMyLocationEnabled(true);
		mMapView.showZoomControls(false);
		mMapView.showScaleControl(false);
		
		
		// 第三步，设置POI检索监听者；
		OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener() {

			public void onGetPoiResult(PoiResult result) {
//				showMap();
				// 获取POI检索结果
				if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
					MyToastShowCenter.CenterToast(getBaseContext(), "没有找到结果！");
					return;
				}

				LatLng latLng = null;
				OverlayOptions options = null;
				Marker marker = null;

				Log.e("-----------------------------", "infos="+result);
				infos = result.getAllPoi();
				List<String> descs=new ArrayList<String>();
				descs.clear();
				for (PoiInfo p : infos) {
					descs.add(p.name);
				}
				
				SearchBaiDuAdapter adapter;
				adapter=new SearchBaiDuAdapter(descs, getApplicationContext());
				lv_searchresult.setAdapter(adapter);

			}

			public void onGetPoiDetailResult(PoiDetailResult result) {

			}

			@Override
			public void onGetPoiIndoorResult(PoiIndoorResult arg0) {
				// TODO Auto-generated method stub

			}
		};

		mPoiSearch.setOnGetPoiSearchResultListener(poiListener);
		
		

	}

	private void setListener() {
		
		lv_searchresult.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				GetLocationActivity.this.Posi=position;
				mMapView.setVisibility(View.VISIBLE);
				lv_searchresult.setVisibility(View.GONE);
				
				LatLng la=new LatLng(infos.get(position).location.latitude, infos.get(position).location.longitude);
				
				MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(la);

				mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.icon_mylocation);
				// ����MarkerOption�������ڵ�ͼ�����Marker
				OverlayOptions option = new MarkerOptions().position(la).icon(mCurrentMarker);
				// �ڵ�ͼ�����Marker������ʾ
				mBaiduMAP.addOverlay(option);
				mBaiduMAP.animateMapStatus(msu);
				
			
				
				showPopWin(la,infos.get(position));
				 
				
				
				
			}
		});
		
		et_detail_search.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(s.length()>=0){
					
					mMapView.setVisibility(View.GONE);
					lv_searchresult.setVisibility(View.VISIBLE);
					
					String key=et_detail_search.getText().toString();
					
					PoiCitySearchOption option=new PoiCitySearchOption();
					option.city("深圳市").keyword(key).pageNum(0).pageCapacity(30);
					mPoiSearch.searchInCity(option);
					
					
//					PoiDetailSearchOption option=new PoiDetailSearchOption();
//					option.poiUid(key);
//					mPoiSearch.searchPoiDetail(option);
					
//					PoiBoundSearchOption option =new PoiBoundSearchOption();
//					  LatLng southwest = new LatLng(3.86 - 0.01, 53.55-0.012);// 西南  
//				        LatLng northeast = new LatLng(73.66 + 0.01, 135.05 + 0.012);// 东北  
////				        LatLng southwest = new LatLng( 53.55-0.012,3.86 - 0.01);// 西南  
////				        LatLng northeast = new LatLng(135.05 + 0.012,73.66 + 0.01);// 东北  
//				        LatLngBounds bounds = new LatLngBounds.Builder().include(southwest)  
//				                .include(northeast).build();// 得到一个地理范围对象  
//					option.bound(bounds).keyword(key).pageCapacity(30).pageNum(3);
//					mPoiSearch.searchInBound(option);
					
				}
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
		tv_detail_cacle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				
				String text=et_detail_search.getText().toString();
//				mPoiSearch.
				if(text!=null || !text.equals("")){
					
					mMapView.setVisibility(View.GONE);
					lv_searchresult.setVisibility(View.VISIBLE);
					
					String key=et_detail_search.getText().toString();
					
					PoiCitySearchOption option=new PoiCitySearchOption();
					option.city("深圳市").keyword(key.trim()).pageNum(0).pageCapacity(30);
					mPoiSearch.searchInCity(option);
					
					
					
				
				}
			}
		});
		
		mBaiduMAP.setOnMapClickListener(new OnMapClickListener() {

			@Override
			public boolean onMapPoiClick(MapPoi poi) {
				Intent i = new Intent(GetLocationActivity.this, AgentPersonalActivity.class);
				double latitude = poi.getPosition().latitude;
				double longitude = poi.getPosition().longitude;
				Log.e("LatLng", "LatLng" + latitude + " " + longitude);
				i.putExtra("latitude", latitude);
				i.putExtra("longitude", longitude);
				i.putExtra("PS", poiString);
				setResult(LOCATION, i);
				finish();
				
				return true;
			}

			@Override
			public void onMapClick(LatLng arg0) {
				GetLocationActivity.this.position = arg0;

				Intent i = new Intent(GetLocationActivity.this, AgentPersonalActivity.class);
				double latitude = arg0.latitude;
				double longitude = arg0.longitude;
				Log.e("LatLng", "LatLng" + latitude + " " + longitude);
				i.putExtra("latitude", latitude);
				i.putExtra("longitude", longitude);
				i.putExtra("PS", poiString);
				setResult(LOCATION, i);
				finish();
			}
		});
	}




	protected void showPopWin(LatLng la, PoiInfo poiInfo) {
		//显示
		Marker marker = null;
		OverlayOptions options;
		
//		LayoutInflater layoutInflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);  
//		 View v=layoutInflater.inflate(R.layout.map_house_show2, null); 
//		 TextView tv=(TextView) v.findViewById(R.id.tv_map_house);
//		 
//		 tv.setText(poiInfo.name);
//		 
//		 mMarker=BitmapDescriptorFactory.fromView(tv);
//		 
//			options = new MarkerOptions().position(la).icon(mMarker).draggable(true);
//			// 添加marker
//			marker = (Marker) mBaiduMAP.addOverlay(options);
			
			InfoWindow infoWindow;
			// TextView tv=(TextView) findViewById(R.layout.mark_baidu_map);
			TextView tv = new TextView(this);
			tv.setBackgroundResource(R.drawable.map_house_overlay);
			tv.setBackgroundColor(android.graphics.Color.WHITE);
			tv.setTextColor(android.graphics.Color.BLACK);
			tv.setPadding(30, 20, 30, 20);
//			tv.setGravity(Gravity.CENTER);
			tv.setText(poiInfo.name + "");


			BitmapDescriptor bit = new BitmapDescriptorFactory().fromView(tv);

			OnInfoWindowClickListener infowindowListener = new OnInfoWindowClickListener() {

				@Override
				public void onInfoWindowClick() {
					mBaiduMAP.hideInfoWindow();
				}
			};
			infoWindow = new InfoWindow(bit, la, -47, infowindowListener);
			mBaiduMAP.showInfoWindow(infoWindow);
	}
	
	

	
}



