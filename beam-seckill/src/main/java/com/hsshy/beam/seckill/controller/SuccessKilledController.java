package com.hsshy.beam.seckill.controller;
import com.hsshy.beam.seckill.service.ISuccessKilledService;
import com.hsshy.beam.common.base.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 秒杀成功明细表
 *
 * @author hs
 * @email 457030599@qq.com
 * @date 2018-11-20 14:52:22
 */
@Api(value="SuccessKilledController",tags={"SuccessKilled接口"})
@RequestMapping("/queue/successkilled")
public class SuccessKilledController extends BaseController {

    @Autowired
    private ISuccessKilledService successKilledService;








}