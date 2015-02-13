package com.jat.safer.activity;

import com.jat.safer.R;
import com.jat.safer.biz.SaferPreference;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

public class Setup4ConfigActivity extends Activity implements OnClickListener{
	
	private SharedPreferences sp;
	private CheckBox cb_setup4_start_protect;
	private SaferPreference saferPreference;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.setup4config);
		
		saferPreference = new SaferPreference();
		
		cb_setup4_start_protect = (CheckBox) findViewById(R.id.cb_setup4_start_protect);
		
		sp = getSharedPreferences("config", Context.MODE_PRIVATE);
		cb_setup4_start_protect.setOnClickListener(this);;
	}
	
	public void pre(View v){
		Intent intent = new Intent(this, Setup3ConfigActivity.class);
		startActivity(intent);
		finish();
	}
	
	public void next(View v){
		//判断防盗保护是否开启
		boolean isprotected = sp.getBoolean("isprotected", false);
		if(isprotected){
			Editor editor = sp.edit();
			//设置向导已经完成了
			editor.putBoolean("issetup", true);
			editor.commit();
			saferPreference.save(this, "issetup", true);
			finish();
		}else{
			AlertDialog.Builder builder = new Builder(this);
			builder.setTitle("强烈建议");
			builder.setMessage("手机防盗极大的保护了手机的安全，请勾选开启防盗");
			builder.setPositiveButton("开启", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					cb_setup4_start_protect.setChecked(true);
					cb_setup4_start_protect.setText("防盗保护已经开启");
					Editor editor = sp.edit();
					editor.putBoolean("isprotected", true);
					//设置向导已经完成了
					editor.putBoolean("issetup", true);
					editor.commit();
					//完成之后就把当前的activity关闭掉
					finish();
				}
			});
			builder.setNegativeButton("放弃", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					Editor editor = sp.edit();
					editor.putBoolean("isprotected", false);
					//设置向导已经完成了
					editor.putBoolean("isset", true);
					editor.commit();
					finish();
				}
			});
			saferPreference.save(this, "issetup", true);
			AlertDialog dialog = builder.create();
			dialog.show();
		}
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		switch (id) {
		case R.id.cb_setup4_start_protect:
			boolean isprotected = sp.getBoolean("isprotected", false);
			if(isprotected){
				cb_setup4_start_protect.setChecked(false);
				cb_setup4_start_protect.setText("防盗保护没有开启");
				Editor editor = sp.edit();
				editor.putBoolean("isprotected", false);
				editor.commit();
			}else{
				cb_setup4_start_protect.setChecked(true);
				cb_setup4_start_protect.setText("防盗保护已经开启");
				Editor editor = sp.edit();
				editor.putBoolean("isprotected", true);
				editor.commit();
			}
			break;

		default:
			break;
		}
		
	}
}
