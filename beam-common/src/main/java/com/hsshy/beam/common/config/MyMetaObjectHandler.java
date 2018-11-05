package com.hsshy.beam.common.config;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**  自定义填充公共 name 字段  */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {



    /**
     * 测试 user 表 name 字段为空自动填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {


        setFieldValByName("createDate",  new Timestamp(System.currentTimeMillis()), metaObject);
        setFieldValByName("updateDate", new Timestamp(System.currentTimeMillis()), metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateDate", new Timestamp(System.currentTimeMillis()), metaObject);
    }
}