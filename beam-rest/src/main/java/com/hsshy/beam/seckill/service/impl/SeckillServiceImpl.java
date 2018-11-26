package com.hsshy.beam.seckill.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hsshy.beam.seckill.aop.ServiceLimit;
import com.hsshy.beam.seckill.aop.Servicelock;
import com.hsshy.beam.common.utils.R;
import com.hsshy.beam.seckill.dao.SeckillMapper;
import com.hsshy.beam.seckill.entity.Seckill;
import com.hsshy.beam.seckill.entity.SuccessKilled;
import com.hsshy.beam.seckill.service.ISeckillService;
import com.hsshy.beam.seckill.service.ISuccessKilledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 秒杀库存表
 *
 * @author hs
 * @email 457030599@qq.com
 * @date 2018-11-20 14:52:22
 */
@Service(value = "seckillService")
public class SeckillServiceImpl extends ServiceImpl<SeckillMapper, Seckill> implements ISeckillService {

    @Autowired
    private ISuccessKilledService successKilledService;

    /**
     * 思考：为什么不用synchronized
     * service 默认是单例的，并发下lock只有一个实例
     */
    private Lock lock = new ReentrantLock(true);//互斥锁 参数默认false，不公平锁



    @Override
    public List<Seckill> getSeckillList() {
        return this.getSeckillList();
    }

    @Override
    public Seckill selectById(long seckillId) {
        return this.getById(seckillId);
    }

    @Override
    public Long getSeckillCount(long seckillId) {

        Integer count = successKilledService.count(new QueryWrapper<SuccessKilled>().lambda().eq(SuccessKilled::getSeckillId,seckillId));
        return Long.parseLong(count+"");
    }
    @Override
    @Transactional
    public void deleteSeckill(long seckillId) {

        successKilledService.remove(new QueryWrapper<SuccessKilled>().lambda().eq(SuccessKilled::getSeckillId,seckillId));

        Seckill seckill = this.getById(seckillId);
        seckill.setNumber(100);
        this.updateById(seckill);
    }
    @Override
//    @ServiceLimit
    @Transactional
    public R startSeckil(long seckillId,long userId) {
        Integer number = this.getById(seckillId).getNumber();
        if(number>0){
            //扣库存

            Seckill seckill = new Seckill();
            seckill.setId(seckillId);
            baseMapper.updateNum(seckill);
            //创建订单
            SuccessKilled killed = new SuccessKilled();
            killed.setSeckillId(seckillId);
            killed.setUserId(userId);
            killed.setState(0);
            killed.setCreateTime(new Timestamp(new Date().getTime()));
            successKilledService.save(killed);
            //支付
            return R.ok();
        }else{
            return R.fail();
        }
    }
    @Override
    @Transactional
    public R  startSeckilLock(long seckillId, long userId) {
        try {
            lock.lock();
            //这里、不清楚为啥、总是会被超卖101、难道锁不起作用、lock是同一个对象
            //来自热心网友 zoain 的细心测试思考、然后自己总结了一下
            //事物未提交之前，锁已经释放(事物提交是在整个方法执行完)，导致另一个事物读取到了这个事物未提交的数据，也就是传说中的脏读。建议锁上移
            //给自己留个坑思考：为什么分布式锁(zk和redis)没有问题？(事实是有问题的，由于redis释放锁需要远程通信，不那么明显而已)
            Integer number = this.getById(seckillId).getNumber();

            if(number>0){
                Seckill seckill = new Seckill();
                seckill.setId(seckillId);
                baseMapper.updateNum(seckill);
                SuccessKilled killed = new SuccessKilled();
                killed.setSeckillId(seckillId);
                killed.setUserId(userId);
                killed.setState(number.intValue());
                killed.setCreateTime(new Timestamp(new Date().getTime()));
                successKilledService.save(killed);
            }else{
                return R.fail();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return R.ok();
    }
    @Override
    @Servicelock
    @Transactional
    public R startSeckilAopLock(long seckillId, long userId) {
        //来自码云码友<马丁的早晨>的建议 使用AOP + 锁实现
        Integer number = this.getById(seckillId).getNumber();

        if(number>0){
            Seckill seckill = new Seckill();
            seckill.setId(seckillId);
            baseMapper.updateNum(seckill);
            SuccessKilled killed = new SuccessKilled();
            killed.setSeckillId(seckillId);
            killed.setUserId(userId);
            killed.setState(number.intValue());
            killed.setCreateTime(new Timestamp(new Date().getTime()));
            successKilledService.save(killed);

        }else{
            System.out.println(R.fail());

            return R.fail();
        }
        return R.ok();
    }
    //注意这里 限流注解 可能会出现少买 自行调整
    @Override
    @ServiceLimit
    @Transactional
    public R startSeckilDBPCC_ONE(long seckillId, long userId) {
        //单用户抢购一件商品或者多件都没有问题
        Integer number = this.getById(seckillId).getNumber();

        if(number>0){
            Seckill seckill = new Seckill();
            seckill.setId(seckillId);
            baseMapper.updateNum(seckill);
            SuccessKilled killed = new SuccessKilled();
            killed.setSeckillId(seckillId);
            killed.setUserId(userId);
            killed.setState(0);
            killed.setCreateTime(new Timestamp(new Date().getTime()));
            successKilledService.save(killed);
            return R.ok();
        }else{
            return R.fail();
        }
    }
    /**
     * SHOW STATUS LIKE 'innodb_row_lock%';
     * 如果发现锁争用比较严重，如InnoDB_row_lock_waits和InnoDB_row_lock_time_avg的值比较高
     */
    @Override
    @Transactional
    public R startSeckilDBPCC_TWO(long seckillId, long userId) {
        //单用户抢购一件商品没有问题、但是抢购多件商品不建议这种写法
        Seckill seckill = new Seckill();
        seckill.setId(seckillId);
        seckill.setNumber(0);
        Integer count = baseMapper.updateNum(seckill);
        if(count>0){
            SuccessKilled killed = new SuccessKilled();
            killed.setSeckillId(seckillId);
            killed.setUserId(userId);
            killed.setState(0);
            killed.setCreateTime(new Timestamp(new Date().getTime()));
            successKilledService.save(killed);
            return R.ok();
        }else{
            return R.fail();
        }
    }
    @Override
    @Transactional
    public R startSeckilDBOCC(long seckillId, long userId, Long number) {
        Seckill kill = this.getById(seckillId);
        if(kill.getNumber()>=number){//剩余的数量应该要大于等于秒杀的数量
            //乐观锁

            Seckill seckill = this.getById(seckillId);
            seckill.setNumber(seckill.getNumber()-number.intValue());
            if(this.updateById(seckill)){
                SuccessKilled killed = new SuccessKilled();
                killed.setSeckillId(seckillId);
                killed.setUserId(userId);
                killed.setState(0);
                killed.setCreateTime(new Timestamp(new Date().getTime()));
                successKilledService.save(killed);
                return R.ok();
            }else{
                return R.fail();
            }
        }else{
            return R.fail();
        }
    }

    @Override
    public R startSeckilTemplate(long seckillId, long userId, long number) {
        return null;
    }

}
