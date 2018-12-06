package com.hsshy.beam.seckill.annotation;
import java.lang.annotation.*;


@Target({ElementType.PARAMETER, ElementType.METHOD})    
@Retention(RetentionPolicy.RUNTIME)    
@Documented    
public  @interface RedisServicelock {
	 String description()  default "";
}
