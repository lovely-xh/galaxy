package com.galaxy.utils;

import java.security.MessageDigest;
import java.util.Arrays;

import org.apache.log4j.Logger;

public class CheckUtil {
	
	private static final String token = "galaxy";
	
	private static Logger logger = Logger.getLogger(CheckUtil.class);
	
	public static boolean checkSignature(String signature, String timestamp, String nonce) {
		String[] arr = new String[] {token, timestamp, nonce};
		
		Arrays.sort(arr);
		
		logger.debug(Arrays.toString(arr));
		
		StringBuffer content = new StringBuffer();
		for (int i = 0; i< arr.length; i++) {
			content.append(arr[i]);
		}

		String sign = getShaHex(content.toString());
		logger.debug("sign:" + sign);
		
		return sign.equals(signature);
	}
	
	private static String getShaHex(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		
		char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f'};
		
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes("UTF-8"));
			
			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			
			return new String(buf);
		} catch (Exception e) {
			return null;
		}
	}
}
