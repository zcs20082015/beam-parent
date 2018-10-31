package com.hsshy.beam.modular.business.controller;
import com.hsshy.beam.common.utils.R;
import com.hsshy.beam.sys.service.ISysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value="SysConfigController",tags={"系统配置"})
@RestController
@RequestMapping("/sysconfig")
public class SysConfigController {


    @Autowired
    private ISysConfigService sysConfigService;

    @ApiOperation("根据key获取value")
    @GetMapping("/get/{paramKey}")
    public Object getValueByKey(@PathVariable String paramKey){
        return R.ok(sysConfigService.getValue(paramKey,""));
    }




}