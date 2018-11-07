package com.hsshy.beam.modular.business.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hsshy.beam.api.DemoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoConsumerController {

    @Reference(version = "${beam.admin.version}",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880")
    private DemoService demoService;

    @RequestMapping("/sayHello/{name}")
    public String sayHello(@PathVariable("name") String name) {
        return demoService.sayHello(name);
    }

}