package com.hsshy.beam.modular.system.controller;
import com.hsshy.beam.sys.entity.Dict;
import com.hsshy.beam.sys.service.IDictService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
/**
 * 字典表
 *
 * @author hs
 * @email 457030599@qq.com
 * @date 2018-10-10 21:39:07
 */
@Api(value="DictController",tags={"Dict接口"})
@Controller
@RequestMapping("/system/dict")
public class DictController {

    @Autowired
    private IDictService dictService;









}