package com.jat.safer.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	
	public static String getData(String str){
		try {
			//����MessageDigest��MD5����
			MessageDigest digest =MessageDigest.getInstance("md5");
			//����һ���ֽ����ͣ�����MessageDigestת��� �ַ�
			byte[] data = digest.digest(str.getBytes());
			StringBuffer sb = new StringBuffer();
			for(int i = 0 ; i< data.length ; i++){
				//���ַ�ת��Ϊstring�ַ���
				String result = Integer.toHexString(data[i]&0xff);
				String temp = null;
				//������ֻ�� һ��λ�Ļ��Ͳ���
				if(result.length() == 1){
					temp = "0" +result;
				}else{
					temp = result;
				}
				//���ַ������ӵ�ĩβ
				sb.append(temp);
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
