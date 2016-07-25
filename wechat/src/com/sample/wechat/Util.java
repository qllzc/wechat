package com.sample.wechat;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;

public class Util {
	static String  token="wechattest";
	static String appid="wx5817e3c05201c96d";
	static String appsecret="e5056311a0bdffbafad43e42ed45603d";
	public static String loadJSON (String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL URL = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)URL.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-type", "text/html");
            httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
            httpURLConnection.setRequestProperty("contentType", "utf-8");
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"utf-8"));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        System.out.println(json);
		
        return json.toString();
		
    }
	public static Map getAccess_tokenAndOpenId(String code) {
		String URL =String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code", appid,appsecret,code);
		return getMapFromJsonObjStr(loadJSON(URL));
		
	}
	
	public static Map getMapFromJsonObjStr(String jsonObjStr) {   
       JSONObject jsonObject = JSONObject.fromObject(jsonObjStr);   
  
       Map map = new HashMap<>();   
        for (Iterator iter = jsonObject.keys(); iter.hasNext();) {   
            String key =  (String) iter.next();   
            map.put(key,  jsonObject.get(key));   
        }   
        return map;   
    }    
}
