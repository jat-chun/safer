package com.jat.safer.biz;

import java.util.jar.Pack200.Packer;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class ViewHelper {
	/**
	 * ��ȡ�汾��
	 */

	public static String getVersion(Context context){
		PackageManager pm = context.getPackageManager();//�õ���������
		try {
			//��װ�����еĹ����嵥�е�����
			PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
			return info.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
