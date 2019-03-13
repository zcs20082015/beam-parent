package com.hsshy.beam.multi.controller;

import com.hsshy.beam.common.utils.R;
import com.hsshy.beam.multi.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 测试多数据源
 * @author: hs
 * @create: 2019-02-12 16:08:14
 **/
@Api(value="TestController",tags={"测试数据源(测试前新建测试表test)"})
@RequestMapping("/test/multi")
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @ApiOperation(value="测试数据源beam")
    @PostMapping("/beam")
    public R beam(){
        testService.testBeam();
        return R.ok();
    }
    @ApiOperation(value="测试数据源biz")
    @PostMapping("/biz")
    public R biz(){
        testService.testBiz();
        return R.ok();
    }
    @ApiOperation(value="测试数据源all")
    @PostMapping("/all")
    public R all(){
        testService.testAll();
        return R.ok();
    }


}
