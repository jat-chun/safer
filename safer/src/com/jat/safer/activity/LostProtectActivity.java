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
		//判断之前有没有设置密码
		//如果密码可以取到就说明已经设置了密码了 key
		if(TextUtils.isEmpty(SaferPreference.getString(this, Const.SAFERPASSWORD))){
			//密码为空，第一次进来
			showFirstDialog();
		}else {
			//已经设置了密码了
			showNomalDialog();
		}
	}

	/**
	 * 输入已经设置的密码
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
					Toast.makeText(TAG, "密码错误，请重新输入", 1).show();
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
	 * 设置密码
	 */
	private void showFirstDialog() {
		AlertDialog.Builder builder = new Builder(this);
		View view = LayoutInflater.from(this).inflate(R.layout.first_dialog, null);
		
		final EditText et_first_one = (EditText) view.findViewById(R.id.et_first_one);
		final EditText et_second_one = (EditText) view.findViewById(R.id.et_second_one);
		
		
		builder.setView(view);
		
		
		final Dialog dialog = builder.create();
		//处理按钮点击事件
		view.findViewById(R.id.bt_save).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				String first_password = et_first_one.getText().toString();
				System.out.println(first_password);
				String second_password = et_second_one.getText().toString();

				if(!TextUtils.isEmpty(first_password)&&first_password.equals(second_password)){
					//密码一致
					//进行加密
					String md5_pwd = MD5.getData(first_password);
					SaferPreference.save(TAG, Const.SAFERPASSWORD, md5_pwd);
					//进入手机防盗的向导界面
					loadGuideUI();
					dialog.dismiss();
				}else{
					Toast.makeText(TAG, "密码不一致，请重新输入", 1).show();
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
	//进入手机向导界面，如果向导界面已经设置完成，直接进入手机防盗界面
	private void loadGuideUI() {
		//是否完成设置向导
		boolean issetup = sp.getBoolean(TAG ,Const.ISSETUP);
//		boolean setup = preferences.getBoolean("issetup", false);
		if(issetup){
			//直接进入手机防盗设置界面
			Intent intent = new Intent(TAG, LostProtectedSettingActivity.class);
			startActivity(intent);
			finish();
		}else{
			//进入手机防盗向导界面
			Intent intent = new Intent(TAG, Setup1ConfigActivity.class);
			startActivity(intent);
			finish();
		}
	}
	
}
