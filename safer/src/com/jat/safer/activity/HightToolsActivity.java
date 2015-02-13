package com.jat.safer.activity;

import com.jat.safer.R;
import com.jat.safer.biz.Const;
import com.jat.safer.biz.SaferPreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.TextView;

public class HightToolsActivity extends BaseActivity {

	private HightToolsActivity TAG = HightToolsActivity.this;
	private SharedPreferences sp;
	private TextView tv_is_update;
	private TextView tv_is_ipcall;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hightools);

		sp = getSharedPreferences("config", Context.MODE_PRIVATE);
		tv_is_update = (TextView) findViewById(R.id.tv_is_update);
		tv_is_ipcall = (TextView) findViewById(R.id.tv_is_ipcall);
		final CheckBox cb_is_update = getView(R.id.cb_is_update);
		final CheckBox cb_is_ipcall = getView(R.id.cb_is_ipcall);
		if(SaferPreference.getBoolean(TAG, Const.ISUPDATE)){
			cb_is_update.setChecked(true);
			tv_is_update.setText("�ѿ����Զ�����");
		}else{
			cb_is_update.setChecked(false);
			tv_is_update.setText("��û�����Զ�����");
		}
		if(sp.getBoolean("is_ipcall", false)){
			cb_is_ipcall.setChecked(true);
			tv_is_ipcall.setText("�ѿ���ip����");
		}else{
			cb_is_ipcall.setChecked(false);
			tv_is_ipcall.setText("��û����ip����");
		}
		cb_is_update.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				//�ж��Ƿ�ѡ�У������������
				if(cb_is_update.isChecked()){
					SaferPreference.save(TAG, Const.ISUPDATE, true);
					tv_is_update.setText("�ѿ����Զ�����");
				}else{
					SaferPreference.save(TAG, Const.ISUPDATE, false);
					tv_is_update.setText("��û�����Զ�����");
				}

			}
		});
		cb_is_ipcall.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				//�ж��Ƿ�ѡ�У������������
				if(cb_is_update.isChecked()){
					Editor editor = sp.edit();
					editor.putBoolean("is_ipcall", true);
					editor.commit();
					tv_is_ipcall.setText("�ѿ���ip����");
				}else{
					Editor editor = sp.edit();
					editor.putBoolean("is_ipcall", false);
					editor.commit();
					tv_is_ipcall.setText("��û����ip����");
				}
			}
		});


	}
}
