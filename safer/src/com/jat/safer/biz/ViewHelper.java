package com.jat.safer.biz;

import java.util.jar.Pack200.Packer;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class ViewHelper {
	/**
	 * 获取版本号
	 */

	public static String getVersion(Context context){
		PackageManager pm = context.getPackageManager();//拿到包管理器
		try {
			//封装看所有的功能清单中的数据
			PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
			return info.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
