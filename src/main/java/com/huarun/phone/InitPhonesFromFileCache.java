package com.huarun.phone;

import com.huarun.phone.cache.PhoneCache;
import com.huarun.phone.utils.FileUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InitPhonesFromFileCache implements InitializingBean {
	
	@Value("${phoneNoFile.path}")
	private String filePath;
	
	public void afterPropertiesSet() {
		Map<String,Object> configMap = FileUtils.getPhoneNoListFromFile(filePath);
		PhoneCache.setPhonesMap(configMap);
	}

}
