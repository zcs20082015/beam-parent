package com.hsshy.beam.modular.queue.controller;
import com.hsshy.beam.common.utils.R;
import com.hsshy.beam.queue.disruptor.DisruptorUtil;
import com.hsshy.beam.queue.disruptor.SeckillEvent;
import com.hsshy.beam.queue.entity.SuccessKilled;
import com.hsshy.beam.queue.jvm.SeckillQueue;
import com.hsshy.beam.queue.service.ISeckillService;
import com.hsshy.beam.common.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 秒杀库存表
 *
 * @author hs
 * @email 457030599@qq.com
 * @date 2018-11-20 14:52:22
 */
@Api(value="SeckillController",tags={"Seckill接口"})
@RequestMapping("/queue/seckill")
@RestController
public class SeckillController extends BaseController {

    @Autowired
    private ISeckillService seckillService;

    private final static Logger LOGGER = LoggerFactory.getLogger(SeckillController.class);

    private static int corePoolSize = Runtime.getRuntime().availableProcessors();
    //创建线程池  调整队列数 拒绝服务
    private static ThreadPoolExecutor executor  = new ThreadPoolExecutor(corePoolSize, corePoolSize+1, 10l, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(1000));



    @ApiOperation(value="秒杀一(最low实现)",nickname="科帮网")
    @PostMapping("/start")
    public R start(long seckillId){
        int skillNum = 1000;
        final CountDownLatch latch = new CountDownLatch(skillNum);//N个购买者
        seckillService.deleteSeckill(seckillId);
        final long killId =  seckillId;
        LOGGER.info("开始秒杀一(会出现超卖)");
        for(int i=0;i<skillNum;i++){
            final long userId = i;
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    R result = seckillService.startSeckil(killId, userId);
                    if(result!=null){
                        LOGGER.info("用户:{}{}",userId,result.get("msg"));
                    }else{
                        LOGGER.info("用户:{}{}",userId,"哎呦喂，人也太多了，请稍后！");
                    }
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
    @ApiOperation(value="秒杀二(程序锁)",nickname="科帮网")
    @PostMapping("/startLock")
    public R startLock(long seckillId){
        int skillNum = 1000;
        final CountDownLatch latch = new CountDownLatch(skillNum);//N个购买者
        seckillService.deleteSeckill(seckillId);
        final long killId =  seckillId;
        LOGGER.info("开始秒杀二(正常)");
        for(int i=0;i<1000;i++){
            final long userId = i;
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    R result = seckillService.startSeckilLock(killId, userId);
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
    @ApiOperation(value="秒杀三(AOP程序锁)",nickname="科帮网")
    @PostMapping("/startAopLock")
    public R startAopLock(long seckillId){
        int skillNum = 1000;
        final CountDownLatch latch = new CountDownLatch(skillNum);//N个购买者
        seckillService.deleteSeckill(seckillId);
        final long killId =  seckillId;
        LOGGER.info("开始秒杀三(正常)");
        for(int i=0;i<1000;i++){
            final long userId = i;
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    R result = seckillService.startSeckilAopLock(killId, userId);
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
    @ApiOperation(value="秒杀四(数据库悲观锁)",nickname="科帮网")
    @PostMapping("/startDBPCC_ONE")
    public R startDBPCC_ONE(long seckillId){
        int skillNum = 1000;
        final CountDownLatch latch = new CountDownLatch(skillNum);//N个购买者
        seckillService.deleteSeckill(seckillId);
        final long killId =  seckillId;
        LOGGER.info("开始秒杀四(正常)");
        for(int i=0;i<1000;i++){
            final long userId = i;
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    R result = seckillService.startSeckilDBPCC_ONE(killId, userId);

                    LOGGER.info("用户:{}{}",userId,result.get("error"));
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
    @ApiOperation(value="秒杀五(数据库悲观锁)",nickname="科帮网")
    @PostMapping("/startDPCC_TWO")
    public R startDPCC_TWO(long seckillId){
        int skillNum = 1000;
        final CountDownLatch latch = new CountDownLatch(skillNum);//N个购买者
        seckillService.deleteSeckill(seckillId);
        final long killId =  seckillId;
        LOGGER.info("开始秒杀五(正常、数据库锁最优实现)");
        for(int i=0;i<1000;i++){
            final long userId = i;
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    R result = seckillService.startSeckilDBPCC_TWO(killId, userId);
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
    @ApiOperation(value="秒杀六(数据库乐观锁)",nickname="科帮网")
    @PostMapping("/startDBOCC")
    public R startDBOCC(long seckillId){
        int skillNum = 1000;
        final CountDownLatch latch = new CountDownLatch(skillNum);//N个购买者
        seckillService.deleteSeckill(seckillId);
        final long killId =  seckillId;
        LOGGER.info("开始秒杀六(正常、数据库锁最优实现)");
        for(int i=0;i<1000;i++){
            final long userId = i;
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    //这里使用的乐观锁、可以自定义抢购数量、如果配置的抢购人数比较少、比如120:100(人数:商品) 会出现少买的情况
                    //用户同时进入会出现更新失败的情况
                    R result = seckillService.startSeckilDBOCC(killId, userId,1L);
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
    @ApiOperation(value="秒杀柒(进程内队列)",nickname="科帮网")
    @PostMapping("/startQueue")
    public R startQueue(long seckillId){

        seckillService.deleteSeckill(seckillId);
        final long killId =  seckillId;
        LOGGER.info("开始秒杀柒(正常)");
        for(int i=0;i<1000;i++){
            final long userId = i;
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    SuccessKilled kill = new SuccessKilled();
                    kill.setId(killId);
                    kill.setUserId(userId);
                    try {
                        Boolean flag = SeckillQueue.getMailQueue().produce(kill);
                        if(flag){
                            LOGGER.info("用户:{}{}",kill.getUserId(),"秒杀成功");
                        }else{
                            LOGGER.info("用户:{}{}",userId,"秒杀失败");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        LOGGER.info("用户:{}{}",userId,"秒杀失败");
                    }
                }
            };
            executor.execute(task);
        }
        try {
            Thread.sleep(10000);
            Long  seckillCount = seckillService.getSeckillCount(seckillId);
            LOGGER.info("一共秒杀出{}件商品",seckillCount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return R.ok();
    }
    @ApiOperation(value="秒杀柒(Disruptor队列)",nickname="科帮网")
    @PostMapping("/startDisruptorQueue")
    public R startDisruptorQueue(long seckillId){
        seckillService.deleteSeckill(seckillId);
        final long killId =  seckillId;
        LOGGER.info("开始秒杀八(正常)");
        for(int i=0;i<1000;i++){
            final long userId = i;
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    SeckillEvent kill = new SeckillEvent();
                    kill.setSeckillId(killId);
                    kill.setUserId(userId);
                    DisruptorUtil.producer(kill);
                }
            };
            executor.execute(task);
        }
        try {
            Thread.sleep(10000);
            Long  seckillCount = seckillService.getSeckillCount(seckillId);
            LOGGER.info("一共秒杀出{}件商品",seckillCount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return R.ok();
    }






}