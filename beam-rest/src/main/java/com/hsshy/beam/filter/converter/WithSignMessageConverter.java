package com.hsshy.beam.filter.converter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.hsshy.beam.filter.properties.JwtProperties;
import com.hsshy.beam.filter.security.DataSecurityAction;
import com.hsshy.beam.filter.util.JwtTokenUtil;
import com.hsshy.beam.common.enumeration.RetEnum;
import com.hsshy.beam.common.exception.BeamException;
import com.hsshy.beam.common.support.HttpKit;
import com.hsshy.beam.common.utils.MD5Util;
import com.hsshy.beam.common.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * 带签名的http信息转化器
 *
 * @author fengshuonan
 * @date 2017-08-25 15:42
 */
public class WithSignMessageConverter extends FastJsonHttpMessageConverter {

    @Autowired
    JwtProperties jwtProperties;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    DataSecurityAction dataSecurityAction;

    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

        System.out.println(type);
        System.out.println(contextClass.getAnnotations());
        System.out.println(inputMessage);

        InputStream in = inputMessage.getBody();
        Object o = JSON.parseObject(in, super.getFastJsonConfig().getCharset(), BaseTransferEntity.class, super.getFastJsonConfig().getFeatures());

        //先转化成原始的对象
        BaseTransferEntity baseTransferEntity = (BaseTransferEntity) o;

        //校验签名
        String token = HttpKit.getRequest().getHeader(jwtProperties.getHeader()).substring(7);
        String md5KeyFromToken = jwtTokenUtil.getMd5KeyFromToken(token);

        String object = baseTransferEntity.getObject();
        String json = dataSecurityAction.unlock(object);
        String encrypt = MD5Util.encrypt(object + md5KeyFromToken);

        if (encrypt.equals(baseTransferEntity.getSign())) {
            System.out.println("签名校验成功!");
        } else {
            System.out.println("签名校验失败,数据被改动过!");
            throw new BeamException(RetEnum.SIGN_ERROR);
        }

        //如果是泛型则将其解析为  获得 Class 定义中声明的父类的泛型参数类型
        if(type.toString().equals("Entity")){

            return JSON.parseObject(json, ReflectionUtils.getSuperGenericType(contextClass));
        }
        else {

            //校验签名后再转化成应该的对象
            return JSON.parseObject(json, type);
        }

    }
}
