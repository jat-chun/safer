package com.jat.safer.biz;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * ��sharepreference����
 * @author jat
 *
 */
public class SaferPreference {
	/**
	 * ��sharepreference�д�����
	 * @param context
	 * @param key
	 * @param value
	 * �������Ա����ַ�����Ҳ���Ա���Boolean
	 */
	public static void save(Context context,String key , Object value){
		//�õ�SaferPreference
		SharedPreferences sp = context.getSharedPreferences(Const.PFNAME, 0);
		//�ж�Ҫ�������ʱʲô����
		if(value instanceof String){
			sp.edit().putString(key, (String)value).commit();
		}else if(value instanceof Boolean){
			sp.edit().putBoolean(key, (Boolean)value).commit();
		}
	}


	public static String getString(Context context,String key){
		//�õ�SaferPreference
		SharedPreferences sp = context.getSharedPreferences(Const.PFNAME, 0);
		String value = sp.getString(key, null);
		return value;
	}
	public static Boolean getBoolean(Context context,String key){
		//�õ�SaferPreference
		SharedPreferences sp = context.getSharedPreferences(Const.PFNAME, 0);
		Boolean value = sp.getBoolean(key, false);
		return value;
	}
}
