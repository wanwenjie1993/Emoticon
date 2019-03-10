package com.wanwj1.xw.jsoup;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.wanwj1.xw.entity.TImg;



public class Pic {   

public static void main(String[] args) {   
//60
	String aaString="http://img.doutula.com/production/uploads/image//2019/02/26/20190226189216_fnyVwu.jpg";
	System.out.println(new Pic().getImg(1));
	
}

public List<TImg> getImg(int num) {
	Map<String, String> map=new HashMap<String,String>();
	map.put("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
	map.put("accept-encoding","utf-8");
	map.put("accept-language","zh-CN,zh;q=0.9");
	map.put("cookie","__cfduid=d6d240e51145851de3c74a9d2c589147c1551274475; BAIDU_SSP_lcr=https://www.baidu.com/link?url=eF21GuodRuKBGqPlkyVHiE0Kw185XrKoKD42egoR8yFbg6J9a3J9ZWp03xyrUKvz&wd=&eqid=e47b91f90014b835000000055c7691e4; UM_distinctid=1692f29268615b-0f505f1d85993a-38395c0b-1fa400-1692f2926878cc; _ga=GA1.2.467185891.1551274420; _gid=GA1.2.879295373.1551274420; yjs_id=c7a2a073e17a5447045c24540e4caaca; ctrl_time=1; CNZZDATA1256911977=1499156307-1551269255-null%7C1551271287; _gat=1; XSRF-TOKEN=eyJpdiI6IkNtOWhtXC9ONEt1S0FrSGNlUFJDSVFBPT0iLCJ2YWx1ZSI6IkxQblhEWEtldUlFeUJyTmRvc3FJQ09IaGd0dXN6a1FLYmVHYVowZ3hTUWdtZlwvc2RuanpmaW1OQ2tNN3VYdHBJIiwibWFjIjoiOTI2ZmM4YjczOWIwMTdkZGJhZmNiNTZhYzIxOTI5NzJhZTE5ZDlkMjFmYzYwNGUyNmM1MWJiZTYyZGJkNThiMyJ9; doutula_session=eyJpdiI6InRlNTdvcVRZUGd0ZFBpcjhKRTFESXc9PSIsInZhbHVlIjoick9HZzVMdmVXQmpcL0R0Q0JnWHBBSTU5RHcxRDVGbEN3UkZKODN2bTlSR1VUbWlvVG1rbitSZml2NWpCa2pWcTciLCJtYWMiOiI0M2RmZWExMWIwODUwMTE3YTA3ZWQ1ZTMzZDllMzNiYjFkYmFkNzJlN2MxYjdkOWZjMTVjMjhhZDhhZmYxZDk2In0%3D");
	map.put("referer","https://www.doutula.com/photo/list/?page=2");
	map.put("upgrade-insecure-requests","1");
	map.put("user-agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36");
	try {
		Document documen=Jsoup.connect("https://www.doutula.com/photo/list/?page="+num).headers(map).get();
		Elements element=documen.body().select("img");
		List<TImg> list=new ArrayList<TImg>();
		for(int i=0;i<element.size();i++) {
			String imgUrl=element.get(i).attr("data-original").toString();
			String imgName=element.get(i).attr("alt").toString();
			imgUrl=imgUrl.replace("!dta", "");
			if(imgUrl.length()>60) {
			TImg timg=new TImg();
			timg.setPicName(imgName);
			timg.setPicUrl(imgUrl);
			timg.setStatus(1);
			list.add(timg);
			saveToFile(imgUrl,imgUrl.substring(31));
			}
		}
		return list;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}   

public static void saveToFile(String destUrl,String imgName) {  
	 FileOutputStream fos = null;  
	 BufferedInputStream bis = null;  
	 HttpURLConnection httpUrl = null;  
	 URL url = null;  
	 int BUFFER_SIZE = 1024;  
	 byte[] buf = new byte[BUFFER_SIZE];  
	 int size = 0;  
	 try {  
	 url = new URL(destUrl);  
	 httpUrl = (HttpURLConnection) url.openConnection();  
	 httpUrl.connect();  
	 bis = new BufferedInputStream(httpUrl.getInputStream());  
	 fos = new FileOutputStream("d:\\images\\"+imgName);  
	 while ((size = bis.read(buf)) != -1) {  
	 fos.write(buf, 0, size);  
	 }  
	 fos.flush();  
	 } catch (IOException e) {  
	 } catch (ClassCastException e) {  
	 } finally {  
	 try {  
	 fos.close();  
	 bis.close();  
	 httpUrl.disconnect();  
	 } catch (IOException e) {  
	 } catch (NullPointerException e) {  
	 }  
	 }  
	 }  
}


