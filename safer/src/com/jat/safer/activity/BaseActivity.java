package com.jat.safer.activity;

import android.app.Activity;
import android.view.View;

public class BaseActivity extends Activity {
	
	protected <A extends View> A getView(int id){
		return (A)findViewById(id);
	}

}
