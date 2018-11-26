package com.hsshy.beam.seckill.controller;


import com.hsshy.beam.common.utils.R;
import com.hsshy.beam.seckill.service.ISeckillService;
import com.hsshy.beam.seckill.service.ISeckillDistributedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Api(tags ="分布式秒杀")
@RestController
@RequestMapping("/seckillDistributed")
public class SeckillDistributedController {
	private final static Logger LOGGER = LoggerFactory.getLogger(SeckillDistributedController.class);
	
	private static int corePoolSize = Runtime.getRuntime().availableProcessors();
	//调整队列数 拒绝服务
	private static ThreadPoolExecutor executor  = new ThreadPoolExecutor(corePoolSize, corePoolSize+1, 10l, TimeUnit.SECONDS,
			new LinkedBlockingQueue<Runnable>(10000));
	
	@Autowired
	private ISeckillService seckillService;
	@Autowired
	private ISeckillDistributedService seckillDistributedService;



	
	@ApiOperation(value="秒杀一(Rediss分布式锁)",nickname="科帮网")
	@PostMapping("/startRedisLock")
	public R startRedisLock(long seckillId){
		int skillNum = 10000;
		final CountDownLatch latch = new CountDownLatch(skillNum);//N个购买者
		seckillService.deleteSeckill(seckillId);
		final long killId =  seckillId;
		LOGGER.info("开始秒杀一");
		for(int i=0;i<10000;i++){
			final long userId = i;
			Runnable task = new Runnable() {
				@Override
				public void run() {
					R result = seckillDistributedService.startSeckilRedisLock(killId, userId);
					LOGGER.info("用户:{}{}",userId,result.get("msg"));
					latch.countDown();
				}
			};
			executor.execute(task);
		}
		try {
			latch.await();// 等待所有人任务结束
			Long  seckillCount = seckillService.getSeckillCount(seckillId);
			LOGGER.info("一共秒杀出{}件商品",seckillCount);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return R.ok();
	}

	@ApiOperation(value="秒杀一(AOP Rediss分布式锁)",nickname="科帮网")
	@PostMapping("/startAopRedisLock")
	public R startAopRedisLock(long seckillId){
		int skillNum = 11000;
		final CountDownLatch latch = new CountDownLatch(skillNum);//N个购买者
		seckillService.deleteSeckill(seckillId);
		final long killId =  seckillId;
		LOGGER.info("开始秒杀一");
		for(int i=0;i<11000;i++){
			final long userId = i;
			Runnable task = new Runnable() {
				@Override
				public void run() {
					R result = seckillDistributedService.startAopSeckilRedisLock(killId, userId);
					LOGGER.info("用户:{}{}",userId,result.get("msg"));
					latch.countDown();
				}
			};
			executor.execute(task);
		}
		try {
			latch.await();// 等待所有人任务结束
			Long  seckillCount = seckillService.getSeckillCount(seckillId);
			LOGGER.info("一共秒杀出{}件商品",seckillCount);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return R.ok();
	}

}
