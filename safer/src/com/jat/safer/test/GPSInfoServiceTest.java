package com.jat.safer.test;

import com.jat.safer.engine.GPSInfoService;

import android.test.AndroidTestCase;

public class GPSInfoServiceTest extends AndroidTestCase{
	
	public void testRegisterLocarionChangeListener() throws Exception{
		GPSInfoService service = GPSInfoService.getInstance(getContext());
		service.registenerLocationListener();
	}
	
}
