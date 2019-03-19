package com.hsshy.beam.interceptors.converter;

/**
 * 基础的传输bean
 *
 * @author hs
 * @date 2019-03-13 15:42
 */
public class BaseTransferEntity {

    private String object; //base64编码的json字符串

    private String sign;   //签名

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
