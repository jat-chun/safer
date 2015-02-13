package com.jat.safer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jat.safer.R;

public class Setup1ConfigActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setup1config);
	}
	
	public void next(View v){
		Intent intent = new Intent(Setup1ConfigActivity.this, Setup2ConfigActivity.class);
		startActivity(intent);
		//从当前的任务里面移除
		finish();
	}
	
}
