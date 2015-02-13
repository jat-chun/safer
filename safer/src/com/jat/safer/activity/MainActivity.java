package com.jat.safer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.jat.safer.R;
import com.jat.safer.adapter.MainAdapter;

public class MainActivity extends BaseActivity {

	private MainAdapter mAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		GridView gv_main_gv = (GridView) findViewById(R.id.gv_main_gv);
		
		mAdapter = new MainAdapter(this);
		gv_main_gv.setAdapter(mAdapter);
		gv_main_gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long arg3) {
				switch (position) {
				case 0:
					Intent intent0 = new Intent(MainActivity.this,LostProtectActivity.class);
					MainActivity.this.startActivity(intent0);
					break;
				case 7:
					Intent intent7 = new Intent(MainActivity.this,AtoolsActivity.class);
					MainActivity.this.startActivity(intent7);
					break;
				case 8:
					Intent intent8 = new Intent(MainActivity.this,HightToolsActivity.class);
					MainActivity.this.startActivity(intent8);
					break;

				default:
					break;
				}
			}
		});
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		mAdapter.notifyDataSetChanged();//让listview自动刷新，其实就是让getview再次调用
		super.onResume();
	}

}
