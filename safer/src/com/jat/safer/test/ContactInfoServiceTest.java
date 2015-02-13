package com.jat.safer.test;

import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.jat.safer.bean.ContactInfo;
import com.jat.safer.engine.ContactInfoService;

public class ContactInfoServiceTest extends AndroidTestCase {

	public void testGetContacts() throws Exception{
		ContactInfoService service = new ContactInfoService(getContext());
		 List<ContactInfo> contactInfos = service.getContact();
		 for(ContactInfo info: contactInfos){
			 Log.i("i", info.toString());
		 }
	}
}
