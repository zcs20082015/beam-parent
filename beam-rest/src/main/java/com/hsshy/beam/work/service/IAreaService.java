package com.hsshy.beam.work.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hsshy.beam.common.utils.R;
import com.hsshy.beam.work.entity.Area;

/**
 * 开通区域
 *
 * @author hs
 * @email 457030599@qq.com
 * @date 2018-12-24 09:50:10
 */
public interface IAreaService extends IService<Area> {

     R changeStatus(Long areaId, Integer flag);

}