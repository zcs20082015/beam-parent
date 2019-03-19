package com.hsshy.beam.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsshy.beam.api.DemoService;

@Service(version = "${provider.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return "Hello, " + name + " (from Spring Boot)";
    }
}