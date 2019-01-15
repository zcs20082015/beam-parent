package com.hsshy.beam.work.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hsshy.beam.common.base.entity.RestEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 开通省份
 *
 * @author hs
 */
@Data
@NoArgsConstructor
@TableName("w_province")
public class Province extends RestEntity<Long> {

    // ID
    @TableId
    private Long id;
    // 省份名称
    @TableField(value = "name")
    private String name;
    // 开通状态：0：未开通 1：已开通
    @TableField(value = "status")
    private Integer status;


    @Override
    public Long getId(){
        return id;
    }
    @Override
    public void setId(Long id){
        this.id=id;
    }
    @Override
    protected Serializable pkVal(){
        return this.id;
    }
}
