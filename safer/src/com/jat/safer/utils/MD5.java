package com.jat.safer.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	
	public static String getData(String str){
		try {
			//利用MessageDigest的MD5技术
			MessageDigest digest =MessageDigest.getInstance("md5");
			//定义一个字节类型，接收MessageDigest转义的 字符
			byte[] data = digest.digest(str.getBytes());
			StringBuffer sb = new StringBuffer();
			for(int i = 0 ; i< data.length ; i++){
				//把字符转化为string字符串
				String result = Integer.toHexString(data[i]&0xff);
				String temp = null;
				//如果结果只有 一个位的话就补零
				if(result.length() == 1){
					temp = "0" +result;
				}else{
					temp = result;
				}
				//把字符串连接到末尾
				sb.append(temp);
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
