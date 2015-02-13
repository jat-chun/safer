package com.jat.safer.engine;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.jat.safer.bean.ContactInfo;

public class ContactInfoService {

	private Context context;
	public ContactInfoService(Context context) {
		this.context = context;
	}


	//获得手机里面所有的联系人
	public List<ContactInfo> getContact(){
		List<ContactInfo> contactInfos = new ArrayList<ContactInfo>();
		ContentResolver cr = context.getContentResolver();
		//查询raw_contacts表里得到联系人的id
		Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
		//projection第二个参数的意思是： 投影什么对象
		Cursor cursor = cr.query(uri, new String[]{"_id","display_name"}, null, null, null);
//		String[] names = cursor.getColumnNames();
//		for(String name: names){
//			Log.i("i", name);
//		}
		while(cursor.moveToNext()){
			ContactInfo info = new ContactInfo();
			String _id = cursor.getString(cursor.getColumnIndex("_id"));
			String name = cursor.getString(cursor.getColumnIndex("display_name"));
			info.setName(name);
			
			//查询data表
			uri = Uri.parse("content://com.android.contacts/raw_contacts/"+ _id +"/data");
			Cursor c = cr.query(uri, new String[]{"data1","mimetype"}, null, null, null);
//			String[] names = c.getColumnNames();
//			for(String n: names){
//				Log.i("i", n);
//			}
			//处理电话号码的操作
			while(c.moveToNext()){
				String data1 = c.getString(c.getColumnIndex("data1"));
				String mimetype = c.getString(c.getColumnIndex("mimetype"));
				if("vnd.android.cursor.item/phone_v2".equals(mimetype)){
					info.setNumber(data1);
					contactInfos.add(info);
				}
			}
			c.close();
		}
		cursor.close();
		return contactInfos;

	}
}
