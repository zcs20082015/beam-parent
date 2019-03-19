package com.hsshy.beam.dubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hsshy.beam.api.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Api(value="DemoConsumerController",tags={"dubbo"})
@RequestMapping("/dubbo/demo")
@RestController
public class DemoConsumerController {

    @Reference(version = "${consumer.version}",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880")
    private DemoService demoService;


    @ApiOperation(value = "sayHello")
    @GetMapping("/sayHello/{name}")
    public String sayHello(@PathVariable("name") String name) {
        return demoService.sayHello(name);
    }

}