package com.jat.safer.utils;

import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

import com.jat.safer.bean.UpdateBean;

public class XmlParseUtils {

	/**
	 * 解析XML
	 * @param inputStream
	 * @return
	 */
	public static UpdateBean getUpdateInfo(InputStream inputStream) {
		//拿到解析器，初始化，拿到事件类型，如果是制定内容就放进bean里面
		XmlPullParser parser = Xml.newPullParser();
		UpdateBean bean = new UpdateBean();
		try {
			parser.setInput(inputStream, "UTF-8");
			int type = parser.getEventType();
			//没解析到最后就继续解析
			while(type!=XmlPullParser.END_DOCUMENT){
				switch (type) {
				case XmlPullParser.START_TAG:
					if("version".equals(parser.getName())){
						bean.setVersion(parser.nextText());
						System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+bean.getVersion());
					}else if("description".equals(parser.getName())){
						bean.setDescription(parser.nextText());
					}else if("apkurl".equals(parser.getName())){
						bean.setApkurl(parser.nextText());
					}
					break;
				}
				type = parser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
}
