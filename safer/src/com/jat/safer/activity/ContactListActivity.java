package com.jat.safer.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.jat.safer.R;
import com.jat.safer.adapter.ContactInfoAdapter;
import com.jat.safer.bean.ContactInfo;
import com.jat.safer.engine.ContactInfoService;


public class ContactListActivity extends Activity {
	
	private ListView lv_contact;
	
	private ContactInfoService infoService;
	
	private ContactInfoAdapter contactAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_list);
		
		lv_contact = (ListView) findViewById(R.id.lv_contact);
		
		infoService = new ContactInfoService(this);
		List<ContactInfo> contacts = infoService.getContact();
		
		contactAdapter = new ContactInfoAdapter(this, contacts);
		lv_contact.setAdapter(contactAdapter);
		
		lv_contact.setOnItemClickListener(new MyOnItemClickListener());
		
	}
	
	private final class MyOnItemClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			ContactInfo info =(ContactInfo) contactAdapter.getItem(position);
			String number = info.getNumber();
			Intent data = new Intent();
			data.putExtra("number", number);
			setResult(200, data);//往上一个activity返回数据
			finish();
		}
		
	}
}
