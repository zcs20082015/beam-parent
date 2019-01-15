package com.hsshy.beam.work.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hsshy.beam.common.base.entity.RestEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 开通城市
 *
 * @author hs
 */
@Data
@NoArgsConstructor
@TableName("w_city")
public class City extends RestEntity<Long> {

    // ID
    @TableId
    private Long id;
    // 
    @TableField(value = "code")
    private String code;
    // 城市名称
    @TableField(value = "name")
    private String name;
    // 
    @TableField(value = "province_code")
    private String provinceCode;
    // 省份ID
    @TableField(value = "province_id")
    private Long provinceId;
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
