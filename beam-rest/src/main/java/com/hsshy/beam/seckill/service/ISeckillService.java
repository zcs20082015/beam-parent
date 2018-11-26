package com.hsshy.beam.seckill.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hsshy.beam.common.utils.R;
import com.hsshy.beam.seckill.entity.Seckill;

import java.util.List;

/**
 * 秒杀库存表
 *
 * @author hs
 * @email 457030599@qq.com
 * @date 2018-11-20 14:52:22
 */
public interface ISeckillService extends IService<Seckill> {



    /**
     * 查询全部的秒杀记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     * @param seckillId
     * @return
     */
    Seckill selectById(long seckillId);
    /**
     * 查询秒杀售卖商品
     * @param seckillId
     * @return
     */
    Long getSeckillCount(long seckillId);
    /**
     * 删除秒杀售卖商品记录
     * @param seckillId
     * @return
     */
    void deleteSeckill(long seckillId);

    /**
     * 秒杀 一、会出现数量错误
     * @param seckillId
     * @param userId
     * @return
     */
    R startSeckil(long seckillId,long userId);

    /**
     * 秒杀 二、程序锁
     * @param seckillId
     * @param userId
     * @return
     */
    R startSeckilLock(long seckillId,long userId);
    /**
     * 秒杀 二、程序锁AOP
     * @param seckillId
     * @param userId
     * @return
     */
    R startSeckilAopLock(long seckillId,long userId);

    /**
     * 秒杀 二、数据库悲观锁
     * @param seckillId
     * @param userId
     * @return
     */
    R startSeckilDBPCC_ONE(long seckillId,long userId);
    /**
     * 秒杀 三、数据库悲观锁
     * @param seckillId
     * @param userId
     * @return
     */
    R startSeckilDBPCC_TWO(long seckillId, long userId);
    /**
     * 秒杀 三、数据库乐观锁
     * @param seckillId
     * @param userId
     * @return
     */
    R startSeckilDBOCC(long seckillId,long userId,Long number);

    /**
     * 秒杀 四、事物模板
     * @param seckillId
     * @param userId
     * @return
     */
    R startSeckilTemplate(long seckillId,long userId,long number);

}
