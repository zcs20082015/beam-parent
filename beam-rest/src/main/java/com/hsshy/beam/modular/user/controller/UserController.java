package com.hsshy.beam.modular.user.controller;
import com.hsshy.beam.base.controller.CommonController;
import com.hsshy.beam.modular.user.entity.User;
import com.hsshy.beam.modular.user.service.IUserService;
import com.hsshy.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Api(value="UserController",tags={"用户接口"})
@RestController
@RequestMapping("/user")
public class UserController extends CommonController<User> {

    @Autowired
    private IUserService userService;

    public UserController() {
        setCommonService(userService);
    }

    @ApiOperation(value = "新增")
    @PostMapping(value = "/add")
    public Object add(@RequestBody @ApiParam(name="实体对象",value="传入json格式",required=true) User entity) {
        Assert.notNull(entity,"数据不能为空");

/*        User a = new User();
        a.setId(100L);
        logger.error(a.toString());*/

        logger.error(entity.toString());
        if(userService.save(entity)){
            return R.ok("新增成功");
        }
        else {
            return R.ok("新增失败");

        }

    }

}