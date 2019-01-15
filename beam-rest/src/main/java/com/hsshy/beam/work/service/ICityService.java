package com.hsshy.beam.work.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hsshy.beam.common.utils.R;
import com.hsshy.beam.work.entity.City;

/**
 * 开通城市
 *
 * @author hs
 * @email 457030599@qq.com
 * @date 2018-12-24 09:50:10
 */
public interface ICityService extends IService<City> {

    R changeStatus(Long cityId, Integer flag);


}