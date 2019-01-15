package com.hsshy.beam.seckill.util.redis.message;


import com.hsshy.beam.common.utils.R;
import com.hsshy.beam.seckill.service.ISeckillService;
import com.hsshy.beam.seckill.util.redis.SeckillRedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 消费者
 * @author 科帮网 By https://blog.52itstyle.com
 */
@Service
public class RedisConsumer {
	
	@Autowired
	private ISeckillService seckillService;
	@Autowired
	private SeckillRedisUtil seckillRedisUtil;
	
    public void receiveMessage(String message) {
        //收到通道的消息之后执行秒杀操作(超卖)
    	String[] array = message.split(";"); 
    	if(seckillRedisUtil.getValue(array[0])==null){//control层已经判断了，其实这里不需要再判断了
    		R result = seckillService.startSeckilDBPCC_TWO(Long.parseLong(array[0]), Long.parseLong(array[1]));
    		if(result.equals(R.ok())){
				System.out.println(array[0].toString()+"秒杀成功");
//    			WebSocketServer.sendInfo(array[0].toString(), "秒杀成功");//推送给前台
    		}else{
//    			WebSocketServer.sendInfo(array[0].toString(), "秒杀失败");//推送给前台
				System.out.println(array[0].toString()+"秒杀失败");
    			seckillRedisUtil.cacheValue(array[0], "ok");//秒杀结束
    		}
    	}else{
			System.out.println(array[0].toString()+"秒杀失败");
//    		WebSocketServer.sendInfo(array[0].toString(), "秒杀失败");//推送给前台
    	}
    }
}