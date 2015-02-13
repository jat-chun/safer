package com.jat.safer.biz;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 对sharepreference操作
 * @author jat
 *
 */
public class SaferPreference {
	/**
	 * 往sharepreference中存数据
	 * @param context
	 * @param key
	 * @param value
	 * 不仅可以保存字符串，也可以保存Boolean
	 */
	public static void save(Context context,String key , Object value){
		//拿到SaferPreference
		SharedPreferences sp = context.getSharedPreferences(Const.PFNAME, 0);
		//判断要存的数据时什么类型
		if(value instanceof String){
			sp.edit().putString(key, (String)value).commit();
		}else if(value instanceof Boolean){
			sp.edit().putBoolean(key, (Boolean)value).commit();
		}
	}


	public static String getString(Context context,String key){
		//拿到SaferPreference
		SharedPreferences sp = context.getSharedPreferences(Const.PFNAME, 0);
		String value = sp.getString(key, null);
		return value;
	}
	public static Boolean getBoolean(Context context,String key){
		//拿到SaferPreference
		SharedPreferences sp = context.getSharedPreferences(Const.PFNAME, 0);
		Boolean value = sp.getBoolean(key, false);
		return value;
	}
}
