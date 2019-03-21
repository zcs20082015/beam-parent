package com.hsshy.beam.email;
import com.hsshy.beam.common.utils.R;
import com.hsshy.beam.email.service.EmailService;
import com.hsshy.beam.email.util.DisruptorUtil;
import com.hsshy.beam.email.util.OperateEvent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 发送邮件测试
 * @author: hs
 * @create: 2019-03-21 16:54:39
 **/
@Api(value = "EmailController", tags = {"发送邮件测试"})
@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private AmqpTemplate rabbitTemplate;


    @Autowired
    private EmailService emailService;


    @ApiOperation("发送邮件测试")
    @GetMapping("/send")
    public R send(@RequestParam String targetAddress) {
        long startTime = System.currentTimeMillis();    //获取开始时间
        emailService.sendEmail(targetAddress);
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时
        return R.ok();

    }

    @ApiOperation("发送邮件 DeferredResult异步")
    @GetMapping("/send/deferred")
    public DeferredResult<R> deferredSend(@RequestParam String targetAddress) {
        DeferredResult deferredResult = new DeferredResult(20000L);//设置超时时间20秒
        long startTime = System.currentTimeMillis();    //获取开始时间
        emailService.sendEmail(targetAddress);
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时
        deferredResult.setResult(R.ok());
        return deferredResult;

    }

    @ApiOperation("发送邮件测试 disruptor生产者-消费者")
    @GetMapping("/send/disruptor")
    public R disruptorSend(@RequestParam String targetAddress) {
        long startTime = System.currentTimeMillis();    //获取开始时间
        OperateEvent operateEvent = new OperateEvent();
        operateEvent.setTargetId(targetAddress);
        operateEvent.setType(1);
        DisruptorUtil.producer(operateEvent);
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时
        return R.ok();

    }

    @ApiOperation("发送邮件 消息队列")
    @GetMapping("/send/rabbitmq")
    public R rabbitmqSend(@RequestParam String targetAddress) {
        long startTime = System.currentTimeMillis();    //获取开始时间
        rabbitTemplate.convertAndSend("common_queue", targetAddress);
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时
        return R.ok();

    }








}
