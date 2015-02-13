package com.jat.safer.receiver;

import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.provider.MediaStore.Audio.Media;
import android.sax.StartElementListener;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import com.jat.safer.R;
import com.jat.safer.engine.GPSInfoService;

public class SmsReceiver extends BroadcastReceiver {

	private SharedPreferences sp;
	private DevicePolicyManager devicePolicyManager;

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.i("i", "已经拦截到短信");
		sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		devicePolicyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
		boolean isprotected = sp.getBoolean("isprotected", false);
		ComponentName componentName = new ComponentName(context, MyAdmin.class);
		if(isprotected){
			Object[] pdus = (Object[]) intent.getExtras().get("pdus");
			for(Object pdu : pdus){
				SmsMessage smsMessage = SmsMessage.createFromPdu((byte[])pdu);
				String body = smsMessage.getDisplayMessageBody();

				String safe_number = sp.getString("safe_number", "");

				if("#location*#".equals(body)){
					//获取手机的位置
					//吧位置发送给安全号码
					//中断广播
					GPSInfoService service = GPSInfoService.getInstance(context);
					service.registenerLocationListener();
					String last_location = sp.getString("last_number", "");
					SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage(safe_number, null,"shou ji de wei zhi: "+ last_location, null, null);

					abortBroadcast();
				}else if("#*lockscreen*#".equals(body)){
					devicePolicyManager.lockNow();
					devicePolicyManager.resetPassword("5161", 0);
				}else if("#*alarm*#".equals(body)){
					MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.alarm);
					mediaPlayer.setVolume(1.0f, 1.0f);
					mediaPlayer.start();
					abortBroadcast();
				}else if("#*delete*#".equals(body)){
					devicePolicyManager.wipeData(0);
					abortBroadcast();
				}
			}

		}else{
			Intent i = new Intent();
			i.setAction(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
			i.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
			context.startActivity(i);
		}
	}

}
