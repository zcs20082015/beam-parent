package com.hsshy.beam.interceptors.security.impl;
import com.hsshy.beam.interceptors.security.DataSecurityAction;
import org.springframework.util.Base64Utils;

/**
 * 对数据进行base64编码的方式
 *
 * @author hs
 * @date 2019-03-13 10:09
 */
public class Base64SecurityAction implements DataSecurityAction {

    @Override
    public String doAction(String beProtected) {
        return Base64Utils.encodeToString(beProtected.getBytes());
    }

    @Override
    public String unlock(String securityCode) {
        byte[] bytes = Base64Utils.decodeFromString(securityCode);
        return new String(bytes);
    }
}
