package com.hsshy.beam.admin.core.util;


import com.hsshy.beam.admin.config.properties.BeamAdminProperties;
import com.hsshy.beam.common.utils.SpringContextHolder;

/**
 * 验证码工具类
 */
public class KaptchaUtil {

    private boolean kaptchaOnOff = false;

    public boolean isKaptchaOnOff() {
        return kaptchaOnOff;
    }
    /**
     * 获取验证码开关
     */
    public void setKaptchaOnOff() {
        this.kaptchaOnOff = SpringContextHolder.getBean(BeamAdminProperties.class).getKaptchaOpen();
    }

}