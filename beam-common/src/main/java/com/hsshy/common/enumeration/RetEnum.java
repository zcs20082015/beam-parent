package com.hsshy.common.enumeration;

/**
 * 返回信息枚举
 * created by ZWen
 * at 2017/12/6 17:49
 */
public enum RetEnum {
    /**
     * 成功
      */
    SUCCESS(200,"成功"),
    /**
     * 失败
     */
    ERROR(400,"失败"),
    /**
     * 验证码错误
     */
    ERROR_CHECKCODE(-3,"验证码错误"),
    /**
     * 设备禁止多终端登录
     */
    FORBID_MULTI_DEVICE(-4,"设备禁止多终端登录"),
    /**
     * 禁止该用户登录
     */
    FORBID_USER_LOGIN(-5,"禁止该用户登录"),
    /**
     * 禁止访问
     */
    FORBID(1000,"禁止访问"),
    /**
     * 请求已超时
     */
    TIMEOUT(1001,"请求已超时"),
    /**
     * SIGN签名无效
     */
    ERRORSIGN(1002,"无效的签名"),
    /**
     * 参数异常
     */
    ERRORPARAM(1004,"参数异常"),
    /**
     * token过期
     */
    TOKEN_EXPIRED(1005,"token过期"),
    /**
     * token验证失败
     */
    TOKEN_ERROR(1005,"token验证失败"),

    /**
     * 其他
     */
    INVLIDE_DATE_STRING(400, "输入日期格式不对"),

    /**
     * 其他
     */
    WRITE_ERROR(500, "渲染界面错误"),

    /**
     * 文件上传
     */
    FILE_READING_ERROR(400, "FILE_READING_ERROR!"),

    FILE_NOT_FOUND(400, "FILE_NOT_FOUND!"),

    /**
     * 错误的请求
     */
    REQUEST_NULL(400, "请求有错误"),

   /**
    *  服务器异常
    * */
    SERVER_EXCEPTION(500,"未知的服务器异常");

    /**
     * @Description 返回信息
     * @author wendy
     * @date 2017/12/8 14:14
     */
    RetEnum(int ret, String msg){
        setRet(ret);
        setMsg(msg);
    }

    /**
     * @Description 获取返回值
     * @author wendy
     * @date 2017/12/8 14:15
     * @param
     * @return   返回值
     */
    public int getRet() {
        return ret;
    }

    /**
     * @Description 设置返回值
     * @author wendy
     * @date 2017/12/8 14:15
     * @param   ret 返回值
     * @return
     */
    private void setRet(int ret) {
        this.ret = ret;
    }

    /**
     * @Description 获取返回信息
     * @author wendy
     * @date 2017/12/8 14:15
     * @param
     * @return   返回信息
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @Description 设置返回信息
     * @author wendy
     * @date 2017/12/8 14:16
     * @param   msg 返回信息
     */
    private void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @Description 返回值
     * @author wendy 
     * @date 2017/12/8 14:16
     */  
    private int ret;
    /**
     * @Description 返回信息
     * @author wendy 
     * @date 2017/12/8 14:17
     */  
    private String msg;


}
