package com.hsshy.beam.work.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hsshy.beam.common.base.entity.RestEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 开通区域
 *
 * @author hs
 */
@Data
@NoArgsConstructor
@TableName("w_area")
public class Area extends RestEntity<Long> {

    // ID
    @TableId
    private Long id;
    // 行政编码
    @TableField(value = "code")
    private String code;
    // 地区名称
    @TableField(value = "name")
    private String name;
    // 城市行政编码
    @TableField(value = "city_code")
    private String cityCode;
    // 开通状态：0：未开通 1：已开通
    @TableField(value = "status")
    private Integer status;
    // 城市ID
    @TableField(value = "city_id")
    private Long cityId;
    // 省份ID
    @TableField(value = "province_id")
    private Long provinceId;


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
