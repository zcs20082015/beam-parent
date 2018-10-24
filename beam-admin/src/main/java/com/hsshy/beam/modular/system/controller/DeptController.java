package com.hsshy.beam.modular.system.controller;
import com.hsshy.beam.modular.system.entity.Dept;
import com.hsshy.beam.modular.system.service.IDeptService;
import com.hsshy.beam.web.base.controller.BaseCommonController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 部门管理
 *
 * @author hs
 * @email 457030599@qq.com
 * @date 2018-10-10 21:13:03
 */
@Api(value="DeptController",tags={"Dept接口"})
@Controller
@RequestMapping("/system/dept")
public class DeptController extends BaseCommonController<Dept,Long> {

    @Autowired
    private IDeptService deptService;



    public DeptController() {
        setCommonService(deptService);
    }



}