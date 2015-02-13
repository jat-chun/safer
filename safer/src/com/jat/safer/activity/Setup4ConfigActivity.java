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
		//�жϷ��������Ƿ���
		boolean isprotected = sp.getBoolean("isprotected", false);
		if(isprotected){
			Editor editor = sp.edit();
			//�������Ѿ������
			editor.putBoolean("issetup", true);
			editor.commit();
			saferPreference.save(this, "issetup", true);
			finish();
		}else{
			AlertDialog.Builder builder = new Builder(this);
			builder.setTitle("ǿ�ҽ���");
			builder.setMessage("�ֻ���������ı������ֻ��İ�ȫ���빴ѡ��������");
			builder.setPositiveButton("����", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					cb_setup4_start_protect.setChecked(true);
					cb_setup4_start_protect.setText("���������Ѿ�����");
					Editor editor = sp.edit();
					editor.putBoolean("isprotected", true);
					//�������Ѿ������
					editor.putBoolean("issetup", true);
					editor.commit();
					//���֮��Ͱѵ�ǰ��activity�رյ�
					finish();
				}
			});
			builder.setNegativeButton("����", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					Editor editor = sp.edit();
					editor.putBoolean("isprotected", false);
					//�������Ѿ������
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
				cb_setup4_start_protect.setText("��������û�п���");
				Editor editor = sp.edit();
				editor.putBoolean("isprotected", false);
				editor.commit();
			}else{
				cb_setup4_start_protect.setChecked(true);
				cb_setup4_start_protect.setText("���������Ѿ�����");
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
