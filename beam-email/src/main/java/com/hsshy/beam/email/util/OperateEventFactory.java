package com.hsshy.beam.email.util;


import com.lmax.disruptor.EventFactory;


public class OperateEventFactory implements EventFactory<OperateEvent> {

	public OperateEvent newInstance() {
		return new OperateEvent();
	}
}
