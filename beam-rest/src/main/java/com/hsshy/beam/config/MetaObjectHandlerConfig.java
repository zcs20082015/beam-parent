package com.hsshy.beam.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MetaObjectHandlerConfig implements MetaObjectHandler {

  @Override
  public void insertFill(MetaObject metaObject) {
    setFieldValByName("createDate", new Date(), metaObject);
    setFieldValByName("createBy", 1L, metaObject);
    setFieldValByName("updateDate", new Date(), metaObject);
    setFieldValByName("updateBy", 1L, metaObject);
  }

  @Override
  public void updateFill(MetaObject metaObject) {
    setFieldValByName("updateDate", new Date(), metaObject);
    setFieldValByName("updateBy", 1L, metaObject);
  }
}
