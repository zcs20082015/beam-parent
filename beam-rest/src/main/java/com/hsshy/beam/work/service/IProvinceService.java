package com.hsshy.beam.work.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hsshy.beam.common.utils.R;
import com.hsshy.beam.work.entity.Province;

/**
 * 开通省份
 *
 * @author hs
 * @email 457030599@qq.com
 * @date 2018-12-21 17:32:16
 */
public interface IProvinceService extends IService<Province> {

    R changeStatus(Long provinceId, Integer flag);


}