package com.hsshy.beam.email.util;

import com.lmax.disruptor.EventTranslatorVararg;
import com.lmax.disruptor.RingBuffer;

public class OperateProducer {
	
	private final static EventTranslatorVararg<OperateEvent> translator = new EventTranslatorVararg<OperateEvent>() {
		public void translateTo(OperateEvent operateEvent, long seq, Object... objs) {
			operateEvent.setTargetId((String) objs[0]);
			operateEvent.setType((int) objs[1]);
		}
	};

	private final RingBuffer<OperateEvent> ringBuffer;
	
	public OperateProducer(RingBuffer<OperateEvent> ringBuffer){
		this.ringBuffer = ringBuffer;
	}
	
	public void operate(String targetId,int type){
		this.ringBuffer.publishEvent(translator,targetId,type);
	}
}
