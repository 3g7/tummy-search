package com.fayelau.tummy.search.core.utils;

import java.security.MessageDigest;
import java.util.Map;

import com.fayelau.tummy.search.entity.Session;

public class BaseSecurity {
    
    public static ThreadLocal<Session> currentSessionLocal = new ThreadLocal<>();

	public static ThreadLocal<Map<String, String>> clientInfoLocal = new ThreadLocal<>();
	
	public static String currentPassportId() {
		if (currentSessionLocal.get() != null) {
			return currentSessionLocal.get().getPassportId();
		}
		return "ANONYMOUS";
	}
	
	public static String getClientInfo(String key) {
	    Map<String, String> clientInfo = clientInfoLocal.get();
	    if (clientInfo == null) {
	        return "UNKNOWN";
	    }
	    return clientInfo.get(key);
	}
	
	public static Session currentSession() {
	    return currentSessionLocal.get();
	}
	
	public static String MD5(String key, String STR) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			StringBuilder keyBuilder = new StringBuilder(STR).append(key);
			keyBuilder.append("com.fayelau.tummy").append("@3g7:3g7@fayelau.com").append("@#$%^&*|?/:;'1234567890");
			keyBuilder.append("ABCDEFGHIGKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz.");
			byte[] btInput = keyBuilder.toString().getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(BaseSecurity.MD5("fayelau", "fayelau"));
	}
	
	

}
