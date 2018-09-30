package com.hsshy.beam.common.exception;

import com.hsshy.beam.common.enumeration.RetEnum;

/**
 * 封装guns的异常
 *
 * @author fengshuonan
 * @Date 2017/12/28 下午10:32
 */
public class BeamException extends RuntimeException {

    private Integer code;

    private String message;

    public BeamException(RetEnum retEnum) {
        this.code = retEnum.getRet();
        this.message = retEnum.getMsg();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
