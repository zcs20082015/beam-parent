package com.hsshy.rest.modular.hello;
import com.hsshy.common.utils.R;
import com.hsshy.rest.modular.hello.service.BeamUserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

/*
* swagger 注解参考： https://blog.csdn.net/u014231523/article/details/76522486
* swagger 注解示例
*/
@Api(value="HelloController",tags={"你好接口"})
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private BeamUserService beamUserService;


    @ApiOperation(value = "index-value",notes = "index-notes")
    @GetMapping("/index")
    public Object index() {

        Hello hello = new Hello();
        hello.setId(1);
        hello.setName("aa");

        Hello hello1 = new Hello();
        hello.setId(2);
        hello.setName("bb");

        List<Hello> helloList = new ArrayList<Hello>();
        helloList.add(hello);
        helloList.add(hello1);

        return R.ok(helloList);
    }

    @ApiOperation(value = "hello-value",notes = "hello-notes")
    @GetMapping("/hello")

    /**
     * @method  hello
     * @description 描述一下方法的作用
     * @date: 2018/9/19 18:15
     * @author: hs
     * @param  * @param userName
     * @return java.lang.String
     */
    @ApiImplicitParam(name = "userName",value = "用户名",dataType = "string",paramType = "query",required = true)
    public Object hello(@RequestParam String userName) {
        return R.ok(200,"操作成功","Hello "+userName);
    }

    @ApiOperation("测试1")
    @GetMapping("/test1")
    @ApiImplicitParams({
            @ApiImplicitParam(name="name",value="用户名",dataType="string", paramType = "query",example="hs"),
            @ApiImplicitParam(name="id",value="用户id",dataType="string", paramType = "query")})
    public void test1(){

    }

    @ApiOperation("测试2")
    @GetMapping("/test2")
    @ApiImplicitParam(name="name",value="用户名",dataType="String", paramType = "query")
    public Object test2(String name){
        Assert.notNull(name,"用户名不能为空");

        return R.ok();
    }


    @ApiOperation("测试jpa")
    @GetMapping("/findByAge")
    @ApiImplicitParam(name="age",value="年龄",dataType="Integer", paramType = "query")
    public Object findByAge(Integer age){

        return R.ok(beamUserService.findByAge(age));
    }

}