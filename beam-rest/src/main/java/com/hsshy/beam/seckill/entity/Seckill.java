package com.hsshy.beam.seckill.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 * 秒杀库存表
 *
 * @author hs
 */
@Data
@NoArgsConstructor
@TableName("seckill")
public class Seckill extends Model<Seckill> {

    /**
     * 商品库存id
     *
     */
        @TableId
            private Long id;
    /**
     * 商品名称
     *
     */
            @TableField(value = "name")
        private String name;
    /**
     * 库存数量
     *
     */
            @TableField(value = "number")
        private Integer number;
    /**
     * 秒杀开启时间
     *
     */
            @TableField(value = "start_time")
        private Date startTime;
    /**
     * 秒杀结束时间
     *
     */
            @TableField(value = "end_time")
        private Date endTime;
    /**
     * 创建时间
     *
     */
            @TableField(value = "create_time")
        private Date createTime;
    /**
     * 版本号
     *
     */
        @Version
            @TableField(value = "version")
        private Integer version;




    @Override
    protected Serializable pkVal(){
        return this.id;
    }
}
