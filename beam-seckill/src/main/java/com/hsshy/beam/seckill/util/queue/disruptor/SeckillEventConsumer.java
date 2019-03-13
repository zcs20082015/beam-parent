package com.hsshy.beam.seckill.util.queue.disruptor;

import com.hsshy.beam.common.utils.SpringContextHolder;
import com.hsshy.beam.seckill.service.ISeckillService;
import com.lmax.disruptor.EventHandler;

/**
 * 消费者(秒杀处理器)
 * 创建者 科帮网
 */
public class SeckillEventConsumer implements EventHandler<SeckillEvent> {
	
	private ISeckillService seckillService = (ISeckillService) SpringContextHolder.getBean("seckillService");
	
	public void onEvent(SeckillEvent seckillEvent, long seq, boolean bool) throws Exception {
		seckillService.startSeckil(seckillEvent.getSeckillId(), seckillEvent.getUserId());
	}
}
