package com.huarun.phone.cache;

import java.util.HashMap;
import java.util.Map;

public class PhoneCache {
	 public  static Map<String, Object> phonesMap = new HashMap<String, Object>() ;

	public static void setPhonesMap(Map<String, Object> phonesMap) {
		PhoneCache.phonesMap = phonesMap;
	}
}
