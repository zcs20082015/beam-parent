package com.hsshy.beam.work.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hsshy.beam.common.utils.R;
import com.hsshy.beam.work.dao.CityMapper;
import com.hsshy.beam.work.entity.City;
import com.hsshy.beam.work.entity.Province;
import com.hsshy.beam.work.service.IAreaService;
import com.hsshy.beam.work.service.ICityService;
import com.hsshy.beam.work.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 开通城市
 *
 * @author hs
 * @email 457030599@qq.com
 * @date 2018-12-24 09:50:10
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements ICityService {

    @Autowired
    private IAreaService areaService;

    @Autowired
    private IProvinceService provinceService;

    @Override
    public R changeStatus(Long cityId, Integer flag) {
        City city = this.getById(cityId);
        Assert.notNull(city,"找不到城市");
        city.setStatus(flag);
        boolean b = false;
        if(flag==1){
            Province province = provinceService.getById(city.getProvinceId());
            if(province.getStatus()==0){
                return R.fail("请先开通省份");
            }
            else {
               b = this.updateById(city);

            }
        }
        else {
            b = this.updateById(city);
        }

        //关闭城市时是否关闭其下所有地区
//        if(b&&flag==0){
//            List<Area> areas = areaService.list(new QueryWrapper<Area>().eq("city_id",city.getId()));
//            areas.forEach(area->{
//                area.setStatus(0);
//            });
//            areaService.updateBatchById(areas);
//        }
        return R.ok();
    }
}
