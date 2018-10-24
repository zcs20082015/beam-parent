package com.hsshy.beam.modular.system.controller;
import com.hsshy.beam.modular.system.entity.Dict;
import com.hsshy.beam.modular.system.service.IDictService;
import com.hsshy.beam.web.base.controller.BaseCommonController;
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
public class DictController extends BaseCommonController<Dict,Long> {

    @Autowired
    private IDictService dictService;



    public DictController() {
        setCommonService(dictService);
    }





}