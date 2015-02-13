package com.jat.safer.activity;

import com.jat.safer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class AtoolsActivity extends Activity implements OnClickListener {

	private TextView tv_atools_add_ipcall;
	private TextView tv_atools_address_query;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.atools);
		tv_atools_add_ipcall = (TextView) findViewById(R.id.tv_atools_add_ipcall);
		tv_atools_add_ipcall.setOnClickListener(this);
		tv_atools_address_query = (TextView) findViewById(R.id.tv_atools_address_query);
		tv_atools_address_query.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		int id = view.getId();
		Intent intent = null;
		
		switch (id) {
		case R.id.tv_atools_add_ipcall:
			intent = new Intent(this,AddIpCallActivity.class);
			startActivity(intent);
			break;
		case R.id.tv_atools_address_query:
			intent = new Intent();
			startActivity(intent);
			break;

		default :
			break;
		}
		
	}

}
