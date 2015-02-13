package com.jat.safer.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;

public class BootCompleteReceiver extends BroadcastReceiver {

	private SharedPreferences sp;
	private TelephonyManager manager;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		SmsManager smsManager = SmsManager.getDefault();
		sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		boolean isprotect = sp.getBoolean("isprotect", false);
		if(isprotect){
			String sim_serial = manager.getSimSerialNumber();
			String old_sim_serial = sp.getString("sim_serial", "");
			if(!sim_serial.equals(old_sim_serial)){
				String safe_number = sp.getString("safe_number", "");
				smsManager.sendTextMessage(safe_number, null, "ni de shou ji ke neng bei dao!!!", null, null);
			}
		}
	}

}
