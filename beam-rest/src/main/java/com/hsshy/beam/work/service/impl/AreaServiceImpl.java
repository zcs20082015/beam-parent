package com.hsshy.beam.work.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hsshy.beam.common.utils.R;
import com.hsshy.beam.work.dao.AreaMapper;
import com.hsshy.beam.work.entity.Area;
import com.hsshy.beam.work.entity.City;
import com.hsshy.beam.work.service.IAreaService;
import com.hsshy.beam.work.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 开通区域
 *
 * @author hs
 * @email 457030599@qq.com
 * @date 2018-12-24 09:50:10
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements IAreaService {

    @Autowired
    private ICityService cityService;

    @Override
    public R changeStatus(Long areaId, Integer flag) {
        Area area = this.getById(areaId);
        Assert.notNull(area,"找不到地区");
        area.setStatus(flag);

        boolean b = false;
        if(flag==1){
            City city = cityService.getById(area.getCityId());
            if(city.getStatus()==0){
                return R.fail("请先开通城市");
            }
            else {
                b = this.updateById(area);

            }
        }
        else {
            b = this.updateById(area);
        }


        return R.ok();
    }
}
