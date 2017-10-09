package com.huarun.phone.controller;

import com.alibaba.fastjson.JSON;
import com.huarun.phone.cache.PhoneCache;
import com.huarun.phone.utils.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 验证手机号是否存在
 */
@Controller
public class PhoneValidController {
    private Logger logger = Logger.getLogger(this.getClass());
    @Value("${phoneNoFile.path}")
    private String filePath;

    @Value("${remember.new}")
    private String rememberNew;

    @RequestMapping(value = "/valid/{phone}",produces="application/json;charset=UTF-8")
    @ResponseBody
    public String verifyPhoneNoExists(HttpServletResponse response, @PathVariable("phone") String phone) {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<String, Object>();
        logger.info("收到请求参数:"+phone);
        if (!isPhone(phone)) {
            map.put("msg", "手机号格式不正确");
        } else {
            if (PhoneCache.phonesMap.containsKey(phone)) {
                logger.info("手机号" + phone + "已经存在");
                map.put("msg", "手机号" + phone + "已经存在");
            } else {
                map.put("msg", "手机号"+ phone +"不存在");
                if ("true".equals(rememberNew)) {
                    PhoneCache.phonesMap.put(phone, phone);
                    FileUtils.write(filePath, phone);
                }
            }
        }

        String jsonString = JSON.toJSONString(map);
        return jsonString;
    }

    /**
     * 验证手机号格式是否正确
     * @param str
     * @return
     */
    private boolean isPhone(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        if (str.length() < 11 || str.length() > 20) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}