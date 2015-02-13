package com.jat.safer.biz;



import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.sax.StartElementListener;
import android.widget.Toast;

import com.jat.safer.R;
import com.jat.safer.activity.MainActivity;
import com.jat.safer.bean.UpdateBean;
import com.jat.safer.utils.XmlParseUtils;

/**
 * 专门处理登陆逻辑
 * @author jat
 *
 */
public class LoginHelper {
	private static LoginHelper login;
	private Activity context;
	private final int UPDATE = 11;//更新
	private final int CONNECTERROR = 12;//连接失败
	private final int SERVICEERROR = 13;//服务器出错
	private final int DOWNLOADERROR = 14;//下载出错
	private ProgressDialog pd;
	private UpdateBean bean;
	private LoginHelper(Activity context){
		this.context = context;
	}
	public static LoginHelper getInstance(Activity context){
		if(login == null){
			login = new LoginHelper(context);
		}
		return login;
	}
	/**
	 * 连接服务器
	 * 新起一个线程来进行网络连接
	 */
	public void loginConnect(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				connect();
			}
		}).start();
	}
	protected void connect() {
		Message msg= new Message();
		try {
			String apkUrl = context.getResources().getString(R.string.apkurl);
			System.out.println(apkUrl);
			URL url = new URL(apkUrl);
			//打开连接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);

			if(conn.getResponseCode() == 200 ){
				//连接成功
				System.out.println("连接成功");
				//用工具来解析处理
				bean = XmlParseUtils.getUpdateInfo(conn.getInputStream());
				System.out.println(bean.getVersion());
				if(bean!=null){
					if(bean.getVersion()==ViewHelper.getVersion(context)){
						//无需更新，已经是最新版本，直接进入主界面
						enterMain();
					}else{
						//有新版本需要更新，弹开提示

					}
				}
			}else{
				//连接失败，服务器出错
				msg.what = SERVICEERROR;
				handler.sendMessage(msg);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//服务器连不上
			msg.what = CONNECTERROR;
			handler.sendMessage(msg);
		}
	}

	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATE://进行更新提示
				updateTipDialog();
				break;
			case CONNECTERROR:
				Toast.makeText(context, "连接服务器失败", 1).show();
				enterMain();
			case SERVICEERROR:
				Toast.makeText(context, "服务器出错", 1).show();
				enterMain();
			case DOWNLOADERROR:
				Toast.makeText(context, "下载失败", 1).show();
				enterMain();
			default:
				break;

			}
		};
	};

	/**
	 * 进入主界面
	 */
	private void enterMain(){
		Intent intent = new Intent(context, MainActivity.class);
		context.startActivity(intent);
		context.finish();
	}
	protected void updateTipDialog() {
		AlertDialog.Builder builder = new Builder(context);
		builder.setTitle("升级提示");
		builder.setMessage(bean.getDescription());
		builder.setPositiveButton("升级", new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				updateApk();
			}
		});
		builder.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				enterMain();
			}
		});
		builder.create().show();
	}

	protected void updateApk() {
		//在下载的时候显示一个进度条，动画   下载了多少
		pd = new ProgressDialog(context);
		pd.setTitle("正在下载......");
		pd.show();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				File file = DownloadHelper.getApkFile(bean.getApkurl());
				pd.dismiss();
				if(file == null){
					//下载失败
					Message msg = new Message();
					msg.what = DOWNLOADERROR;
					handler.sendMessage(msg);
				}else{
					//进行安装，也是通过intent来设置参数和启动activity来进行安装的
					Intent intent = new Intent();
					intent.setAction(null);
					intent.addCategory(null);
					intent.setDataAndType(null, null);
					context.startActivity(intent);
					context.finish();
					
				}
			}
		}).start();

	}
	public void destroy(){
		login = null;
	}

}
