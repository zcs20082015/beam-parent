package com.hsshy.beam.redislock.service;


import com.hsshy.beam.common.utils.R;

public interface ISeckillDistributedService {

	/**
	 * 秒杀 一  单个商品
	 * @param seckillId 秒杀商品ID
	 * @param userId 用户ID
	 * @return
	 */
	R startSeckilRedisLock(long seckillId, long userId);
	R startAopSeckilRedisLock(long seckillId, long userId);
	/**
	 * 秒杀 一  单个商品
	 * @param seckillId 秒杀商品ID
	 * @param userId 用户ID
	 * @return
	 */
//	R startSeckilZksLock(long seckillId, long userId);

	/**
	 * 秒杀 二 多个商品
	 * @param seckillId 秒杀商品ID
	 * @param userId 用户ID
	 * @param number 秒杀商品数量
	 * @return
	 */
//	R startSeckilLock(long seckillId, long userId, long number);
	
}
