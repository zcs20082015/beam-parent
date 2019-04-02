package com.hsshy.beam.test;

import com.hsshy.beam.common.annotion.IgnoreUTokenAuth;
import com.hsshy.beam.common.utils.R;
import com.hsshy.beam.filter.properties.JwtProperties;
import com.hsshy.beam.filter.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 测试
 * @author: hs
 * @create: 2019-04-01 22:16:07
 **/
@RequestMapping(value = "/test")
@RestController
public class TestController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtProperties jwtProperties;

    @IgnoreUTokenAuth
    @PostMapping(value = "/login")
    public R  login(){
        return R.ok(jwtTokenUtil.generateToken("1",jwtProperties.getMd5Key()));
    }


    @IgnoreUTokenAuth
    @GetMapping(value = "/ignore")
    public R  testIgnore(){
        return R.ok();
    }

    @GetMapping(value = "/no/ignore")
    public R  testNoIgnore(){
        return R.ok();
    }




}
