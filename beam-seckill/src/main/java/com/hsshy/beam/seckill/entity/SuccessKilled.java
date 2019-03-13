package com.hsshy.beam.seckill.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 * 秒杀成功明细表
 *
 * @author hs
 */
@Data
@NoArgsConstructor
@TableName("success_killed")
public class SuccessKilled extends Model<SuccessKilled> {

    /**
     * 秒杀商品id
     *
     */
        @TableId
            private Long id;

        @TableField(value = "seckill_id")
        private  Long seckillId;
    /**
     * 用户Id
     *
     */
            @TableField(value = "user_id")
        private Long userId;
    /**
     * 状态标示：-1指无效，0指成功，1指已付款
     *
     */
            @TableField(value = "state")
        private Integer state;
    /**
     * 创建时间
     *
     */
            @TableField(value = "create_time")
        private Date createTime;



    @Override
    protected Serializable pkVal(){
        return this.id;
    }
}
