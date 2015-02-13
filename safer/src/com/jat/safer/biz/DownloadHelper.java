package com.jat.safer.biz;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Environment;

public class DownloadHelper {

	public static File getApkFile(String url){
		//下载读到sd，把文件返回回去
		int last = url.lastIndexOf("/");
		File file = new File(Environment.getExternalStorageDirectory(), url.substring(last+1));
		try {
			URL u = new URL(url);
			HttpURLConnection conn =  (HttpURLConnection) u.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			if(conn.getResponseCode()==200){
				InputStream is = conn.getInputStream();
				FileOutputStream os = new FileOutputStream(file);
				byte[] buffer = new byte[1024];
				int len;
				while((len = is.read(buffer))!=-1){
					os.write(buffer, 0, len);
				}
				os.flush();
				os.close();
				is.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}
}
