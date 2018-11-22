package com.hsshy.beam.modular.system.controller;
import com.hsshy.beam.sys.entity.Dept;
import com.hsshy.beam.sys.service.IDeptService;
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
public class DeptController  {

    @Autowired
    private IDeptService deptService;






}