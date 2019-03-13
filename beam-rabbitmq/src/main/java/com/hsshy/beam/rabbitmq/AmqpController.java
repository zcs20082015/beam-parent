package com.hsshy.beam.rabbitmq;


import com.hsshy.beam.rabbitmq.dto.Order;
import com.hsshy.beam.rabbitmq.service.DelaySender;
import com.hsshy.beam.rabbitmq.service.ProducerService;
import com.hsshy.beam.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 消息队列测试
 * @author: hs
 * @create: 2019-03-01 16:54:39
 **/
@Api(value="AmqpController",tags={"消息队列测试"})
@RestController
@RequestMapping("/amqp")
public class AmqpController {

    @Autowired
    private ProducerService producerService;

    @Autowired
    private DelaySender delaySender;

    @ApiOperation("单生产者单消费者测试")
    @GetMapping("/test1")
    public R test1(){

        //若有多个生产者消费者则会均匀的接收
        producerService.send();
        return R.ok();

    }

    @ApiOperation("topic测试")
    @GetMapping("/test2")
    public R test2(){

        producerService.topicSend1();
        return R.ok();

    }

    @ApiOperation("topic测试")
    @GetMapping("/test3")
    public R test3(){

        producerService.topicSend2();
        return R.ok();

    }

    @ApiOperation("fanout测试")
    @GetMapping("/fanout")
    public R test4(){

        producerService.fanoutsend();
        return R.ok();

    }




    @ApiOperation("延迟队列测试")
    @GetMapping("/sendDelay")
    public R sendDelay() {
        Order order1 = new Order();
        order1.setOrderStatus(0);
        order1.setOrderId("123456");
        order1.setOrderName("小米6");

        Order order2 = new Order();
        order2.setOrderStatus(1);
        order2.setOrderId("456789");
        order2.setOrderName("小米8");

        delaySender.sendDelay(order1);
        delaySender.sendDelay(order2);
        return R.ok();
    }




}
