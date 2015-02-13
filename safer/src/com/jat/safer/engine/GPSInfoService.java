package com.jat.safer.engine;

import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

//ֻ����һ��ʵ��������ģʽ
public class GPSInfoService {
	
	private static GPSInfoService mInstance;
	private LocationManager locationManager;//��λ����
	private SharedPreferences sp;
	
	private GPSInfoService(Context context) {
		locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
	}
	
	public static GPSInfoService getInstance(Context context){
		if(mInstance == null){
			mInstance = new GPSInfoService(context);
		}
		return mInstance;
	}
	
	public void registenerLocationListener(){
		List<String> providers = locationManager.getAllProviders();
		for(String provider : providers){
			System.out.println(provider);
			Log.i("i", provider);
		}
		
		
		//��ѯ����
		Criteria criteria = new Criteria();
		//��λ�� ��ȷ��
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		//������Ϣ�Ƿ��ע
		criteria.setAltitudeRequired(true);
		//����Χ�������Ƿ���й���
		criteria.setBearingRequired(false);
		//�Ƿ�֧���շѵĲ�ѯ
		criteria.setCostAllowed(true);
		//�ĵ紦��
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		//�Ƿ��ע�ٶ�
		criteria.setSpeedRequired(false);
		//�õ���õĶ�λ��ʽ
		String provider = locationManager.getBestProvider(criteria, true);
		
		//ע�����
		locationManager.requestLocationUpdates(provider, 60000, 0, getliListener());
		
	}
	
	public void UnRegistenerLocationListener(){
		locationManager.removeUpdates(getliListener());
	}
	
	
	private MyLocationListener listener;
	
	//�õ���λ�ļ�����
	private MyLocationListener getliListener(){
		if(listener == null){
			listener = new MyLocationListener();
		}
		return listener;
	}
	
	//����ϸ�����λ��
	public String getLastLocation(){
		return sp.getString("last_location", "");
	}
	
	private final class MyLocationListener implements LocationListener{

		//λ�õĸı�
		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			double latitude = location.getLatitude();//γ��
			double longitude = location.getLongitude();//����
			
			String last_location = "jingdu: " + longitude + "weidu: " + latitude;
			Editor editor = sp.edit();
			editor.putString("last_location", last_location);
			editor.commit();
		}

		//ĳ�����ñ���
		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		//ĳ�����ñ��ر�
		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		//gps������һ��û���ҵ�
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
