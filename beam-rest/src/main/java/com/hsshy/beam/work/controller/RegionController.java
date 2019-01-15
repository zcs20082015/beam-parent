package com.hsshy.beam.work.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hsshy.beam.common.utils.R;
import com.hsshy.beam.interceptors.annotation.IgnoreUTokenAuth;
import com.hsshy.beam.work.entity.Area;
import com.hsshy.beam.work.entity.City;
import com.hsshy.beam.work.entity.Province;
import com.hsshy.beam.work.service.IAreaService;
import com.hsshy.beam.work.service.ICityService;
import com.hsshy.beam.work.service.IProvinceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 区域相关接口
 * @author: hs
 * @create: 2019-01-09 15:04:06
 **/
@Api(value="RegionController",tags={"区域相关接口"})
@RestController
@RequestMapping("/region")
public class RegionController {

    @Autowired
    private IProvinceService provinceService;

    @Autowired
    private ICityService cityService;

    @Autowired
    private IAreaService areaService;

    @IgnoreUTokenAuth
    @ApiOperation("获取开通省份")
    @GetMapping("/get/province")
    public Object getProvinceList(Integer status){

        return R.ok(provinceService.list(new QueryWrapper<Province>().eq("status",status)));
    }

    @IgnoreUTokenAuth
    @ApiOperation("获取开通城市")
    @GetMapping("/get/city")
    public Object getCityList(Integer status,Long provinceId){

        return R.ok(cityService.list(new QueryWrapper<City>().eq("status",status).eq("province_id",provinceId)));
    }


    @IgnoreUTokenAuth
    @ApiOperation("获取开通地区")
    @GetMapping("/get/area")
    public Object getAreaList(Integer status,Long cityId){

        return R.ok(areaService.list(new QueryWrapper<Area>().eq("status",status).eq("city_id",cityId)));
    }


}
