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


	//����ֻ��������е���ϵ��
	public List<ContactInfo> getContact(){
		List<ContactInfo> contactInfos = new ArrayList<ContactInfo>();
		ContentResolver cr = context.getContentResolver();
		//��ѯraw_contacts����õ���ϵ�˵�id
		Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
		//projection�ڶ�����������˼�ǣ� ͶӰʲô����
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
			
			//��ѯdata��
			uri = Uri.parse("content://com.android.contacts/raw_contacts/"+ _id +"/data");
			Cursor c = cr.query(uri, new String[]{"data1","mimetype"}, null, null, null);
//			String[] names = c.getColumnNames();
//			for(String n: names){
//				Log.i("i", n);
//			}
			//����绰����Ĳ���
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
