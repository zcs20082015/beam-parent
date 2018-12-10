package com.hsshy.beam.modular.system.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 总览信息
 *
 * @author fengshuonan
 * @Date 2017年3月4日23:05:54
 */
@RestController
@RequestMapping("/dashboard")
public class DashboardController {



    /**
     * 跳转到黑板
     */
    @GetMapping
    public String blackboard() {

        return "首页";
    }
}
