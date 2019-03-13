package com.hsshy.beam.seckill.dao;
import com.hsshy.beam.seckill.entity.Seckill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 秒杀库存表
 * 
 * @author hs
 * @email 457030599@qq.com
 * @date 2018-11-20 14:52:22
 */
public interface SeckillMapper extends BaseMapper<Seckill> {

    Integer updateNum(@Param("seckill") Seckill seckill);


}
