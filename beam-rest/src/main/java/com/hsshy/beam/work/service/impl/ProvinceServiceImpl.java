package com.hsshy.beam.work.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hsshy.beam.common.utils.R;
import com.hsshy.beam.work.dao.ProvinceMapper;
import com.hsshy.beam.work.entity.Province;
import com.hsshy.beam.work.service.IAreaService;
import com.hsshy.beam.work.service.ICityService;
import com.hsshy.beam.work.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 开通省份
 *
 * @author hs
 * @email 457030599@qq.com
 * @date 2018-12-21 17:32:16
 */
@Service
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, Province> implements IProvinceService {

    @Autowired
    private ICityService cityService;

    @Autowired
    private IAreaService areaService;

    @Override
    public R changeStatus(Long provinceId, Integer flag) {

        Province province = this.getById(provinceId);
        Assert.notNull(province,"找不到省份");
        province.setStatus(flag);
        boolean a = this.updateById(province);

        //关闭省份时是否关闭其下所有地区
//        if(a&&flag==0){
//            List<City> cities = cityService.list(new QueryWrapper<City>().eq("province_id",provinceId));
//            cities.forEach(city->{
//                city.setStatus(0);
//                boolean b = cityService.updateById(city);
//                if(b){
//                    List<Area> areas = areaService.list(new QueryWrapper<Area>().eq("city_id",city.getId()));
//                    areas.forEach(area->{
//                        area.setStatus(0);
//                    });
//                    areaService.updateBatchById(areas);
//                }
//
//            });
//        }


        return R.ok();
    }
}
