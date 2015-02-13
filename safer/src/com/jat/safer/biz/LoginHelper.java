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
 * ר�Ŵ����½�߼�
 * @author jat
 *
 */
public class LoginHelper {
	private static LoginHelper login;
	private Activity context;
	private final int UPDATE = 11;//����
	private final int CONNECTERROR = 12;//����ʧ��
	private final int SERVICEERROR = 13;//����������
	private final int DOWNLOADERROR = 14;//���س���
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
	 * ���ӷ�����
	 * ����һ���߳���������������
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
			//������
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);

			if(conn.getResponseCode() == 200 ){
				//���ӳɹ�
				System.out.println("���ӳɹ�");
				//�ù�������������
				bean = XmlParseUtils.getUpdateInfo(conn.getInputStream());
				System.out.println(bean.getVersion());
				if(bean!=null){
					if(bean.getVersion()==ViewHelper.getVersion(context)){
						//������£��Ѿ������°汾��ֱ�ӽ���������
						enterMain();
					}else{
						//���°汾��Ҫ���£�������ʾ

					}
				}
			}else{
				//����ʧ�ܣ�����������
				msg.what = SERVICEERROR;
				handler.sendMessage(msg);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//������������
			msg.what = CONNECTERROR;
			handler.sendMessage(msg);
		}
	}

	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATE://���и�����ʾ
				updateTipDialog();
				break;
			case CONNECTERROR:
				Toast.makeText(context, "���ӷ�����ʧ��", 1).show();
				enterMain();
			case SERVICEERROR:
				Toast.makeText(context, "����������", 1).show();
				enterMain();
			case DOWNLOADERROR:
				Toast.makeText(context, "����ʧ��", 1).show();
				enterMain();
			default:
				break;

			}
		};
	};

	/**
	 * ����������
	 */
	private void enterMain(){
		Intent intent = new Intent(context, MainActivity.class);
		context.startActivity(intent);
		context.finish();
	}
	protected void updateTipDialog() {
		AlertDialog.Builder builder = new Builder(context);
		builder.setTitle("������ʾ");
		builder.setMessage(bean.getDescription());
		builder.setPositiveButton("����", new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				updateApk();
			}
		});
		builder.setNegativeButton("ȡ��", new OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				enterMain();
			}
		});
		builder.create().show();
	}

	protected void updateApk() {
		//�����ص�ʱ����ʾһ��������������   �����˶���
		pd = new ProgressDialog(context);
		pd.setTitle("��������......");
		pd.show();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				File file = DownloadHelper.getApkFile(bean.getApkurl());
				pd.dismiss();
				if(file == null){
					//����ʧ��
					Message msg = new Message();
					msg.what = DOWNLOADERROR;
					handler.sendMessage(msg);
				}else{
					//���а�װ��Ҳ��ͨ��intent�����ò���������activity�����а�װ��
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
