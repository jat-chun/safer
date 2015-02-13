package com.jat.safer.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.jat.safer.R;
import com.jat.safer.biz.Const;
import com.jat.safer.biz.SaferPreference;
import com.jat.safer.utils.MD5;

public class LostProtectActivity extends BaseActivity {
	
	private LostProtectActivity TAG = LostProtectActivity.this;
	private SaferPreference sp;
//	private SharedPreferences preferences;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
//		preferences = getSharedPreferences("config", Context.MODE_PRIVATE);
		//�ж�֮ǰ��û����������
		//����������ȡ����˵���Ѿ������������� key
		if(TextUtils.isEmpty(SaferPreference.getString(this, Const.SAFERPASSWORD))){
			//����Ϊ�գ���һ�ν���
			showFirstDialog();
		}else {
			//�Ѿ�������������
			showNomalDialog();
		}
	}

	/**
	 * �����Ѿ����õ�����
	 */
	private void showNomalDialog() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new Builder(this);
		View view = LayoutInflater.from(this).inflate(R.layout.login_dialog, null);
		final EditText et_password = (EditText) view.findViewById(R.id.et_password);

		builder.setView(view);
		final Dialog dialog = builder.create();



		view.findViewById(R.id.bt_login_sure).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				String password = et_password.getText().toString();

				String md5_pwd = MD5.getData(password);
				if(md5_pwd.equals(SaferPreference.getString(TAG, Const.SAFERPASSWORD))){
					dialog.dismiss();
					loadGuideUI();
				}else{
					Toast.makeText(TAG, "�����������������", 1).show();
					et_password.setText("");;
				}
			}

		});

		view.findViewById(R.id.bt_login_quit).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				TAG.finish();
			}
		});
		dialog.show();
	}
	/**
	 * ��������
	 */
	private void showFirstDialog() {
		AlertDialog.Builder builder = new Builder(this);
		View view = LayoutInflater.from(this).inflate(R.layout.first_dialog, null);
		
		final EditText et_first_one = (EditText) view.findViewById(R.id.et_first_one);
		final EditText et_second_one = (EditText) view.findViewById(R.id.et_second_one);
		
		
		builder.setView(view);
		
		
		final Dialog dialog = builder.create();
		//����ť����¼�
		view.findViewById(R.id.bt_save).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				String first_password = et_first_one.getText().toString();
				System.out.println(first_password);
				String second_password = et_second_one.getText().toString();

				if(!TextUtils.isEmpty(first_password)&&first_password.equals(second_password)){
					//����һ��
					//���м���
					String md5_pwd = MD5.getData(first_password);
					SaferPreference.save(TAG, Const.SAFERPASSWORD, md5_pwd);
					//�����ֻ��������򵼽���
					loadGuideUI();
					dialog.dismiss();
				}else{
					Toast.makeText(TAG, "���벻һ�£�����������", 1).show();
				}
			}

			
		});
		view.findViewById(R.id.bt_quit).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				TAG.finish();
			}
		});
		dialog.show();
	}
	//�����ֻ��򵼽��棬����򵼽����Ѿ�������ɣ�ֱ�ӽ����ֻ���������
	private void loadGuideUI() {
		//�Ƿ����������
		boolean issetup = sp.getBoolean(TAG ,Const.ISSETUP);
//		boolean setup = preferences.getBoolean("issetup", false);
		if(issetup){
			//ֱ�ӽ����ֻ��������ý���
			Intent intent = new Intent(TAG, LostProtectedSettingActivity.class);
			startActivity(intent);
			finish();
		}else{
			//�����ֻ������򵼽���
			Intent intent = new Intent(TAG, Setup1ConfigActivity.class);
			startActivity(intent);
			finish();
		}
	}
	
}
