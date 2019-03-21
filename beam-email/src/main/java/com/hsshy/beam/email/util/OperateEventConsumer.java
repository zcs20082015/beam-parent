package com.hsshy.beam.email.util;
import com.hsshy.beam.common.utils.SpringContextHolder;
import com.hsshy.beam.email.service.EmailService;
import com.lmax.disruptor.EventHandler;


public class OperateEventConsumer implements EventHandler<OperateEvent> {
	
	private EmailService emailService = (EmailService) SpringContextHolder.getBean("emailService");

	
	public void onEvent(OperateEvent operateEvent, long seq, boolean bool) throws Exception {

		System.out.println("消费者开始执行："+"type="+operateEvent.getType());
		if(operateEvent.getType()==1){
			emailService.sendEmail(operateEvent.getTargetId());
		}


	}
}
