package com.hsshy.beam.modular.hello.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hsshy.beam.mybatis.base.DataEntity;

import java.io.Serializable;

/**
 * @description: 测试类
 * @author: hs
 * @create: 2018-09-20 22:42:04
 **/
@TableName("beam_hello")
public class Hello extends DataEntity  {

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
