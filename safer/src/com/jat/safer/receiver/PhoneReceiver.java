package com.jat.safer.receiver;

import com.jat.safer.activity.LostProtectActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

public class PhoneReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.i("i", "已经拦截到外拨电话");
		//清除数据
		//激活一个activity
		String number = getResultData();
		if("".equals(number)){
			//receiver是不存在于任务栈里面,在里面启动activity必须要指定flags FLAG_ACTIVITY_NEW_TASK
			Intent i = new Intent(context, LostProtectActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(i);
		}else{
			if("110".equals(number)){
				setResultData(null);
			}else{
				SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
				String ip_number = sp.getString("ip_number", null);
				setResultData(ip_number + number);
			}
		}
		
	}

}
