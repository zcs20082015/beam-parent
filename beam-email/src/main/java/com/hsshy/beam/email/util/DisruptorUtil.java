package com.hsshy.beam.email.util;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.ThreadFactory;

/**
 *
 * 创建者 hs
 * 创建时间	2019年3月5日
 *
 */
public class DisruptorUtil {
	
	static Disruptor<OperateEvent> disruptor = null;
	static{
		OperateEventFactory factory = new OperateEventFactory();
		int ringBufferSize = 1024*1024;
		ThreadFactory threadFactory = new ThreadFactory() {
			public Thread newThread(Runnable runnable) {
				return new Thread(runnable);
			}
		};

		disruptor = new Disruptor<OperateEvent>(factory, ringBufferSize, threadFactory);
		disruptor.handleEventsWith(new OperateEventConsumer());
		disruptor.start();
	}
	
	public static void producer(OperateEvent operateEvent){
		RingBuffer<OperateEvent> ringBuffer = disruptor.getRingBuffer();
		OperateProducer producer = new OperateProducer(ringBuffer);
		producer.operate(operateEvent.getTargetId(),operateEvent.getType());
	}
}
