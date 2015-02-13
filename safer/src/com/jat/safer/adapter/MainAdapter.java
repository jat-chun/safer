package com.jat.safer.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.jat.safer.R;
import com.jat.safer.biz.SaferPreference;

public class MainAdapter extends BaseAdapter implements ListAdapter {

	private SharedPreferences sp;
	private Context context;
	private LayoutInflater inflater;
	public MainAdapter(Context context){
		this.context = context;
		inflater = LayoutInflater.from(context);
		sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
	}
	
	
	private String[] nameArray = new String[]{"手机防盗","通讯卫士","软件管理","任务管理",
			"流量管理","手机杀毒","系统优化","高级工具","设置中心"};
	private int[] imageArray = new int[]{R.drawable.widget01,R.drawable.widget02,R.drawable.widget03
			,R.drawable.widget04,R.drawable.widget05,R.drawable.widget06,
			R.drawable.widget07,R.drawable.widget08,R.drawable.widget09};
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return nameArray.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View arg1, ViewGroup arg2) {
		//代码创建layout，将空间添加进去,也可以另外创建layout布局文件，再把控件放置进去
//		LinearLayout view = new LinearLayout(context);
//		view.setGravity(Gravity.CENTER);
//		view.setPadding(0, 10, 0, 10);
//		view.setOrientation(LinearLayout.VERTICAL);
//		
//		ImageView iv = new ImageView(context);
//		iv.setImageResource(imageArray[position]);
//		iv.setLayoutParams(new LinearLayout.LayoutParams(50, 50));
//		view.addView(iv);
//		
//		
//		TextView tv = new TextView(context);
//		tv.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//		tv.setText(nameArray[position]);
//		tv.setTextColor(0xffc0c0c0);
//		tv.setTextSize(16);
//		view.addView(tv);
		
		
		View view = inflater.inflate(R.layout.main_item, null);
		ImageView iv_main = (ImageView) view.findViewById(R.id.iv_main);
		TextView tv_main = (TextView) view.findViewById(R.id.tv_main);
		iv_main.setImageResource(imageArray[position]);
		
		if(position == 0){
			String name = sp.getString("name", nameArray[position]);
			if("".equals(name)){
				name = nameArray[position];
			}
			tv_main.setText(name);
		}else{
			tv_main.setText(nameArray[position]);
		}
		
		return view;
	}

}
