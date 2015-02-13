package com.jat.safer.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jat.safer.R;
import com.jat.safer.bean.ContactInfo;

public class ContactInfoAdapter extends BaseAdapter {

	private Context context;
	private List<ContactInfo> contacts;
	private LayoutInflater mInflater;
	public ContactInfoAdapter(Context context,
			List<ContactInfo> contacts) {
		this.context = context;
		this.contacts = contacts;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return contacts.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return contacts.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View view = mInflater.inflate(R.layout.contact_item, null);
		TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
		TextView tv_number = (TextView) view.findViewById(R.id.tv_number);
		ContactInfo info = contacts.get(position);
		tv_name.setText(info.getName());
		tv_number.setText(info.getNumber());
		return view;
	}

}
